package com.diao.qiang.service.impl;

import com.diao.qiang.mapper.IszjXqMapper;
import com.diao.qiang.model.IszjXq;
import com.diao.qiang.service.IszjXqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IszjXqServiceImpl implements IszjXqService {

    @Autowired
    private IszjXqMapper iszjXqMapper ;

    @Override
    public int insert(IszjXq record) {
        return iszjXqMapper.insert(record);
    }

    @Override
    public IszjXq getListByjhId(IszjXq record) {
        return iszjXqMapper.getListByjhId(record);
    }
}
