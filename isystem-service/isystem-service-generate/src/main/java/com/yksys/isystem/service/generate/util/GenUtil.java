package com.yksys.isystem.service.generate.util;

import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.common.model.Column;
import com.yksys.isystem.common.model.Table;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: YK-iSystem
 * @description: 代码生成器 工具类
 * @author: YuKai Fan
 * @create: 2020-01-13 14:55
 **/
public class GenUtil {

    public static List<String> getTemplates() {
        List<String> templates = Lists.newArrayList();
        templates.add("codeTemplate/Controller.java.vm");
        templates.add("codeTemplate/Entity.java.vm");
        templates.add("codeTemplate/Mapper.java.vm");
        templates.add("codeTemplate/Mapper.xml.vm");
        templates.add("codeTemplate/Service.java.vm");
        templates.add("codeTemplate/ServiceImpl.java.vm");
        templates.add("codeTemplate/Api.js.vm");
        templates.add("codeTemplate/List.vue.vm");
        templates.add("codeTemplate/EntityVo.java.vm");
//        templates.add("codeTemplate/Controller.java.vm");
        return templates;
    }

    /**
     * 生成代码
     * @param table
     * @param columns
     * @param zip
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        Configuration config = getConfig();
        //表信息
        Table tableEntity = new Table();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<Column> columsList = Lists.newArrayList();
        for(Map<String, String> column : columns){
            Column columnEntity = new Column();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);

            //是否主键
            if("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null){
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if(tableEntity.getPk() == null){
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("package", config.getString("package"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", TimeUtil.getCurrentDatetime());
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for(String template : templates){
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), tableEntity.getClassname(), config.getString("package"))));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }

    /**
     * 获取配置信息
     * @return
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败", e);
        }
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if(StringUtils.isNotBlank(tablePrefix)){
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 获取文件名
     * @param template
     * @param className
     * @param packageName
     * @return
     */
    public static String getFileName(String template, String className, String classname, String packageName){
        String packagePath = "main" + File.separator + "java" + File.separator;
        if(StringUtils.isNotBlank(packageName)){
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }
        if(template.contains("Entity.java.vm")){
            return packagePath + "pojo" + File.separator + className + ".java";
        }
        if(template.contains("EntityVo.java.vm")){
            return packagePath + "pojo" + File.separator + className + "Vo.java";
        }
        if(template.contains("Controller.java.vm")){
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }
        if(template.contains("Service.java.vm")){
            return packagePath + "service" + File.separator + className + "Service.java";
        }
        if(template.contains("ServiceImpl.java.vm")){
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }
        if(template.contains("Mapper.java.vm")){
            return packagePath + "mapper" + File.separator + className + "Mapper.java";
        }
        if(template.contains("Mapper.xml.vm")){
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + className + "Mapper.xml";
        }

        if(template.contains("List.vue.vm")){
            return "main" + File.separator + "vue" + File.separator + classname + ".vue";
        }
        if(template.contains("Api.js.vm")){
            return "main" + File.separator + "vue" + File.separator + classname + ".js";
        }

        return null;
    }
}