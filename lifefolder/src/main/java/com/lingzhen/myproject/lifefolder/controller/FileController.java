package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.FileUploadService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import com.lingzhen.myproject.lifefolder.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result view() {
        Result result = new Result();
        try {
            Map map = HttpServletUtil.getRequestParameter();

            File file = new File(map.get("path").toString());
            File[] fileList = file.listFiles();
            List<String> retData = new ArrayList<>();
            for (File temp : fileList) {
                retData.add(temp.getName());
            }
            result.setData(retData);
        }catch (Exception e){
            result.setError(e.getMessage());
        }
        return result;
    }

    //下载
    @RequestMapping(value = "/download", method = {RequestMethod.POST,RequestMethod.GET})
    public void download(HttpServletResponse response) {
        Result result = new Result();
        try{
            Map para = HttpServletUtil.getRequestParameter();
            if(VerifyUtil.stringTrimIsEmpty(para.get("key"))) {
                result.setError("文件key为空");
                return;
            }
            fileUploadService.download(para.get("key").toString(),response);
        }catch (Exception e){
            result.setError(e.getMessage());
        }
    }

//    @RequestMapping(value = "/show", method = RequestMethod.POST)
//    public Result show() {
//        Result result = new Result();
//        try {
//            Map map = HttpServletUtil.getRequestParameter();
//
//            File file = new File(map.get("path").toString());
//            File[] fileList = file.listFiles();
//            List<String> retData = new ArrayList<>();
//            for (File temp : fileList) {
//                retData.add(temp.getName());
//            }
//            result.setData(retData);
//        }catch (Exception e){
//            result.setError(e.getMessage());
//        }
//        return result;
//    }

    //上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        try{
            if(file.isEmpty() || VerifyUtil.stringIsEmpty(file.getOriginalFilename())) {
                result.setError("上传文件为空");
                return result;
            }
            if(!file.getContentType().contains("")) {
                result.setError("上传文件contentType为空");
                return result;
            }
            result = fileUploadService.upload(file);
            return result;
        }catch (Exception e){
            result.setError(e.getMessage());
        }
        return result;
    }

}
