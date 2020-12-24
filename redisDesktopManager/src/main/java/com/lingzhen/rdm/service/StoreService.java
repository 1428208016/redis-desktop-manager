package com.lingzhen.rdm.service;

import com.lingzhen.rdm.pojo.Result;

import java.util.List;
import java.util.Map;

public interface StoreService {

    List myProjectAll();

    List projectList(Map map);

    Result buyProject(String projectId);

    Result buyProject(Long userId, String projectId);

}
