package com.yzozhi;

import java.util.List;

import com.alibaba.excel.EasyExcelFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelRegex {
    public static void main(String[] args) {
        // 定义文件
        String fileRead = "2017.xlsx";
        String fileResult = "result.xlsx";

        long start = System.currentTimeMillis();
        // 读取表格
        DataListener datasListener = new DataListener();
        EasyExcelFactory.read(fileRead, Data.class, datasListener)
                .sheet()
                .doRead();

        List<Data> datas = datasListener.getDatas();
        // 校正原数据，不需要创建新对象
        int count = 0;
        for (Data data : datas) {
            if (data.getNumDeath() == null) {
                // 处理发病数据
                data.setNumIncidence(reFilter(data.getNumIncidence()));
                data.setFreqIncidence(reFilter(data.getFreqIncidence()));
                data.setAge0(reFilter(data.getAge0()));
                data.setAge1(reFilter(data.getAge1()));
                data.setAge5(reFilter(data.getAge5()));
                data.setAge10(reFilter(data.getAge10()));
                data.setAge15(reFilter(data.getAge15()));
                data.setAge20(reFilter(data.getAge20()));
                data.setAge25(reFilter(data.getAge25()));
                data.setAge30(reFilter(data.getAge30()));
                data.setAge35(reFilter(data.getAge35()));
                data.setAge40(reFilter(data.getAge40()));
                data.setAge45(reFilter(data.getAge45()));
                data.setAge50(reFilter(data.getAge50()));
                data.setAge55(reFilter(data.getAge55()));
                data.setAge60(reFilter(data.getAge60()));
                data.setAge65(reFilter(data.getAge65()));
                data.setAge70(reFilter(data.getAge70()));
                data.setAge75(reFilter(data.getAge75()));
                data.setAge80(reFilter(data.getAge80()));
                data.setAge85(reFilter(data.getAge85()));
                data.setRateCrude(reFilter(data.getRateCrude()));
                data.setAsrChinaIncidence(reFilter(data.getAsrChinaIncidence()));
                data.setAsrWorldIncidence(reFilter(data.getAsrWorldIncidence()));
                data.setCumRate64Incidence(reFilter(data.getCumRate64Incidence()));
                data.setCumRate74Incidence(reFilter(data.getCumRate74Incidence()));
            } else {
                // 处理死亡的数据
                data.setNumDeath(reFilter(data.getNumDeath()));
                data.setFreqDeath(reFilter(data.getFreqDeath()));
                data.setAge0(reFilter(data.getAge0()));
                data.setAge1(reFilter(data.getAge1()));
                data.setAge5(reFilter(data.getAge5()));
                data.setAge10(reFilter(data.getAge10()));
                data.setAge15(reFilter(data.getAge15()));
                data.setAge20(reFilter(data.getAge20()));
                data.setAge25(reFilter(data.getAge25()));
                data.setAge30(reFilter(data.getAge30()));
                data.setAge35(reFilter(data.getAge35()));
                data.setAge40(reFilter(data.getAge40()));
                data.setAge45(reFilter(data.getAge45()));
                data.setAge50(reFilter(data.getAge50()));
                data.setAge55(reFilter(data.getAge55()));
                data.setAge60(reFilter(data.getAge60()));
                data.setAge65(reFilter(data.getAge65()));
                data.setAge70(reFilter(data.getAge70()));
                data.setAge75(reFilter(data.getAge75()));
                data.setAge80(reFilter(data.getAge80()));
                data.setAge85(reFilter(data.getAge85()));
                data.setRateCrude(reFilter(data.getRateCrude()));
                data.setAsrChinaDeath(reFilter(data.getAsrChinaDeath()));
                data.setAsrWorldDeath(reFilter(data.getAsrWorldDeath()));
                data.setCumRate64Death(reFilter(data.getCumRate64Death()));
                data.setCumRate74Death(reFilter(data.getCumRate74Death()));
            }
            count++;
            log.info("校对了第" + count + "条数据");
        }

        // 写入主表格
        EasyExcelFactory.write(fileResult, Data.class)
                // 是否在原有数据上追加写入数据，否则直接清空
                // .withTemplate(fileWrite)
                .sheet()
                // 是否写入表头
                // .needHead(false)
                .doWrite(datas);
        long end = System.currentTimeMillis();
        log.warn("\n================================\n写入完成,共" + datas.size()
                + "条数据\n耗时:" + (end - start) / 1000 + "s\n================================");
    }

    public static String reFilter(String str) {
        try {
            str = str.replaceAll("\\(\\)", "0");
            return str;
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }
}