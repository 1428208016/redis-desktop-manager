package com.lingzhen.myproject.lifefolder.service.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.myproject.common.util.UuidUtil;
import com.lingzhen.myproject.lifefolder.mapper.FileMapper;
import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.FileUploadService;
import com.lingzhen.myproject.lifefolder.util.FileUtil;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${fileUploadPath}")
    private String FILEUPLOADPATH;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Result upload(MultipartFile file) {
        Result result = new Result();

        try {
            String uuid = UuidUtil.getUuid();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
            String path = FILEUPLOADPATH+uuid+"."+suffix;
            FileUtil.saveFileFromInputStream(file.getInputStream(),path);

            Map map = new HashMap<>();
            map.put("uuId", uuid);
            map.put("newFileName", uuid);
            map.put("fileName",file.getOriginalFilename());
            map.put("fileType",file.getContentType());
            map.put("fileSize",file.getSize());
            map.put("filePath",path);
            map.put("createTime",DateUtil.getTime());
            map.put("createDate", DateUtil.getDate());
            map.put("creatorId", HttpServletUtil.getUserId());
            fileMapper.insertFile(map);
        } catch (Exception e) {
        }
        return result;
    }

}
