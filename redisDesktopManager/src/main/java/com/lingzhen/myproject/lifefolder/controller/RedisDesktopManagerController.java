package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.RedisDesktopManagerService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import com.lingzhen.myproject.lifefolder.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("redisDesktopManager")
public class RedisDesktopManagerController {

    @Autowired
    private RedisDesktopManagerService redisDesktopManagerService;

    @RequestMapping("saveOrEdit")
    @ResponseBody
    public Result saveOrEdit(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        if (VerifyUtil.stringTrimIsEmpty(param.get("connectionName"))) {
            return result.setErrorReturn("连接名称为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("address"))) {
            return result.setErrorReturn("连接地址为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("port"))) {
            return result.setErrorReturn("连接端口为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("auth"))) {
            return result.setErrorReturn("连接密码为空");
        }

        try {
            String address = param.get("address").toString().trim().toLowerCase();
            if ("localhost".equals(address) || "127.0.0.1".equals(address)) {
                return result.setErrorReturn("不支持该连接地址");
            }
            int i = redisDesktopManagerService.saveOrEdit(param);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("findConnectionById")
    @ResponseBody
    public Result findConnectionById(){
        Result result = new Result();

        try {
            Map param = HttpServletUtil.getRequestParameter();
            String csId = param.get("csId").toString();
            Map data = redisDesktopManagerService.findConnectionById(csId);
            result.setData(data);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("findConnectionByUserId")
    @ResponseBody
    public Result findConnectionByUserId(){
        Result result = new Result();

        try {
            Long userId = HttpServletUtil.getUserId();
            List data = redisDesktopManagerService.findConnectionByUserId(userId);
            result.setData(data);
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
            if (VerifyUtil.stringTrimIsEmpty(param.get("auth"))) {
                return result.setErrorReturn("连接密码为空");
            }
            result = redisDesktopManagerService.testConnection(param);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Result delete(){
        Result result = new Result();
        Map param = HttpServletUtil.getRequestParameter();

        try {
            if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
                return result.setErrorReturn("csId为空");
            }
            String csId = param.get("csId").toString();
            redisDesktopManagerService.delete(csId);
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
            if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
                return result.setErrorReturn("csId为空");
            }
            String csId = param.get("csId").toString();
            result = redisDesktopManagerService.connectionRedis(csId);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("connectionKey"))) {
            return result.setErrorReturn("连接key为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }

        try {

            String key = "";
            if (null != param.get("key")) {
                key = param.get("key").toString();
            }
            String connectionKey = param.get("connectionKey").toString();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString());
            List<String> data = redisDesktopManagerService.scan(connectionKey,dbIndex,key);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("connectionKey"))) {
            return result.setErrorReturn("连接key为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }

        try {

            String connectionKey = param.get("connectionKey").toString();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString());
            String key = param.get("key").toString();
            result = redisDesktopManagerService.loadKeyValue(connectionKey,dbIndex,key);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
            return result.setErrorReturn("csId为空");
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
            String csId = param.get("csId").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String type = param.get("type").toString().trim();
            Object value = param.get("value");
            Long ttl = new Long(0);
            if (!VerifyUtil.stringTrimIsEmpty(param.get("ttl"))) {
                ttl = new Long(param.get("ttl").toString());
            }
            result = redisDesktopManagerService.addNewKey(csId,dbIndex,key,type,value,ttl);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
            return result.setErrorReturn("csId为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }

        try {
            String csId = param.get("csId").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            result = redisDesktopManagerService.deleteKey(csId,dbIndex,key);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
            return result.setErrorReturn("csId为空");
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
            String csId = param.get("csId").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String type = param.get("type").toString().trim();
            Object value = param.get("value");
            Long ttl = new Long(0);
            if (!VerifyUtil.stringTrimIsEmpty(param.get("ttl"))) {
                ttl = new Long(param.get("ttl").toString());
            }
            result = redisDesktopManagerService.editKey(csId,dbIndex,key,type,value,ttl);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
            return result.setErrorReturn("csId为空");
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
            String csId = param.get("csId").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String newKey = param.get("newKey").toString().trim();
            result = redisDesktopManagerService.renameKey(csId,dbIndex,key,newKey);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
            return result.setErrorReturn("csId为空");
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

            String csId = param.get("csId").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            Integer ttl = Integer.valueOf(param.get("ttl").toString());
            result = redisDesktopManagerService.setTTL(csId,dbIndex,key,ttl);
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

        if (VerifyUtil.stringTrimIsEmpty(param.get("csId"))) {
            return result.setErrorReturn("csId为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("dbIndex"))) {
            return result.setErrorReturn("dbIndex为空");
        }
        if (VerifyUtil.stringTrimIsEmpty(param.get("key"))) {
            return result.setErrorReturn("key为空");
        }

        try {

            String csId = param.get("csId").toString().trim();
            Integer dbIndex = Integer.valueOf(param.get("dbIndex").toString().trim());
            String key = param.get("key").toString().trim();
            String type = param.get("type").toString().trim();
            String scanKey = param.get("scanKey").toString();
            result = redisDesktopManagerService.kvScan(csId,dbIndex,key,type,scanKey);
        } catch (Exception e) {
            result.setError(e);
        }
        return result;
    }







}
