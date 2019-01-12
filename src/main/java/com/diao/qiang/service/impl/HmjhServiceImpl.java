package com.diao.qiang.service.impl;

import com.diao.qiang.mapper.HmjhMapper;
import com.diao.qiang.model.Hmjh;
import com.diao.qiang.service.HmjhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HmjhServiceImpl implements HmjhService {

    @Autowired
    private HmjhMapper hmjhMapper ;


    @Override
    public List<Hmjh> getList() {
        return hmjhMapper.getList();
    }

    @Override
    public Hmjh getListBydqG() {
        return hmjhMapper.getListBydqG();
    }

    @Override
    public int updateByPrimaryKeySelective(Hmjh record) {
        return hmjhMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Hmjh selectByPrimaryKey(Integer id) {
        return hmjhMapper.selectByPrimaryKey(id);
    }
}
