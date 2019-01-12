package com.diao.qiang.service;

import com.diao.qiang.model.Txffc;

public interface TxffcService {
    int insert (Txffc record);
    Txffc selectTxffcTop1 (Txffc txffc);
}
