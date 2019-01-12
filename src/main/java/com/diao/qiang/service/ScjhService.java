package com.diao.qiang.service;

import com.diao.qiang.model.Scjh;

import java.util.List;


public interface ScjhService {

    List<Scjh> getScjhList();

    int insertSelective(Scjh record);

    int updateByPrimaryKeySelective(Scjh record);
}
