package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    Result upload(MultipartFile file);

}
