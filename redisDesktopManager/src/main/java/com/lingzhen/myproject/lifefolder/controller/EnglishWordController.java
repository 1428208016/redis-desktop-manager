package com.lingzhen.myproject.lifefolder.controller;

import com.github.pagehelper.PageInfo;
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

    @RequestMapping("lexicon4500List")
    @ResponseBody
    public Result lexicon4500List(){
        Result result = new Result();
        Map para = HttpServletUtil.getRequestParameter();

        try {
            List data = englishwordService.lexicon4500List(para);
            PageInfo pi = new PageInfo(data);
            result.setData(pi);
        } catch (Exception e) {
            result.setError();
        }
        return result;
    }

    @RequestMapping("findLexicon4500ById")
    @ResponseBody
    public Result findLexicon4500ById(){
        Result result = new Result();

        try {
            Map para = HttpServletUtil.getRequestParameter();
            Long elId = Long.valueOf(para.get("elId").toString());
            Map data = englishwordService.findLexicon4500ById(elId);
            result.setData(data);
        } catch (Exception e) {
            result.setError();
        }
        return result;
    }

    @RequestMapping("lexicon4500Edit")
    @ResponseBody
    public Result lexicon4500Edit(){
        Result result = new Result();

        try {
            Map para = HttpServletUtil.getRequestParameter();
            englishwordService.lexicon4500Edit(para);
        } catch (Exception e) {
            result.setError();
        }
        return result;
    }

    @RequestMapping("favorites")
    @ResponseBody
    public Result favorites(){
        Result result = new Result();

        try {
            Map para = HttpServletUtil.getRequestParameter();
            Long elId = Long.valueOf(para.get("elId").toString());
            englishwordService.favorites(HttpServletUtil.getUserId(),elId);
        } catch (Exception e) {
            result.setError();
        }
        return result;
    }

    @RequestMapping("cancelFavorites")
    @ResponseBody
    public Result cancelFavorites(){
        Result result = new Result();

        try {
            Map para = HttpServletUtil.getRequestParameter();
            Long elId = Long.valueOf(para.get("elId").toString());
            englishwordService.cancelFavorites(HttpServletUtil.getUserId(),elId);
        } catch (Exception e) {
            result.setError();
        }
        return result;
    }

    @RequestMapping("favoritesList")
    @ResponseBody
    public Result favoritesList(){
        Result result = new Result();
        Map para = HttpServletUtil.getRequestParameter();

        try {
            List data = englishwordService.favoritesList(para);
            PageInfo pi = new PageInfo(data);
            result.setData(pi);
        } catch (Exception e) {
            result.setError();
        }
        return result;
    }


//    @RequestMapping("init")
//    @ResponseBody
//    public Result init(){
//        Result result = new Result();
//
//        try {
//            Map data = englishwordService.init();
//            result.setData(data);
//        } catch (Exception e) {
//            result.setError();
//        }
//        return result;
//    }

}
