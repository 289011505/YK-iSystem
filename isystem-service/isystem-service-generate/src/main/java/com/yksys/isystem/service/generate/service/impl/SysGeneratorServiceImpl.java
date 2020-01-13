package com.yksys.isystem.service.generate.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.yksys.isystem.service.generate.mapper.SysGeneratorMapper;
import com.yksys.isystem.service.generate.service.SysGeneratorService;
import com.yksys.isystem.service.generate.util.GenUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-01-13 11:50
 **/
@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {
    @Autowired
    private SysGeneratorMapper sysGeneratorMapper;

    @Override
    public List<Map<String, String>> getDBTables(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getDBTables(map);
    }

    @Override
    public List<Map<String, String>> getDBTables(Map<String, Object> map) {
        return sysGeneratorMapper.getDBTables(map);
    }

    @Override
    public byte[] generateCode(String[] tables) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String table : tables) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("tableName", table);
            List<Map<String, String>> dbTables = this.getDBTables(map);
            if (!CollectionUtils.isEmpty(dbTables) && dbTables.size() == 1) {
                List<Map<String, String>> columns = sysGeneratorMapper.getColumns(table);
                //生成代码
                GenUtil.generatorCode(dbTables.get(0), columns, zip);
            }
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}