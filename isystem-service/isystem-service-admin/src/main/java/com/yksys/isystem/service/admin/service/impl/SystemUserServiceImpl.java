package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.PinYinUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.core.utils.file.FileModel;
import com.yksys.isystem.common.core.utils.file.FileUtil;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.common.pojo.UserRole;
import com.yksys.isystem.common.vo.BucketVo;
import com.yksys.isystem.service.admin.mapper.SystemUserMapper;
import com.yksys.isystem.service.admin.service.SystemUserService;
import com.yksys.isystem.service.admin.service.feign.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-03 20:05
 **/
@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public SystemUser addSystemUser(SystemUser systemUser) {
        systemUser.setId(AppUtil.randomId());
        systemUser.setStatus(1);
        if (StringUtil.isBlank(systemUser.getPassword())) {//如果密码为空则使用默认密码111111
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode("111111");
            systemUser.setPassword(encode);
        }
        if (StringUtil.isBlank(systemUser.getAccount())) {//如果账号为空则使用名称的拼音
            systemUser.setAccount(PinYinUtil.toPinyin(systemUser.getUserName()));
        }
        systemUserMapper.addSystemUser(systemUser);

        //添加用户角色关系
        if (StringUtil.isBlank(systemUser.getRoleId())) {
            throw new ParameterException("角色为空, 请选择角色!");
        }
        addUserRoles(systemUser);
        return systemUser;
    }

    private void addUserRoles(SystemUser systemUser) {
        List<UserRole> list = Lists.newArrayList();
        UserRole userRole = new UserRole();
        userRole.setRoleId(systemUser.getRoleId());
        userRole.setUserId(systemUser.getId());
        list.add(userRole);
//        systemUser.getRoles().forEach(roleId -> {
//            UserRole userRole = new UserRole();
//            userRole.setUserId(systemUser.getId());
//            userRole.setRoleId(roleId);
//            list.add(userRole);
//        });
        systemUserMapper.addUserRoles(list);
    }

    @Override
    public Map<String, Object> getSystemUserById(String id) {
        return systemUserMapper.getSystemUserById(id);
    }

    @Override
    public List<Map<String, Object>> getSystemUsers(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getSystemUsers(map);
    }

    @Override
    public List<Map<String, Object>> getSystemUsers(Map<String, Object> map) {
        List<Map<String, Object>> systemUsers = systemUserMapper.getSystemUsers(map);
        systemUsers.forEach(systemUser -> {
            systemUser.put("roleId", systemUserMapper.getUserRolesByUserId(systemUser.get("id").toString()));
        });
        return systemUsers;
    }

    @Override
    public void editSystemUser(SystemUser systemUser) {
        systemUserMapper.editSystemUserById(systemUser);

        //先删除用户角色关系, 再添加
        systemUserMapper.delUserRolesByUserId(systemUser.getId());
        if (!StringUtil.isBlank(systemUser.getRoleId())) {
            addUserRoles(systemUser);
        }
    }

    @Override
    public void delSystemUserById(String id) {
        systemUserMapper.delSystemUserById(id);
    }

    @Override
    public void delSystemUserByIs(List<String> ids) {
        systemUserMapper.delSystemUserByIds(ids);
    }

    @Override
    public void delSystemUserRealById(String id) {
        systemUserMapper.delSystemUserRealById(id);
    }

    @Override
    public void delSystemUserRealByIds(List<String> ids) {
        systemUserMapper.delSystemUserRealByIds(ids);
    }

    @Override
    public Result updateUserIcon(HttpServletRequest request) throws IOException {
        FileModel fileModel = FileUtil.getRequestFile(request);
        if (fileModel == null) {
            return null;
        }
        BucketVo bucketVo = new BucketVo();
        bucketVo.setBucketName(fileModel.getBucketName());
        bucketVo.setStorageType(fileModel.getStorageType());
        bucketVo.setDataRedundancyType(fileModel.getDataRedundancyType());
        bucketVo.setCannedACL(fileModel.getCannedACL());
        return fileUploadService.uploadOSSByFile(fileModel.getFile(), bucketVo);
    }

    @Override
    public List<Map<String, Object>> getAllUserRoles() {
        return systemUserMapper.getAllUserRoles();
    }

}
