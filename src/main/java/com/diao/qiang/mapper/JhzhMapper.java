package com.diao.qiang.mapper;

import com.diao.qiang.model.Jhzh;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JhzhMapper {
    int deleteByPrimaryKey(Integer id);

    Jhzh getJhzhList();

    int insert(Jhzh record);

    int insertSelective(Jhzh record);

    Jhzh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jhzh record);

    int updateByPrimaryKey(Jhzh record);
}