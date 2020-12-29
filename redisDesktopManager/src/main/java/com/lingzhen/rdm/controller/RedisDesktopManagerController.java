package com.lingzhen.rdm.controller;

import com.lingzhen.rdm.pojo.Result;
import com.lingzhen.rdm.service.RedisDesktopManagerService;
import com.lingzhen.rdm.util.HttpServletUtil;
import com.lingzhen.rdm.util.UuidUtil;
import com.lingzhen.rdm.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("redisDesktopManager")
public class RedisDesktopManagerController {

    @Autowired
    private RedisDesktopManagerService redisDesktopManagerService;

    @RequestMapping("getNewUUID")
    @ResponseBody
    public Result getNewUUID(){
        Result result = new Result();

        try {
            result.setData(UuidUtil.getUuid());
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("testConnection")
    @ResponseBody
    public Result testConnection(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        try {
            if (VerifyUtil.stringTrimIsEmpty(param.get("connectionName"))) {
                return result.setErrorReturn("连接名称为空");
            }
            if (VerifyUtil.stringTrimIsEmpty(param.get("address"))) {
                return result.setErrorReturn("连接地址为空");
            }
            if (VerifyUtil.stringTrimIsEmpty(param.get("port"))) {
                return result.setErrorReturn("连接端口为空");
            }
            result = redisDesktopManagerService.testConnection(param);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("connectionRedis")
    @ResponseBody
    public Result connectionRedis(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        try {
            if (VerifyUtil.stringTrimIsEmpty(param.get("connKey"))) {
                return result.setErrorReturn("connKey为空");
            }
            if (VerifyUtil.stringTrimIsEmpty(param.get("obj"))) {
                return result.setErrorReturn("obj为空");
            }


            String connKey = param.get("connKey").toString();
            String obj = param.get("obj").toString();
            result = redisDesktopManagerService.connectionRedis(connKey,obj);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("scan")
    @ResponseBody
    public Result scan(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connStr为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }

        try {

            String key = "";
            if (null != param.get("key")) {
                key = param.get("key").toString();
            }
            String connStr = param.get("connStr").toString();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString());
            List<String> data = redisDesktopManagerService.scan(connStr,dbIndex,key);
            result.setData(data);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("loadKeyValue")
    @ResponseBody
    public Result loadKeyValue(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connStr为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }

        try {

            String connStr = param.get("connStr").toString();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString());
            String key = param.get("key").toString();
            result = redisDesktopManagerService.loadKeyValue(connStr,dbIndex,key);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("addNewKey")
    @ResponseBody
    public Result addNewKey(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connStr为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("type"))) {
            return result.setErrorReturn("type为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("value"))) {
            return result.setErrorReturn("value为空");
        }

        try {
            String connStr = param.get("connStr").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String type = param.get("type").toString().trim();
            Object value = param.get("value");
            Long ttl = new Long(0);
            if (!VerifyUtil.stringTrimIsEmpty(param.get("ttl"))) {
                ttl = new Long(param.get("ttl").toString());
            }
            result = redisDesktopManagerService.addNewKey(connStr,dbIndex,key,type,value,ttl);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("deleteKey")
    @ResponseBody
    public Result deleteKey(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connObj为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }

        try {
            String connStr = param.get("connStr").toString();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            result = redisDesktopManagerService.deleteKey(connStr,dbIndex,key);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("editKey")
    @ResponseBody
    public Result editKey(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connStr为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("type"))) {
            return result.setErrorReturn("type为空");
        }

        try {
            String connStr = param.get("connStr").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String type = param.get("type").toString().trim();
            Object value = param.get("value");
            Long ttl = new Long(0);
            if (!VerifyUtil.stringTrimIsEmpty(param.get("ttl"))) {
                ttl = new Long(param.get("ttl").toString());
            }
            result = redisDesktopManagerService.editKey(connStr,dbIndex,key,type,value,ttl);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("renameKey")
    @ResponseBody
    public Result renameKey(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connStr为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("newKey"))) {
            return result.setErrorReturn("newKey为空");
        }

        try {
            String connStr = param.get("connStr").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String newKey = param.get("newKey").toString().trim();
            result = redisDesktopManagerService.renameKey(connStr,dbIndex,key,newKey);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("setTTL")
    @ResponseBody
    public Result setTTL(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connStr为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("ttl"))) {
            return result.setErrorReturn("ttl为空");
        }

        try {

            String connStr = param.get("connStr").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            Integer ttl = Integer.valueOf(param.get("ttl").toString());
            result = redisDesktopManagerService.setTTL(connStr,dbIndex,key,ttl);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("kvScan")
    @ResponseBody
    public Result kvScan(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connStr"))) {
            return result.setErrorReturn("connStr为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }

        try {

            String connStr = param.get("connStr").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String type = param.get("type").toString().trim();
            String scanKey = param.get("scanKey").toString();
            result = redisDesktopManagerService.kvScan(connStr,dbIndex,key,type,scanKey);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }







}
