package com.diao.qiang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.diao.qiang.config.Constants;
import com.diao.qiang.mapper.TxffcMapper;
import com.diao.qiang.model.Exjh;
import com.diao.qiang.model.Jhzh;
import com.diao.qiang.model.Scjh;
import com.diao.qiang.model.Txffc;
import com.diao.qiang.service.ExjhService;
import com.diao.qiang.service.JhzhService;
import com.diao.qiang.service.ScjhService;
import com.diao.qiang.service.TxffcService;
import com.diao.qiang.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TxffcServiceImpl implements TxffcService {

    private static final Logger logger = LoggerFactory.getLogger(TxffcServiceImpl.class);

    @Autowired
    private TxffcMapper txffcMapper ;

    @Autowired
    private ExjhService exjhService;

    @Autowired
    public ScjhService scjhService;

    @Autowired
    private JhzhService jhzhService;

    @Override
    public int insert(Txffc record) {
        return txffcMapper.insert(record);
    }

    @Override
    public Txffc  selectTxffcTop1(Txffc txffc) {
        return txffcMapper.selectTxffcTop1(txffc);
    }

    @Override
    @Transactional
    public void insertTxffc() {

        logger.info("start time: {}", System.currentTimeMillis());

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
        System.out.println("-----------------------------------------------------------------");

        System.out.println("期号："+lotteryNo+"---- 开奖号：" + lotteryNums);

        try{
            Txffc txffc = new Txffc();
            txffc.setQh(lotteryNo);
            txffc.setKjh(lotteryNums);
            if(this.selectTxffcTop1(txffc) == null){
                System.out.println("开始：" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
                this.insert(new Txffc(lotteryNo,lotteryNums));
                isZj(lotteryNo,lotteryNums);

                insertScjh(lotteryNo,lotteryNums);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("end time:{}", System.currentTimeMillis());
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

    // 查询所有计划 判断是否中奖  修改计划的连中连挂
    public void isZj(String lotteryNo,String lotteryNums){
        System.out.println("期号："+lotteryNo+" ---查询所有计划 修改计划的连中连挂");
        List<Exjh> exjhList = exjhService.getList();
        //循环所有计划
        for (Exjh exjh : exjhList) {
            if(exjh.getJh().contains(lotteryNums.substring(3))){
                exjh.setDqz(exjh.getDqz() +1 );
                exjh.setDqg(0);
            }else{
                exjh.setDqg(exjh.getDqg() + 1 );
                exjh.setDqz(0);
            }
            exjhService.updateByPrimaryKeySelective(exjh);

            Jhzh jhzh = jhzhService.getJhzhList();
            if(jhzh.getZdlg() < exjh.getDqg()){
                jhzh.setZdlg(exjh.getDqg());
            }
            if(jhzh.getZdlz() < exjh.getDqz()){
                jhzh.setZdlz(exjh.getDqz());
            }

            jhzhService.updateJhzh(jhzh);
        }
    }





    //生成计划  修改计划几期中奖数据
    public void insertScjh(String lotteryNo,String lotteryNums){
        List<Scjh> scjhs = scjhService.getScjhList();
        Exjh exjh = exjhService.getListBydqG();
        Scjh scjh = new Scjh();
        scjh.setJhid(exjh.getId());
        scjh.setJqzj(0);
        scjh.setScqh(lotteryNo);
        scjh.setIszj("等");

        if(scjhs.size() > 0 ){
            for (Scjh jh:scjhs) {
                if(exjhService.selectByPrimaryKey(jh.getJhid()).getJh().contains(lotteryNums.substring(3))){
                    jh.setIszj("中");
                    if(scjhService.insertSelective(scjh) > 0 ){
                        System.out.println("计划第"+ (Integer.valueOf(jh.getJqzj())+1) + "期中，生成"+lotteryNo+"的计划");
                    }
                }else{
                    System.out.println("计划进行中（"+ (Integer.valueOf(jh.getJqzj())+1) + "） ");
                }
                jh.setJqzj(jh.getJqzj()+1);
                scjhService.updateByPrimaryKeySelective(jh);
            }
        }else{
            if(scjhService.insertSelective(scjh) > 0 ){
                System.out.println("期号："+lotteryNo+" ---生成计划");
            }
        }
        System.out.println("结束：" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
        System.out.println("-----------------------------------------------------------------");

    }

}
