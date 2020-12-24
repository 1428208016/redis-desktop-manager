package com.lingzhen.rdm.mapper;

import java.util.Map;

public interface FileMapper {

    int insertFile(Map map);

    Map findById(String key);
}
