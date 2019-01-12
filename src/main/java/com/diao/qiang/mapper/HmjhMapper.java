package com.diao.qiang.mapper;

import com.diao.qiang.model.Hmjh;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HmjhMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hmjh record);

    Hmjh getListBydqG();

    List<Hmjh> getList();

    int insertSelective(Hmjh record);

    Hmjh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hmjh record);

    int updateByPrimaryKey(Hmjh record);
}