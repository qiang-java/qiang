package com.diao.qiang.service;

import com.diao.qiang.model.Jhzh;

public interface JhzhService {
    int insertJhzh(Jhzh jhzh);
    int updateJhzh(Jhzh jhzh);
    Jhzh getJhzhList();
}
