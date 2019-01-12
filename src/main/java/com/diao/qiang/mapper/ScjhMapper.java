package com.diao.qiang.mapper;

import com.diao.qiang.model.Scjh;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScjhMapper {
    int deleteByPrimaryKey(Integer id);

    List<Scjh> getScjhList();

    int insert(Scjh record);

    int insertSelective(Scjh record);

    Scjh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scjh record);

    int updateByPrimaryKey(Scjh record);
}