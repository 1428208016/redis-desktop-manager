package com.lingzhen.myproject.lifefolder.mapper;

import java.util.List;
import java.util.Map;

public interface StoreMapper {

    List myProject(Long userId);

    List projectList(Map map);

    int addUserProject(Map map);

    Map findById(String projectId);
}
