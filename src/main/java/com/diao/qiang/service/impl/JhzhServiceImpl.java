package com.diao.qiang.service.impl;

import com.diao.qiang.mapper.JhzhMapper;
import com.diao.qiang.model.Jhzh;
import com.diao.qiang.service.JhzhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JhzhServiceImpl implements JhzhService {

    @Autowired
    private JhzhMapper jhzhMapper;


    @Override
    public int updateJhzh(Jhzh jhzh) {
        return jhzhMapper.updateByPrimaryKeySelective(jhzh);
    }

    @Override
    public Jhzh getJhzhList() {
        return jhzhMapper.getJhzhList();
    }
}
