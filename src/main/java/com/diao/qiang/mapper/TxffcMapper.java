package com.diao.qiang.mapper;

import com.diao.qiang.model.Txffc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TxffcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Txffc record);

    int insertSelective(Txffc record);

    Txffc selectByPrimaryKey(Integer id);

    Txffc selectTxffcTop1(Txffc txffc);

    int updateByPrimaryKeySelective(Txffc record);

    int updateByPrimaryKey(Txffc record);
}