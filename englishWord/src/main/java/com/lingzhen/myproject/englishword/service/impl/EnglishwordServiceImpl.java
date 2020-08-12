package com.lingzhen.myproject.englishword.service.impl;

import com.lingzhen.myproject.common.util.UuidUtil;
import com.lingzhen.myproject.englishword.dao.Dao;
import com.lingzhen.myproject.englishword.service.EnglishwordService;
import com.lingzhen.myproject.englishword.service.SysConfigService;
import com.lingzhen.myproject.englishword.util.DateUtil;
import com.lingzhen.myproject.englishword.util.PageData;
import com.lingzhen.myproject.englishword.util.Tools;
import com.lingzhen.myproject.englishword.util.baidufanyi.TransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("englishWordService")
public class EnglishwordServiceImpl implements EnglishwordService {

    @Resource(name = "daoImpl")
    private Dao dao;


    @Autowired
    private SysConfigService sysConfigService;

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
        PageData pd = new PageData();
        while((word = br.readLine()) != null){
            for(String temp : word.split(" ")){
                if(Tools.notEmpty(temp)) {
                    PageData data = (PageData) dao.queryOne("wordMapper.findByWord", temp);
                    if (null != data) {
                        if ("0".equals(data.getString("translate"))) {
                            //翻译单词并更新
                            String zh = TransUtil.getTransResult(temp, TransUtil.EN, TransUtil.ZH);
                            PageData wordObj = new PageData();
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
        PageData pd = new PageData();
        while((word = br.readLine()) != null){
            if(Tools.notEmpty(word)){
                en = word.substring(0,word.indexOf("："));
                chinese = word.substring(word.indexOf("：")+1,word.length());
                PageData data = (PageData) dao.queryOne("wordHtmlMapper.findByWord", en);
                if (null != data) {
                    if ("0".equals(data.getString("translate"))) {
                        //翻译单词并更新
                        chinese = TransUtil.getTransResult(en, TransUtil.EN, TransUtil.ZH);
                        PageData wordObj = new PageData();
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
    public void save(PageData pd){
        PageData save = new PageData();
        try {
            save.put("id", UuidUtil.getUuid());
            save.put("word", pd.get("word"));
            save.put("chinese", pd.get("chinese"));
            save.put("translate", Tools.notEmpty(pd.getString("chinese")) ? "1" : "0");
            String time = DateUtil.getMillisecondTime();
            save.put("creatorId", "1");
            save.put("createTime", time);
            save.put("modifyId", "1");
            save.put("lastModified", time);
            dao.save("wordMapper.save", save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save_html(PageData pd){
        PageData save = new PageData();
        try {
            save.put("id", UuidUtil.getUuid());
            save.put("word", pd.get("word"));
            save.put("chinese", pd.get("chinese"));
            save.put("translate", Tools.notEmpty(pd.getString("chinese")) ? "1" : "0");
            String time = DateUtil.getMillisecondTime();
            save.put("creatorId", "1");
            save.put("createTime", time);
            save.put("modifyId", "1");
            save.put("lastModified", time);
            dao.save("wordHtmlMapper.save", save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(PageData pd){
        try {
            PageData update = new PageData();
            update.putAll(pd);
            update.put("lastModified",DateUtil.getMillisecondTime());
            dao.update("wordMapper.updateByWord", update);
        }catch (Exception e){

        }
    }

    public void update_html(PageData pd){
        try {
            PageData update = new PageData();
            update.putAll(pd);
            update.put("lastModified",DateUtil.getMillisecondTime());
            dao.update("wordHtmlMapper.updateByWord", update);
        }catch (Exception e){

        }
    }

    @Override
    public List<PageData> getTodayWord(PageData pd) throws Exception {
        String userId = pd.getString("userId");
        String time = pd.getString("time");
        String tab = pd.getString("tab");
        userId = Tools.notEmpty(userId)?userId:"1";
        time = Tools.notEmpty(time)?time:DateUtil.getDay();
        tab = Tools.notEmpty(tab)?tab:"ew_word";
        //通过用户、日期获取单词
        PageData sel = new PageData();
        sel.put("userId",userId);
        sel.put("time",time);
        sel.put("tab",tab);
        List<PageData> list = (List<PageData>) dao.queryList("userTimeWordMapper.findByUserAndTime",sel);
        if(null == list || list.size() <= 0){
            list = new ArrayList<PageData>();
            //获取配置的每天单词数
            String num = sysConfigService.findValueByCode("wordsNumberOfDay");
            int nums = Tools.notEmpty(num)?Integer.valueOf(num):6;
            //通过用户随机获取单词
            PageData data = null;
            for(int i = 1;i<=nums;i++){
                //获取最大行数
                int max = (int) dao.queryOne("wordMapper.findUserTabMaxRow",sel);
                sel.put("pre",(int)(Math.random()*(max)));
                data = (PageData) dao.queryOne("wordMapper.findRandomWord",sel);
                PageData saveData = new PageData();
                saveData.put("userId",userId);
                saveData.put("time",DateUtil.getDay());
                saveData.put("creatorId","1");
                saveData.put("createTime",DateUtil.getTime());
                saveData.put("id",UuidUtil.getUuid());
                saveData.put("word",data.get("word"));
                list.add(data);
                dao.save("userTimeWordMapper.save",saveData);
            }
        }
        return list;
    }

    @Override
    public PageData listWord(PageData pd) throws Exception {
        PageData data = new PageData();
        data.put("list",dao.queryList("wordMapper.listWordlistPage",pd));
        data.put("count",pd.get("sql_count"));
        data.put("start",pd.get("start"));
        data.put("len",pd.get("len"));
        return data;
    }


}
