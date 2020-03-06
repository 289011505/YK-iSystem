package com.yksys.isystem.service.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.security.YkUserDetails;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.core.utils.file.FileModel;
import com.yksys.isystem.common.core.utils.file.FileUtil;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.common.vo.BucketVo;
import com.yksys.isystem.common.vo.SystemUserVo;
import com.yksys.isystem.service.admin.mapper.SystemUserMapper;
import com.yksys.isystem.service.admin.service.SystemUserService;
import com.yksys.isystem.service.admin.service.feign.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
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
        systemUserMapper.addSystemUser(systemUser);
        return systemUser;
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
        return systemUserMapper.getSystemUsers(map);
    }

    @Override
    public void editSystemUser(SystemUser systemUser) {
        systemUserMapper.editSystemUserById(systemUser);
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

}
