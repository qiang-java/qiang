package com.diao.qiang.service;


import com.diao.qiang.model.Exjh;

import java.util.List;

public interface ExjhService {
    List<Exjh> getList();

    Exjh getListBydqG();

    int updateByPrimaryKeySelective(Exjh record);

    Exjh selectByPrimaryKey(Integer id);
}
