package com.diao.qiang.mapper;

import com.diao.qiang.model.Jhzh;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JhzhMapper {
    Jhzh getJhzhList();
    int updateByPrimaryKeySelective(Jhzh record);
}