package com.diao.qiang.service.impl;

import com.diao.qiang.mapper.ScjhMapper;
import com.diao.qiang.model.Scjh;
import com.diao.qiang.service.ScjhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScjhServiceImpl implements ScjhService {

    @Autowired
    private ScjhMapper scjhMapper;

    @Override
    public List<Scjh> getScjhList() {
        return scjhMapper.getScjhList();
    }

    @Override
    public int insertSelective(Scjh record) {
        return scjhMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Scjh record) {
        return scjhMapper.updateByPrimaryKeySelective(record);
    }
}
