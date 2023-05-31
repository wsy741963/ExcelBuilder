package com.yzozhi;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.excel.EasyExcelFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelRegex {
    @Test
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 定义文件
        String fileRead = "2020.xlsx";
        String fileResult = "2020_regex.xlsx";

        // 读取表格
        DataListener datasListener = new DataListener();
        EasyExcelFactory.read(fileRead, Data.class, datasListener)
                .sheet()
                .doRead();

        List<Data> datas = datasListener.getDatas();
        List<Data> newDatas = new ArrayList<>();
        int count = 0;
        for (Data data : datas) {
            // 校正原数据，不需要创建新对象
            data.setRateIncidence(reFilter(data.getRateIncidence()));
            data.setRateDeath(reFilter(data.getRateDeath()));
            data.setNumIncidence(reFilter(data.getNumIncidence()));
            data.setFreqIncidence(reFilter(data.getFreqIncidence()));
            data.setAsrChinaIncidence(reFilter(data.getAsrChinaIncidence()));
            data.setAsrWorldIncidence(reFilter(data.getAsrWorldIncidence()));
            data.setCumRate64Incidence(reFilter(data.getCumRate64Incidence()));
            data.setCumRate74Incidence(reFilter(data.getCumRate74Incidence()));
            data.setNumDeath(reFilter(data.getNumDeath()));
            data.setFreqDeath(reFilter(data.getFreqDeath()));
            data.setAsrChinaDeath(reFilter(data.getAsrChinaDeath()));
            data.setAsrWorldDeath(reFilter(data.getAsrWorldDeath()));
            data.setCumRate64Death(reFilter(data.getCumRate64Death()));
            data.setCumRate74Death(reFilter(data.getCumRate74Death()));
            data.setCoverPopulation(reFilter(data.getCoverPopulation()));
            data.setMvPercent(reFilter(data.getMvPercent()));
            data.setDcoPercent(reFilter(data.getDcoPercent()));
            data.setMIRate(reFilter(data.getMIRate()));
            data.setChangeForCr(reFilter(data.getChangeForCr()));
            newDatas.add(data);
            count++;
            log.info("校对了第" + count + "条数据");
        }

        // 写入主表格
        EasyExcelFactory.write(fileResult, NewData.class)
                // 是否在原有数据上追加写入数据，否则直接清空
                // .withTemplate(fileWrite)
                .sheet()
                // 是否写入表头
                // .needHead(false)
                .doWrite(datas);

        long end = System
                .currentTimeMillis();
        log.warn("\n================================\n写入完成,共" + datas.size() + "条数据\n耗时:" + (end - start) / 1000
                + "s\n================================");
    }

    public static String reFilter(String str) {
        try {
            str = str.replaceAll("\\(\\)", "0");
            return str;
        } catch (Exception e) {
            log.error(e.getMessage());
            return str;
        }
    }
}