package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface UserService {

    Map findById(Long userId);

    int insertAccessLog(String method, String parameter);

    int initAccount(Long userId);
}
