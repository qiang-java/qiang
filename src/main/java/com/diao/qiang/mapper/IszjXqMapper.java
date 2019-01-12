package com.diao.qiang.mapper;

import com.diao.qiang.model.IszjXq;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IszjXqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IszjXq record);

    int insertSelective(IszjXq record);

    IszjXq getListByjhId(IszjXq record);

    IszjXq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IszjXq record);

    int updateByPrimaryKey(IszjXq record);
}