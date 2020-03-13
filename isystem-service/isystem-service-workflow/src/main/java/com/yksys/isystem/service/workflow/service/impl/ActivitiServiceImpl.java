package com.yksys.isystem.service.workflow.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.vo.ActivitiModelVo;
import com.yksys.isystem.service.workflow.service.ActivitiService;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    @Override
    public PageInfo<Model> getModels(int start, int pageSize, Map<String, Object> map) {
        List<Model> models = repositoryService.createModelQuery().orderByCreateTime().desc().listPage(start, pageSize);
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
        //同步用户

        //同步角色(组)

        //同步用户-用户组关联
    }
}