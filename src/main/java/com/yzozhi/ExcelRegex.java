package com.yzozhi;

import java.util.List;

import com.alibaba.excel.EasyExcelFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelRegex {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 定义文件
        String fileRead = "2020.xlsx";
        String fileResult = fileRead.substring(0, 4) + "_regex.xlsx";

        String[] sheetNames = { "3-2", "4-1", "1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7", "1-8", "1-9", "1-10",
                "1-11", "1-12", "1-13", "1-14", "1-15", "1-16", "1-17", "1-18", "2-1", "2-2", "2-3", "2-4", "2-5",
                "2-6", "2-7", "2-8", "2-9", "2-10", "2-11", "2-12", "2-13", "2-14", "2-15", "2-16", "2-17", "2-18" };
        // 读取sheet
        OldDataListener datasListener = new OldDataListener();
        for (int i = 0; i < sheetNames.length; i++) {
            EasyExcelFactory.read(fileRead, OldData.class, datasListener)
                    .sheet(i)
                    .doRead();

            List<OldData> datas = datasListener.getDatas();
            int count = 0;
            for (OldData data : datas) {
                // 校正原数据，不需要创建新对象
                data.setA(reFilter(data.getA()));
                data.setB(reFilter(data.getB()));
                data.setC(reFilter(data.getC()));
                data.setD(reFilter(data.getD()));
                data.setE(reFilter(data.getE()));
                data.setF(reFilter(data.getF()));
                data.setG(reFilter(data.getG()));
                data.setH(reFilter(data.getH()));
                data.setI(reFilter(data.getI()));
                data.setJ(reFilter(data.getJ()));
                data.setK(reFilter(data.getK()));
                data.setL(reFilter(data.getL()));
                data.setM(reFilter(data.getM()));
                data.setN(reFilter(data.getN()));
                data.setO(reFilter(data.getO()));
                data.setP(reFilter(data.getP()));
                data.setQ(reFilter(data.getQ()));
                data.setR(reFilter(data.getR()));

                count++;
                log.info("校对了第" + count + "条数据");
            }

            // 写入主表格
            EasyExcelFactory.write(fileResult, OldData.class)
                    // 是否在原有数据上追加写入数据，否则直接清空
                    // .withTemplate(fileWrite)
                    .sheet(sheetNames[i])
                    // 是否写入表头
                    // .needHead(false)
                    .doWrite(datas);

            long end = System.currentTimeMillis();
            log.warn("\n================================\n写入完成,共" + datas.size() + "条数据\n耗时:" + (end - start) / 1000
                    + "s\n================================");
        }
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