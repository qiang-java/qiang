package com.diao.qiang.mapper;

import com.diao.qiang.model.Txffc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TxffcMapper {


    int insert(Txffc record);


    Txffc selectTxffcTop1(Txffc txffc);

}