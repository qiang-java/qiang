package com.diao.qiang.mapper;

import com.diao.qiang.model.Scjh;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScjhMapper {

    List<Scjh> getScjhList();


    int insertSelective(Scjh record);

    int updateByPrimaryKeySelective(Scjh record);

}