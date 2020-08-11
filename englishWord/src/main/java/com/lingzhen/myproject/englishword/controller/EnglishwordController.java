package com.lingzhen.myproject.englishword.controller;

import com.lingzhen.myproject.englishword.service.EnglishwordService;
import com.lingzhen.myproject.englishword.util.PageData;
import com.lingzhen.myproject.englishword.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("englishWord")
public class EnglishwordController {

    @Autowired
    private EnglishwordService englishwordService;

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
        PageData pd = new PageData(request);
        List<PageData> list = new ArrayList<PageData>();
        try{
            list = englishwordService.getTodayWord(pd);
        }catch(Exception e){
        }
        return list;
    }


    @RequestMapping("listWord")
    @ResponseBody
    public Object listWord(HttpServletRequest request){
        PageData res = new PageData();
        res.put("result","1");
        PageData data = new PageData();
        try{
            //解析参数
            PageData pd = new PageData(request);
            //search
            String search = pd.getString("search").trim();
            if (Tools.notEmpty(search)) {
                pd.put("search",search);
            }
            //order
            String order = pd.getString("order");
            if (Tools.isEmpty(order)) {
                pd.put("order","w.word");
            }
            //dire
            String dire = pd.getString("dire");
            if (Tools.isEmpty(dire)) {
                pd.put("dire","ASC");
            }
            //start
            String start = pd.getString("start");
            if (Tools.isEmpty(start)) {
                pd.put("start","0");
            }
            //len
            String len = pd.getString("len");
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
