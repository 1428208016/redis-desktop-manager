package com.lingzhen.rdm.controller;

import com.lingzhen.rdm.pojo.Result;
import com.lingzhen.rdm.service.UserService;
import com.lingzhen.rdm.util.HttpServletUtil;
import com.lingzhen.rdm.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "findById", method = RequestMethod.POST)
    @ResponseBody
    public Result findById() {
        Result result = new Result();
        Map map =  HttpServletUtil.getRequestParameter();
        Long userId;
        if (VerifyUtil.stringTrimIsEmpty(map.get("userId"))) {
            userId = HttpServletUtil.getUserId();
        } else {
            userId = Long.valueOf(map.get("userId").toString());
        }
        if (null == userId) {
            result.setError("用户Id为空！");
            return result;
        }
        try {
            Map data = userService.findById(userId);
            result.setData(data);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }

}
