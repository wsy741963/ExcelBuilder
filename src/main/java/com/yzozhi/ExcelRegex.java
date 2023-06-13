package com.yzozhi;

import java.io.File;
// import java.util.ArrayList;
// import java.util.Arrays;
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
            // 封装所有表头
            // String[] names = { "序号No.", "location_level", "location", "cover_population",
            // "num_incidence", "num_death",
            // "mv_percent", "dco_percent,m_i_rate", "change_for_cr", "接受Accepted" };
            // String[] names2 = { "age", "national_total", "national_male",
            // "national_female", "city_total",
            // "city_male", "city_female", "village_total", "village_male",
            // "village_female",
            // "east_total", "east_male", "east_female", "central_total", "central_male",
            // "central_female", "west_total", "west_male", "west_female" };
            // String[] names3 = { "cause", "", "num_incidence", "freq_incidence",
            // "0", "1", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55",
            // "60", "65", "70", "75",
            // "80", "85", "rate_crude", "asr_china_incidence", "asr_world_incidence",
            // "cum_rate_64_incidence",
            // "cum_rate_74_incidence" };
            // String[] names4 = { "cause", "", "num_death", "freq_death", "0", "1", "5",
            // "10", "15", "20", "25", "30",
            // "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85",
            // "rate_crude", "asr_china_death",
            // "asr_world_death", "cum_rate_64_death", "cum_rate_74_death" };
            // String[] names5 = { "cause", "", "num_incidence", "freq_incidence",
            // "rate_crude", "asr_world_incidence",
            // "cum_rate_64_incidence", "cum_rate_74_incidence", "num_incidence1",
            // "freq_incidence1",
            // "rate_crude1", "asr_world_incidence1", "cum_rate_64_incidence1",
            // "cum_rate_74_incidence1" };
            // String[] names6 = { "cause", "", "num_death", "freq_death", "rate_crude",
            // "asr_world_death",
            // "cum_rate_64_death", "cum_rate_74_death", "num_death1", "freq_death1",
            // "rate_crude1",
            // "asr_china_death1", "asr_world_death1", "cum_rate_64_death1",
            // "cum_rate_74_death1" };

            // 指定sheet名
            String[] sheetNames = { "3-2", "4-1", "1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7", "1-8", "1-9",
                    "1-10",
                    "1-11", "1-12", "1-13", "1-14", "1-15", "1-16", "1-17", "1-18", "2-1", "2-2", "2-3", "2-4",
                    "2-5",
                    "2-6", "2-7", "2-8", "2-9", "2-10", "2-11", "2-12", "2-13", "2-14", "2-15", "2-16", "2-17",
                    "2-18" };

            // 遍历所有文件
            for (File file : fl) {
                String fn = Regex.regex(file.getName(), "^20\\d{2}_old\\.xlsx?$", 0);
                if (!fn.equals("")) {
                    log.warn("发现文件:" + fn);
                    String year = fn.substring(0, 4);

                    // 定义文件
                    String fileRead = year + "_old.xlsx";
                    String fileResult = year + "_regex.xlsx";

                    if (FileUtils.getFile(fileResult).exists()) {
                        log.error("-->" + year + ":上次生成的文件已存在!退出!请删除上次结果!");
                        break;
                    }

                    // 创建写对象
                    ExcelWriter excelWriter = EasyExcelFactory.write(fileResult).build();

                    int count = 0;
                    for (int i = 0; i < sheetNames.length; i++) {
                        OldDataListener datasListener = new OldDataListener();
                        EasyExcelFactory.read(fileRead, OldData.class, datasListener)
                                .sheet(i)
                                .doRead();
                        List<OldData> datas = datasListener.getDatas();
                        String sheetName = sheetNames[i];
                        log.warn("\n处理表" + sheetName);
                        for (OldData data : datas) {
                            // 校正原数据
                            data.setA(reFilter(data.getA(), sheetName));
                            data.setB(reFilter(data.getB(), sheetName));
                            data.setC(reFilter(data.getC(), sheetName));
                            data.setD(reFilter(data.getD(), sheetName));
                            data.setE(reFilter(data.getE(), sheetName));
                            data.setF(reFilter(data.getF(), sheetName));
                            data.setG(reFilter(data.getG(), sheetName));
                            data.setH(reFilter(data.getH(), sheetName));
                            data.setI(reFilter(data.getI(), sheetName));
                            data.setJ(reFilter(data.getJ(), sheetName));
                            data.setK(reFilter(data.getK(), sheetName));
                            data.setL(reFilter(data.getL(), sheetName));
                            data.setM(reFilter(data.getM(), sheetName));
                            data.setN(reFilter(data.getN(), sheetName));
                            data.setO(reFilter(data.getO(), sheetName));
                            data.setP(reFilter(data.getP(), sheetName));
                            data.setQ(reFilter(data.getQ(), sheetName));
                            data.setR(reFilter(data.getR(), sheetName));
                            data.setS(reFilter(data.getS(), sheetName));
                            data.setT(reFilter(data.getT(), sheetName));
                            data.setU(reFilter(data.getU(), sheetName));
                            data.setV(reFilter(data.getV(), sheetName));
                            data.setW(reFilter(data.getW(), sheetName));
                            data.setX(reFilter(data.getX(), sheetName));
                            data.setY(reFilter(data.getY(), sheetName));
                            data.setZ(reFilter(data.getZ(), sheetName));
                            data.setAa(reFilter(data.getAa(), sheetName));
                            data.setAb(reFilter(data.getAb(), sheetName));
                            data.setAc(reFilter(data.getAc(), sheetName));
                            count++;
                            log.info("校对了第" + count + "条数据");
                        }
                        count += datas.size();
                        // 写入sheet
                        excelWriter.write(datas,
                                EasyExcelFactory.writerSheet(i, sheetName).head(OldData.class).build());
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
            log.error(e.getMessage());
        }
    }

    public static String reFilter(String str, String sheetName) {
        try {
            // 匹配汉字，去除英文
            if (!Regex.regex(str, "[\u4e00-\u9fa5]", 0).equals("")) {
                return Regex.regex(str, "[\u4e00-\u9fa5]+", 0);
            }
            // 匹配到数字，校验,否则跳过
            else if (Regex.regex(str, "\\d", 0).equals("")) {
                return str;
            }

            String newStr = null;
            // 纠正规则
            newStr = str.replaceAll(" ", "");
            newStr = newStr.replaceAll("\\(\\)", "0");
            newStr = newStr.replaceAll("\\(", "1");
            newStr = newStr.replaceAll("\\)", "1");
            newStr = newStr.replaceAll("L", "1.");
            newStr = newStr.replaceAll("U", "0");
            newStr = newStr.replaceAll("O", "0");
            newStr = newStr.replaceAll("o", "0");
            newStr = newStr.replaceAll("D", "0");
            newStr = newStr.replaceAll("S", "5");
            newStr = newStr.replaceAll("n", "0");
            newStr = newStr.replaceAll("c", "0");
            newStr = newStr.replaceAll("C", "0");
            newStr = newStr.replaceAll("z", "2");
            newStr = newStr.replaceAll("g", "9");
            newStr = newStr.replaceAll("G", "0.");
            newStr = newStr.replaceAll("!", "1");
            newStr = newStr.replaceAll("I", "1");
            newStr = newStr.replaceAll("i", "1");
            newStr = newStr.replaceAll("】", "1");
            newStr = newStr.replaceAll("\\[", "1");
            newStr = newStr.replaceAll("\\]", "1");
            newStr = newStr.replaceAll(",", ".");
            newStr = newStr.replaceAll("、", ".");
            newStr = newStr.replaceAll("J", "1");
            newStr = newStr.replaceAll("Q", "0.");
            newStr = newStr.replaceAll("q", "9");

            // 输出纠正记录到日志
            if (!newStr.equals(str)) {
                log.info("自动校对:" + str + " -> " + newStr);
            }
            str = newStr;
            // 如果还剩余未识别字符，输出log
            if (!Regex.regex(str, "[^\\d\\.\\-~〜\\+]", 0).equals("")) {
                if (Regex.regex(str, "^C\\d+$|^G\\d+$", 0).equals("")) {
                    log.warn("需要手动校对:" + str);
                }
                // 小数点缺失正则匹配自动校对
            } else if (!Regex.regex(str, "^-?0\\d+$", 0).equals("")) {
                String fStr = null;
                if (!str.substring(0, 1).equals("-")) {
                    fStr = str.substring(0, 1) + "." + str.substring(1);
                    log.warn("自动校对小数点:" + str + " -> " + fStr);
                } else {
                    fStr = str.substring(0, 2) + "." + str.substring(2);
                    log.warn("自动校对小数点:" + str + " -> " + fStr);
                }
                str = fStr;
                // 匹配无小数点或多小数点的情况
            } else if (Regex.regex(str, "^\\-?\\d+\\.\\d+$|^\\-?\\d$", 0).equals("")) {
                log.warn("需要手动校对小数点:" + str);
            }
            return str;
        } catch (Exception e) {
            log.error(e.getMessage());
            return str;
        }
    }
}