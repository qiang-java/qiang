package com.diao.qiang.service;


import com.diao.qiang.model.Hmjh;

import java.util.List;

public interface HmjhService {
    List<Hmjh> getList();

    Hmjh getListBydqG();

    int updateByPrimaryKeySelective(Hmjh record);

    Hmjh selectByPrimaryKey(Integer id);
}
