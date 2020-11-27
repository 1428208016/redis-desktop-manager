package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;

import java.util.List;
import java.util.Map;

public interface StoreService {

    List myProjectAll();

    List projectList(Map map);

    Result buyProject(String projectId);

    Result buyProject(Long userId, String projectId);

}
