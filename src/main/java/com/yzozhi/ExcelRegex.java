package com.yzozhi;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelRegex {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            Collection<File> fl = FileUtils.listFiles(new File("./"), null, false);
            for (File file : fl) {
                String fn = Regex.regex(file.getName(), "(20\\d{2}_old\\.xlsx?)", 1);
                if (!fn.equals("")) {
                    log.warn("发现文件:" + fn);
                    String year = fn.substring(0, 4);

                    // 定义文件
                    String fileRead = year + "_old.xlsx";
                    String fileResult = year + "_regex.xlsx";

                    if (FileUtils.getFile(fileResult).exists()) {
                        log.error("-->" + year + ":上次生成的文件已存在!退出!请删除上次结果!");
                        return;
                    }

                    String[] sheetNames = { "3-2", "4-1", "1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7", "1-8", "1-9",
                            "1-10",
                            "1-11", "1-12", "1-13", "1-14", "1-15", "1-16", "1-17", "1-18", "2-1", "2-2", "2-3", "2-4",
                            "2-5",
                            "2-6", "2-7", "2-8", "2-9", "2-10", "2-11", "2-12", "2-13", "2-14", "2-15", "2-16", "2-17",
                            "2-18" };

                    // 创建写对象
                    ExcelWriter excelWriter = EasyExcelFactory.write(fileResult).build();

                    int count = 0;
                    for (int i = 0; i < sheetNames.length; i++) {
                        OldDataListener datasListener = new OldDataListener();
                        EasyExcelFactory.read(fileRead, OldData.class, datasListener)
                                .sheet(i)
                                .doRead();
                        List<OldData> datas = datasListener.getDatas();
                        log.warn("\n处理表" + sheetNames[i]);
                        for (OldData data : datas) {
                            // 校正原数据
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
                            data.setS(reFilter(data.getS()));
                            data.setT(reFilter(data.getT()));
                            data.setU(reFilter(data.getU()));
                            data.setV(reFilter(data.getV()));
                            data.setW(reFilter(data.getW()));
                            data.setX(reFilter(data.getX()));
                            data.setY(reFilter(data.getY()));
                            data.setZ(reFilter(data.getZ()));
                            data.setAa(reFilter(data.getAa()));
                            data.setAb(reFilter(data.getAb()));
                            data.setAc(reFilter(data.getAc()));
                            count++;
                            log.info("校对了第" + count + "条数据");
                        }
                        count += datas.size();
                        // 写入sheet
                        excelWriter.write(datas, EasyExcelFactory.writerSheet(i, sheetNames[i]).build());
                        // 清除缓存
                        datasListener.clean();
                    }
                    excelWriter.finish();
                    long end = System.currentTimeMillis();
                    log.warn("\n================================\n写入完成,共写入" + count + "条数据，共耗时:"
                            + (end - start) / 1000
                            + "s\n================================");
                }
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                log.info(e.getMessage());
            }
            log.error(e.getMessage());
        }
    }

    public static String reFilter(String str) {
        try {
            // 匹配到数字，校验,否则跳过
            if (Regex.regex(str, "(\\d)", 1).equals("")) {
                return str;
            }
            // 匹配规则
            str = str.replaceAll(" ", "");
            str = str.replaceAll("\\(\\)", "0");
            str = str.replaceAll("\\(", "1");
            str = str.replaceAll("\\)", "1");
            str = str.replaceAll("L", "1.");
            str = str.replaceAll("O", "0");
            str = str.replaceAll("o", "0");
            str = str.replaceAll("!", "1");
            str = str.replaceAll("I", "1");
            str = str.replaceAll("i", "1");
            str = str.replaceAll("\\[", "1");
            str = str.replaceAll("\\]", "1");
            str = str.replaceAll(",", ".");
            str = str.replaceAll("、", ".");
            str = str.replaceAll("J", "1");
            str = str.replaceAll("Q", "0.");
            // str = str.replaceAll("Q", "0.");

            // 如果还剩余未识别字符，输出log
            if (!Regex.regex(str, "([^\\d\\.\\-~])", 1).equals("")) {
                log.warn("需要手动校对:" + str);
            }
            return str;
        } catch (Exception e) {
            if (e.getMessage() == null) {
                log.info(e.getMessage());
                return str;
            }
            log.error(e.getMessage());
            return str;
        }
    }
}