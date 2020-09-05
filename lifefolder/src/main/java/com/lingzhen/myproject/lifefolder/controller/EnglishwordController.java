package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.lifefolder.service.englishword.EnglishWordService;
import com.lingzhen.myproject.lifefolder.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("englishWord")
public class EnglishwordController {

    @Autowired
    private EnglishWordService englishwordService;

    @RequestMapping("init")
    public String init(){
        try{
            englishwordService.init();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error:"+e.getMessage();
        }
        return "success";
    }

    @RequestMapping("init_html")
    public String init_html(){
        try{
            englishwordService.init_html();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error:"+e.getMessage();
        }
        return "success";
    }

    @RequestMapping("getTodayWord")
    @ResponseBody
    public Object getTodayWord(HttpServletRequest request){
//        Map pd = new HashMap(request);
        Map pd = new HashMap();
        List<Map> list = new ArrayList<Map>();
        try{
            list = englishwordService.getTodayWord(pd);
        }catch(Exception e){
        }
        return list;
    }


    @RequestMapping("listWord")
    @ResponseBody
    public Object listWord(HttpServletRequest request){
        Map res = new HashMap();
        res.put("result","1");
        Map data = new HashMap();
        try{
            //解析参数
//            Map pd = new HashMap(request);
            Map pd = new HashMap();
            //search
            String search = pd.get("search").toString().trim();
            if (Tools.notEmpty(search)) {
                pd.put("search",search);
            }
            //order
            String order = pd.get("order").toString();
            if (Tools.isEmpty(order)) {
                pd.put("order","w.word");
            }
            //dire
            String dire = pd.get("dire").toString();
            if (Tools.isEmpty(dire)) {
                pd.put("dire","ASC");
            }
            //start
            String start = pd.get("start").toString();
            if (Tools.isEmpty(start)) {
                pd.put("start","0");
            }
            //len
            String len = pd.get("len").toString();
            if (Tools.isEmpty(len)) {
                pd.put("len","10");
            }
            //业务处理
            data = englishwordService.listWord(pd);
        }catch (Exception e){
            res.put("result","0");
            res.put("msg",e.getMessage());
        }
        res.put("data",data);
        return res;
    }
}
