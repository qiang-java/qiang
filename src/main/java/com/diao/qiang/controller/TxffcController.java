package com.diao.qiang.controller;

import com.diao.qiang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/txffc")
public class TxffcController {



    @Autowired
    public TxffcService txffcService;

    @Autowired
    public ExjhService exjhService;



    @Autowired
    public JhzhService jhzhService;


    @GetMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public void insertTxffc(){

    }



}
