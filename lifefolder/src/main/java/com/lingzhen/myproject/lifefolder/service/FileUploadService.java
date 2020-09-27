package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface FileUploadService {

    Result upload(MultipartFile file);

    void download(String key, HttpServletResponse httpServletResponse);

    Map findById(String key);
}
