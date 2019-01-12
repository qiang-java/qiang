package com.diao.qiang.mapper;

import com.diao.qiang.model.Hmjh;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HmjhMapper {

    Hmjh getListBydqG();

    List<Hmjh> getList();

    Hmjh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hmjh record);

}