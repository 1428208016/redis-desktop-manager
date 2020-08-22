package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.common.util.JWTUtil;
import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletResponse response) {
        Result result = new Result();
        Map map =  HttpServletUtil.getRequestParameter();


        Cookie cookie = new Cookie(JWTUtil.TOKEN,JWTUtil.createToken("1"));
        response.addCookie(cookie);

        return result;
    }

}
