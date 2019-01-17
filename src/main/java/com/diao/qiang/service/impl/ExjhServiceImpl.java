package com.diao.qiang.service.impl;

import com.diao.qiang.mapper.ExjhMapper;
import com.diao.qiang.model.Exjh;
import com.diao.qiang.service.ExjhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExjhServiceImpl implements ExjhService {

    @Autowired
    private ExjhMapper exjhMapper;


    @Override
    public List<Exjh> getList() {
        return exjhMapper.getList();
    }

    @Override
    public Exjh getListBydqG() {
        return exjhMapper.getListBydqG();
    }

    @Override
    public int updateByPrimaryKeySelective(Exjh record) {
        return exjhMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Exjh selectByPrimaryKey(Integer id) {
        return exjhMapper.selectByPrimaryKey(id);
    }
}
