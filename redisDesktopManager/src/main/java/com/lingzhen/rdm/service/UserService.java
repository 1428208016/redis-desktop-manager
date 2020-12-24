package com.lingzhen.rdm.service;

import java.util.Map;

public interface UserService {

    Map findById(Long userId);

    int insertAccessLog(String method, String parameter);

    int initAccount(Long userId);
}
