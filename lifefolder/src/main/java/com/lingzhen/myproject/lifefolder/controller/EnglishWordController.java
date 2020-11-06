package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.EnglishWordService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("englishWord")
public class EnglishWordController {

    @Autowired
    private EnglishWordService englishwordService;


    @RequestMapping("myWordList")
    @ResponseBody
    public Result myWordList(){
        Result result = new Result();

        Map para = HttpServletUtil.getRequestParameter();

        try {
            List data = englishwordService.myWordList(para);
            result.setData(data);
        } catch (Exception e) {
            result.setError();
        }
        return result;
    }
}
