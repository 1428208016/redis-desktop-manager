package com.lingzhen.myproject.lifefolder.mapper.englishword;

import java.util.List;
import java.util.Map;

public interface UserTimeWordMapper {

    List findByUserAndTime(Map map);

    int save(Map map);
}
