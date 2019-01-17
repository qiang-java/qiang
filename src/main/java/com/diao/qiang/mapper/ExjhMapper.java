package com.diao.qiang.mapper;

import com.diao.qiang.model.Exjh;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExjhMapper {

    Exjh getListBydqG();

    List<Exjh> getList();

    Exjh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exjh record);

}