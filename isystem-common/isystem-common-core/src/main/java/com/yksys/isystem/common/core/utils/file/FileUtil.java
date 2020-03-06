package com.yksys.isystem.common.core.utils.file;

import com.yksys.isystem.common.core.constants.FileTypeEnum;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 文件工具类
 * @author: YuKai Fan
 * @create: 2020-03-02 10:17
 **/
public class FileUtil {

    /**
     * 根据文件类型获取相应的标识
     * @param value 文件类型
     * @return 1: 图片, 2: 视频, 3: 文档, 4: 种子, 5: 音乐, 6: 其他
     */
    public static int getTypeFlag(FileTypeEnum value) {
        int type = 6;//其他
        if (value == null) {
            throw new ParameterException("文件类型错误！");
        }
        // 图片  
        FileTypeEnum[] pics = { FileTypeEnum.JPEG, FileTypeEnum.PNG, FileTypeEnum.GIF, FileTypeEnum.TIFF, FileTypeEnum.BMP, FileTypeEnum.DWG, FileTypeEnum.PSD, FileTypeEnum.JPG };

        //文档
        FileTypeEnum[] docs = { FileTypeEnum.RTF, FileTypeEnum.XML, FileTypeEnum.HTML, FileTypeEnum.CSS, FileTypeEnum.JS, FileTypeEnum.EML, FileTypeEnum.DBX, FileTypeEnum.PST, FileTypeEnum.XLS_DOC, FileTypeEnum.XLSX_DOCX, FileTypeEnum.VSD,
                FileTypeEnum.MDB, FileTypeEnum.WPS, FileTypeEnum.WPD, FileTypeEnum.EPS, FileTypeEnum.PDF, FileTypeEnum.QDF, FileTypeEnum.PWL, FileTypeEnum.ZIP, FileTypeEnum.RAR, FileTypeEnum.JSP, FileTypeEnum.JAVA, FileTypeEnum.CLASS,
                FileTypeEnum.JAR, FileTypeEnum.MF, FileTypeEnum.EXE, FileTypeEnum.CHM };

        //视频
        FileTypeEnum[] videos = { FileTypeEnum.AVI, FileTypeEnum.RAM, FileTypeEnum.RM, FileTypeEnum.MPG, FileTypeEnum.MOV, FileTypeEnum.ASF, FileTypeEnum.MP4, FileTypeEnum.FLV, FileTypeEnum.MID };

        //种子
        FileTypeEnum[] tottents = { FileTypeEnum.TORRENT };

        //音乐
        FileTypeEnum[] audios = { FileTypeEnum.WAV, FileTypeEnum.MP3 };

        FileTypeEnum[] others = {};

        //图片
        for (FileTypeEnum fileTypeEnum : pics) {
            if (fileTypeEnum.equals(value)) {
                type = 1;
            }
        }
        //视频
        for (FileTypeEnum fileTypeEnum : videos) {
            if (fileTypeEnum.equals(value)) {
                type = 2;
            }
        }
        //文档
        for (FileTypeEnum fileTypeEnum : docs) {
            if (fileTypeEnum.equals(value)) {
                type = 3;
            }
        }
        //种子
        for (FileTypeEnum fileTypeEnum : tottents) {
            if (fileTypeEnum.equals(value)) {
                type = 4;
            }
        }
        //音乐
        for (FileTypeEnum fileTypeEnum : audios) {
            if (fileTypeEnum.equals(value)) {
                type = 5;
            }
        }

        return type;
    }

    /**
     * 根据文件类型返回相应的文件路径
     * @param type
     * @return
     */
    public static String getFilePath(int type, String fileId, String postfix) {
        String prefix = "";
        switch (type) {
            case 1:
                prefix = "images";
                break;
            case 2:
                prefix = "videos";
                break;
            case 3:
                prefix = "docs";
                break;
            case 4:
                prefix = "tottents";
                break;
            case 5:
                prefix = "audios";
                break;
            case 6:
                prefix = "others";
                break;
        }

        String dataStr = TimeUtil.parseTime(LocalDateTime.now(), TimeUtil.TimeFormat.SHORT_DATE_PATTERN_LINE);
        String filePath = prefix + "/" + dataStr + "/" + fileId + "." + postfix;
        return filePath;
    }

    /**
     * 获取文件类型
     * @param postfix
     * @return
     */
    public static FileTypeEnum getType(String postfix) {
        FileTypeEnum[] fileTypeEnums = FileTypeEnum.values();
        for (FileTypeEnum fileTypeEnum : fileTypeEnums) {
            if (postfix.equals(fileTypeEnum.getValue())) {
                return fileTypeEnum;
            }
        }
        return null;
    }

    /**
     * request转为file
     * @param request
     * @return
     */
    public static FileModel getRequestFile(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            Map<String, MultipartFile> fileMap = ((MultipartHttpServletRequest) request).getFileMap();
            if (CollectionUtils.isEmpty(fileMap) || CollectionUtils.isEmpty(fileMap.keySet())) {
                return null;
            }
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                if (StringUtil.isBlank(entry.getKey())) {
                    continue;
                }

                if (!entry.getValue().isEmpty()) {
                    Map<String, Object> parameterMap = MapUtil.getParameterMap(request);
                    FileModel fileModel = new FileModel();
                    fileModel.setFile(entry.getValue());
                    fileModel.setBucketName(parameterMap.get("bucketName").toString());
                    fileModel.setCannedACL(parameterMap.get("cannedACL").toString());
                    fileModel.setDataRedundancyType(parameterMap.get("dataRedundancyType").toString());
                    fileModel.setStorageType(parameterMap.get("storageType").toString());
                    return fileModel;
                }
            }
        }

        return null;
    }
}