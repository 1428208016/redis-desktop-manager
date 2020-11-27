package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface LoginService {

    Result register(Map map);

    Result login(Map map, HttpServletRequest httpServletRequest, HttpServletResponse response);

    Result logout(HttpServletRequest httpServletRequest, HttpServletResponse response);

    Result sendRegisterYzm(String mobile);
}
