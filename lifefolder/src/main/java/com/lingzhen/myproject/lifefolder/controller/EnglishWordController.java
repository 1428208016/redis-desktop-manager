package com.lingzhen.myproject.lifefolder.controller;

import com.lingzhen.myproject.lifefolder.service.EnglishWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("englishWord")
public class EnglishWordController {

    @Autowired
    private EnglishWordService englishwordService;


}
