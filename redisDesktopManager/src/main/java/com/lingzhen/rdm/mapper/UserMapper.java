package com.lingzhen.rdm.mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int save(Map map);

    List validUserNameAndPhoneNumber(Map map);

    Map findByUserName(Map map);

    Map findById(Long userId);

    int insertAccessLog(Map map);

    Map validMobile(String mobile);
}
