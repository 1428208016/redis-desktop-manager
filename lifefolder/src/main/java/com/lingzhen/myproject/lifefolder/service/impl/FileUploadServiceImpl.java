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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
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
            Date now = new Date();
            String path = FILEUPLOADPATH+DateUtil.getYear(now)+"/"+DateUtil.getMonth(now)+"/"+DateUtil.getDay(now);
            // 文件上传
            FileUtil.saveFileFromInputStream(file.getInputStream(),path,uuid);
            // 入库
            Map map = new HashMap<>();
            map.put("uuId", uuid);
            map.put("newFileName", uuid);
            map.put("fileName",file.getOriginalFilename());
            map.put("fileType",file.getContentType());
            map.put("fileSize",file.getSize());
            map.put("filePath",path+"/"+uuid);
            map.put("createTime",DateUtil.getTime());
            map.put("createDate", DateUtil.getDate());
            map.put("creatorId", HttpServletUtil.getUserId());
            fileMapper.insertFile(map);
            result.setData(uuid);
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

    @Override
    public void download(String key, HttpServletResponse response) {
        try {
            Map data = this.findById(key);
            if (null != data) {
                // path是要下载的文件的路径。
                String filePath = data.get("filePath").toString();
                // 文件名。
                String filename = data.get("fileName").toString();
                // 文件大小
                String fileSize = data.get("fileSize").toString();

                // 以流的形式下载文件。
                InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
                response.addHeader("Content-Length",fileSize);
                OutputStream output = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                output.write(buffer);
                output.flush();
                output.close();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public Map findById(String key) {
        return fileMapper.findById(key);
    }

}
