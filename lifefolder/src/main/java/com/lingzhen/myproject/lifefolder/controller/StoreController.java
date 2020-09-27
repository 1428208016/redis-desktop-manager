package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.StoreService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import com.lingzhen.myproject.lifefolder.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "myProject", method = RequestMethod.POST)
    @ResponseBody
    public Result myProject() {
        Result result = new Result();
        try {
            List data = storeService.myProject();
            result.setData(data);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }

    @RequestMapping(value = "projectList", method = RequestMethod.POST)
    @ResponseBody
    public Result projectList() {
        Result result = new Result();
        Map para = HttpServletUtil.getRequestParameter();
        try {
            List data = storeService.projectList(para);
            result.setData(data);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }

    @RequestMapping(value = "buyProject", method = RequestMethod.POST)
    @ResponseBody
    public Result buyProject() {
        Result result = new Result();
        Map para = HttpServletUtil.getRequestParameter();
        try {
            result = storeService.buyProject(para);
        } catch (Exception e) {
            result.setError();
        }

        return result;
    }


}
