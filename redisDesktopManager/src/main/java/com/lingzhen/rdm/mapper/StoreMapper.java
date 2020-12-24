package com.lingzhen.rdm.mapper;

import java.util.List;
import java.util.Map;

public interface StoreMapper {

    List myProjectAll(Long userId);

    Map findMyProject(Map map);

    List projectList(Map map);

    int addUserProject(Map map);

    Map findById(String projectId);
}
