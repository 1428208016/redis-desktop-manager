package com.lingzhen.myproject.lifefolder.service.englishword.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.myproject.common.util.UuidUtil;
import com.lingzhen.myproject.lifefolder.mapper.englishword.EnglishWordMapper;
import com.lingzhen.myproject.lifefolder.mapper.englishword.UserTimeWordMapper;
import com.lingzhen.myproject.lifefolder.service.englishword.EnglishWordService;
import com.lingzhen.myproject.lifefolder.service.englishword.SysConfigService;
import com.lingzhen.myproject.lifefolder.util.Tools;
import com.lingzhen.myproject.lifefolder.util.baidufanyi.TransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("englishWordService")
public class EnglishWordServiceImpl implements EnglishWordService {

    private EnglishWordMapper englishWordMapper;

    @Autowired
    private SysConfigService sysConfigService;

    private UserTimeWordMapper userTimeWordMapper;

    @Override
    public void init() throws Exception {
        //读取文件
        File file = new File("src/main/word/words.txt");
        if(file.length() <= 0){
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String word = "";
        Map pd = new HashMap();
        while((word = br.readLine()) != null){
            for(String temp : word.split(" ")){
                if(Tools.notEmpty(temp)) {
                    Map data = englishWordMapper.findByWord(temp);
                    if (null != data) {
                        if ("0".equals(data.get("translate").toString())) {
                            //翻译单词并更新
                            String zh = TransUtil.getTransResult(temp, TransUtil.EN, TransUtil.ZH);
                            Map wordObj = new HashMap();
                            wordObj.put("word", temp);
                            wordObj.put("chinese", zh);
                            wordObj.put("translate", Tools.notEmpty(zh) ? "1" : "0");
                            this.update(wordObj);
                        }
                    } else {
                        //翻译并载入单词
                        String zh = TransUtil.getTransResult(temp, TransUtil.EN, TransUtil.ZH);
                        pd.put("word",temp);
                        pd.put("chinese",zh);
                        this.save(pd);
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                }
            }
        }
    }

    @Override
    public void init_html() throws Exception {
        //读取文件
        File file = new File("src/main/word/words-html.txt");
        if(file.length() <= 0){
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String word = "";
        String chinese = "";
        String en = "";
        Map pd = new HashMap();
        while((word = br.readLine()) != null){
            if(Tools.notEmpty(word)){
                en = word.substring(0,word.indexOf("："));
                chinese = word.substring(word.indexOf("：")+1,word.length());
                Map data = englishWordMapper.findByWord(en);
                if (null != data) {
                    if ("0".equals(data.get("translate").toString())) {
                        //翻译单词并更新
                        chinese = TransUtil.getTransResult(en, TransUtil.EN, TransUtil.ZH);
                        Map wordObj = new HashMap();
                        wordObj.put("word", en);
                        wordObj.put("chinese", chinese);
                        wordObj.put("translate", Tools.notEmpty(en) ? "1" : "0");
                        this.update_html(wordObj);
                    }
                }else{
                    pd.put("word",en);
                    pd.put("chinese",chinese);
                    this.save_html(pd);
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            }
        }
    }

    @Override
    public void save(Map pd){
        Map save = new HashMap();
        try {
            save.put("id", UuidUtil.getUuid());
            save.put("word", pd.get("word"));
            save.put("chinese", pd.get("chinese"));
            save.put("translate", Tools.notEmpty(pd.get("chinese").toString()) ? "1" : "0");
            String time = DateUtil.getMillisecondTime();
            save.put("creatorId", "1");
            save.put("createTime", time);
            save.put("modifyId", "1");
            save.put("lastModified", time);
            englishWordMapper.save(save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save_html(Map pd){
        Map save = new HashMap();
        try {
            save.put("id", UuidUtil.getUuid());
            save.put("word", pd.get("word"));
            save.put("chinese", pd.get("chinese"));
            save.put("translate", Tools.notEmpty(pd.get("chinese").toString()) ? "1" : "0");
            String time = DateUtil.getMillisecondTime();
            save.put("creatorId", "1");
            save.put("createTime", time);
            save.put("modifyId", "1");
            save.put("lastModified", time);
            englishWordMapper.saveHtml(save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Map pd){
        try {
            Map update = new HashMap();
            update.putAll(pd);
            update.put("lastModified",DateUtil.getMillisecondTime());
            englishWordMapper.updateByWord(update);
        }catch (Exception e){

        }
    }

    public void update_html(Map pd){
        try {
            Map update = new HashMap();
            update.putAll(pd);
            update.put("lastModified",DateUtil.getMillisecondTime());
            englishWordMapper.updateHtmlByWord(update);
        }catch (Exception e){

        }
    }

    @Override
    public List<Map> getTodayWord(Map pd) throws Exception {
        String userId = pd.get("userId").toString();
        String time = pd.get("time").toString();
        String tab = pd.get("tab").toString();
        userId = Tools.notEmpty(userId)?userId:"1";
        time = Tools.notEmpty(time)?time:DateUtil.getDay()+"";
        tab = Tools.notEmpty(tab)?tab:"ew_word";
        //通过用户、日期获取单词
        Map sel = new HashMap();
        sel.put("userId",userId);
        sel.put("time",time);
        sel.put("tab",tab);

        List<Map> list = userTimeWordMapper.findByUserAndTime(sel);
        if(null == list || list.size() <= 0){
            list = new ArrayList<Map>();
            //获取配置的每天单词数
            String num = sysConfigService.findValueByCode("wordsNumberOfDay");
            int nums = Tools.notEmpty(num)?Integer.valueOf(num):6;
            //通过用户随机获取单词
            Map data = null;
            for(int i = 1;i<=nums;i++){
                //获取最大行数
                int max = englishWordMapper.findUserTabMaxRow(sel);
                sel.put("pre",(int)(Math.random()*(max)));
                data = englishWordMapper.findRandomWord(sel);
                Map saveData = new HashMap();
                saveData.put("userId",userId);
                saveData.put("time",DateUtil.getDay());
                saveData.put("creatorId","1");
                saveData.put("createTime",DateUtil.getTime());
                saveData.put("id",UuidUtil.getUuid());
                saveData.put("word",data.get("word"));
                list.add(data);
                userTimeWordMapper.save(saveData);
            }
        }
        return list;
    }

    @Override
    public Map listWord(Map pd) throws Exception {
        Map data = new HashMap();
        data.put("list",englishWordMapper.listWordlistPage(pd));
        data.put("count",pd.get("sql_count"));
        data.put("start",pd.get("start"));
        data.put("len",pd.get("len"));
        return data;
    }


}
