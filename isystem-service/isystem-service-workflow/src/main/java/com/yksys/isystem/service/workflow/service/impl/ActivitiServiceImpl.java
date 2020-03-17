package com.yksys.isystem.service.workflow.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.vo.ActivitiModelVo;
import com.yksys.isystem.service.workflow.service.ActivitiProcessService;
import com.yksys.isystem.service.workflow.service.ActivitiService;
import com.yksys.isystem.service.workflow.service.feign.AdminService;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-12 16:19
 **/
@Service
public class ActivitiServiceImpl implements ActivitiService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private IdentityService identityService;

    @Override
    public PageInfo<Model> getModels(int start, int pageSize, Map<String, Object> map) {
        List<Model> models = repositoryService.createModelQuery().orderByCreateTime().desc().listPage(start - 1, pageSize);
        PageInfo pageList = new PageInfo<>(models);
        return pageList;
    }

    @Override
    public List<Model> getModels(Map<String, Object> map) {
        List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().list();
        return list;
    }

    @Override
    public String createNewModel(HttpServletRequest request, HttpServletResponse response, ActivitiModelVo activitiModelVo) {
        try {
            //初始化一个空模型
            Model model = repositoryService.newModel();

            //设置节点信息
            ObjectNode modelNode = objectMapper.createObjectNode();
            modelNode.put(ModelDataJsonConstants.MODEL_NAME, activitiModelVo.getName());
            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, activitiModelVo.getDescription());
            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, activitiModelVo.getRevision());

            model.setName(activitiModelVo.getName());
            model.setKey(activitiModelVo.getKey());
            model.setMetaInfo(modelNode.toString());

            //保存模型
            repositoryService.saveModel(model);

            //完善ModelEditorSource
            String id = model.getId();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("UTF-8"));

            return id;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParameterException("模型创建失败");
        }
    }

    @Override
    public void delModel(String id) {
        repositoryService.deleteModel(id);
    }

    @Override
    public void delModelByIds(String[] ids) {
        if (ids.length != 0) {
            for (String id : ids) {
                this.delModel(id);
            }
        }
    }

    @Override
    public void synchronizeData() {
        try {
            //同步用户
            synchronizeUsers();

            //同步角色(组)
            synchronizeGroups();

            //同步用户-用户组关联
            synchronizeMemberShip();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("同步数据错误！");
        }
    }

    @Override
    public void deployByModelId(String id) {
        try {
            Model model = repositoryService.getModel(id);
            byte[] bytes = repositoryService.getModelEditorSource(model.getId());
            if (bytes == null) {
                throw new ParameterException("模型数据为空，请先设计流程并成功保存");
            }
            JsonNode jsonNode = objectMapper.readTree(bytes);

            BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(jsonNode);
            if (bpmnModel.getProcesses().size() == 0) {
                throw new ParameterException("数据模型不符要求，请至少设计一条主线流程");
            }

            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
            //发布流程
            String processName = model.getName() + ".bpmn20.xml";
            Deployment deploy = repositoryService.createDeployment()
                    .name(model.getName())
                    .addString(processName, new String(bpmnBytes, "UTF-8"))
                    .deploy();
            model.setDeploymentId(deploy.getId());
            repositoryService.saveModel(model);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParameterException("流程部署失败!");
        }
    }

    private void synchronizeMemberShip() {
        Result<List<Map<String, Object>>> result = adminService.getAllUserRoles();
        if (result.getCode() == HttpStatus.OK.value()) {
            if (!CollectionUtils.isEmpty(result.getData())) {
                result.getData().forEach(userRole -> {
                    identityService.deleteMembership(userRole.get("userId").toString(),
                            userRole.get("roleId").toString());

                    identityService.createMembership(userRole.get("userId").toString(),
                            userRole.get("roleId").toString());
                });
            }
        }
    }

    private void synchronizeGroups() {
        Result<List<Map<String, Object>>> result = adminService.getSystemRolesNoPage(Maps.newHashMap());
        if (result.getCode() == HttpStatus.OK.value()) {
            if (!CollectionUtils.isEmpty(result.getData())) {
                result.getData().forEach(role -> {
                    Group group = new GroupEntity();
                    group.setId(role.get("id").toString());
                    group.setName(role.get("roleName").toString());
                    identityService.deleteGroup(group.getId());
                    identityService.saveGroup(group);
                });
            }
        }
    }

    private void synchronizeUsers() {
        Result<List<Map<String, Object>>> result = adminService.getSystemUsersNoPage(Maps.newHashMap());
        if (result.getCode() == HttpStatus.OK.value()) {
            if (!CollectionUtils.isEmpty(result.getData())) {
                result.getData().forEach(user -> {
                    User au = new UserEntity();
                    au.setEmail(StringUtil.isNotBlank(user.get("email")) ? user.get("email").toString() : "");
                    au.setPassword(user.get("password").toString());
                    au.setId(user.get("id").toString());
                    au.setFirstName(StringUtil.isNotBlank(user.get("userName"))?user.get("userName").toString():"游客");
                    au.setLastName(StringUtil.isNotBlank(user.get("nickName")) ? user.get("nickName").toString() : "");
                    identityService.deleteUser(au.getId());
                    identityService.saveUser(au);
                });
            }
        }
    }
}