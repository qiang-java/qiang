package com.diao.qiang.service.impl;

import com.diao.qiang.mapper.TxffcMapper;
import com.diao.qiang.model.Txffc;
import com.diao.qiang.service.TxffcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TxffcServiceImpl implements TxffcService {

    @Autowired
    private TxffcMapper txffcMapper ;

    @Override
    public int insert(Txffc record) {
        return txffcMapper.insert(record);
    }

    @Override
    public Txffc  selectTxffcTop1(Txffc txffc) {
        return txffcMapper.selectTxffcTop1(txffc);
    }
}
