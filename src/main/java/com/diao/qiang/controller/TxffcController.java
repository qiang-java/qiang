package com.diao.qiang.controller;

import com.alibaba.fastjson.JSONObject;
import com.diao.qiang.config.Constants;
import com.diao.qiang.model.*;
import com.diao.qiang.service.*;
import com.diao.qiang.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/txffc")
public class TxffcController {



    @Autowired
    public TxffcService txffcService;

    @Autowired
    public HmjhService hmjhService;

    @Autowired
    public IszjXqService iszjXqService;

    @Autowired
    public ScjhService scjhService;

    @Autowired
    public JhzhService jhzhService;


    @GetMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public void insertTxffc(){



        SimpleDateFormat df = new SimpleDateFormat("YYYY_MMDD");
        String text = HttpUtils.sendGet(Constants.URL,"{}");
        // 获取开奖号码
        JSONObject jsonObject = JSONObject.parseObject(text.substring(text.indexOf("(") + 1, text.indexOf(")")));
        int current = jsonObject.getInteger("c");
        int[] array = new int[String.valueOf(current).length()];
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.valueOf(String.valueOf(current).substring(i, i + 1));
            sum = sum + array[i];
        }

        int length = array.length;
        // 最高位数
        int height = sum - sum / 10 * 10;
        // 开奖号码
        String lotteryNums = height + "" + array[length - 4] + "" + array[length - 3] + "" + array[length - 2] + "" + array[length - 1];
        // 计算期号
        String lotteryNo = "";
        String dateTime = df.format(new Date());
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
        if (calendar.get(Calendar.SECOND) == 0) {
            return;
        }
        /**
         * 0 - 1440
         */
        if (minute < 10) {
            // 得到指定日期的毫秒数
            long time = new Date().getTime();
            // 要加上的天数转换成毫秒数
            long day = 1*24*60*60*1000;
            // 相加得到新的毫秒数
            time+=day;
            lotteryNo =  new Date(time)+ "1440";
        } else {
            lotteryNo = dateTime + getNums(minute);
        }


        //System.out.println("期号："+lotteryNo+"---- 开奖号：" + lotteryNums);

        try{
            Txffc txffc = new Txffc();
            txffc.setQh(lotteryNo);
            txffc.setKjh(lotteryNums);
            if(txffcService.selectTxffcTop1(txffc) == null){
                System.out.println("开始：" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
                txffcService.insert(new Txffc(lotteryNo,lotteryNums));
                isZj(lotteryNo,lotteryNums);

                insertScjh(lotteryNo,lotteryNums);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //计算期号
    public String getNums(int second) {
        if (second < 10) {
            return "000" + second;
        }
        if (second < 100) {
            return "00" + second;
        }
        if (second < 1000) {
            return "0" + second;
        }
        return String.valueOf(second);
    }

    //查询所有计划 判断是否中奖  修改计划的连中连挂
    public void isZj(String lotteryNo,String lotteryNums){
        List<Hmjh> hmjhList = hmjhService.getList();
        IszjXq iszjXq = new IszjXq();
        //循环所有计划
        for (Hmjh hmjh:hmjhList) {
            if(hmjh.getJh().contains(lotteryNums.substring(2))){
                iszjXq.setIszj("中");
            }else{
                iszjXq.setIszj("挂");
            }

            //添加是否中奖详情

            iszjXq.setExpect(lotteryNo);

            iszjXq.setJhid(hmjh.getId());

            if(iszjXqService.insert(iszjXq) > 0){
                IszjXq zjXq = new IszjXq();
                zjXq.setJhid(hmjh.getId());
                zjXq.setExpect(lotteryNo);

                iszjXq = iszjXqService.getListByjhId(zjXq);

               if (iszjXq.getIszj().equals("中")){
                   hmjh.setDqz(hmjh.getDqz() +1 );
                   hmjh.setDqg(0);
               }else if (iszjXq.getIszj().equals("挂"))
               {
                   hmjh.setDqg(hmjh.getDqg() + 1 );
                   hmjh.setDqz(0);
               }
               hmjhService.updateByPrimaryKeySelective(hmjh);

                Jhzh jhzh = jhzhService.getJhzhList();
                if(jhzh.getZdlg() < hmjh.getDqg()){
                    jhzh.setZdlg(hmjh.getDqg());
                }
                if(jhzh.getZdlz() < hmjh.getDqz()){
                    jhzh.setZdlz(hmjh.getDqz());
                }

                jhzhService.updateJhzh(jhzh);
            }

        }
    }





    //生成计划  修改计划几期中奖数据
    public void insertScjh(String lotteryNo,String lotteryNums){
        List<Scjh> scjhs = scjhService.getScjhList();
        if(scjhs.size() > 0 ){
            for (Scjh jh:scjhs) {
                if(hmjhService.selectByPrimaryKey(jh.getJhid()).getJh().contains(lotteryNums.substring(2))){
                    jh.setIszj("中");
                }
                jh.setJqzj(jh.getJqzj()+1);
                scjhService.updateByPrimaryKeySelective(jh);
            }
        }

        Hmjh hmjh = hmjhService.getListBydqG();
        Scjh scjh = new Scjh();
        scjh.setJhid(hmjh.getId());
        scjh.setJqzj(0);
        scjh.setScqh(lotteryNo);
        scjh.setIszj("等");
        System.out.println("生成第"+lotteryNo+"期计划："+scjhService.insertSelective(scjh));

        System.out.println("结束：" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
    }


}
