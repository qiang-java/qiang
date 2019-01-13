package com.diao.qiang.controller;

import com.alibaba.fastjson.JSONObject;
import com.diao.qiang.config.Constants;
import com.diao.qiang.model.*;
import com.diao.qiang.service.*;
import com.diao.qiang.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/txffc")
public class TxffcController {



    @Autowired
    public TxffcService txffcService;

    @Autowired
    public HmjhService hmjhService;



    @Autowired
    public JhzhService jhzhService;


    @GetMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public void insertTxffc(){

    }



}
