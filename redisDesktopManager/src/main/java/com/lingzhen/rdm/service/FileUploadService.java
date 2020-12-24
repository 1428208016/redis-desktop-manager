package com.lingzhen.rdm.service;

import com.lingzhen.rdm.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface FileUploadService {

    Result upload(MultipartFile file);

    void download(String key, HttpServletResponse httpServletResponse);

    Map findById(String key);

    void view(String key, HttpServletResponse httpServletResponse);
}
