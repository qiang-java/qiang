package com.diao.qiang.model;

import java.util.Date;
import lombok.Data;

@Data
public class Txffc {
    private Integer id;

    private String qh;

    private String kjh;

    private Date kjdate;

    public Txffc(){
    }


    public Txffc(String qh,String kjh){
        this.qh = qh;
        this.kjh = kjh;
    }
}