package com.yaozh;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelRegex {
    public static void excelRegex(int input) {
        try {
            long start = System.currentTimeMillis();
            Collection<File> fl = FileUtils.listFiles(new File("./"), null, false);
            // 封装所有表头
            String[] names = { "序号No.", "location_level", "location", "cover_population",
                    "num_incidence", "num_death",
                    "mv_percent", "dco_percent", "m_i_rate", "change_for_cr", "接受Accepted" };
            List<List<String>> lists = listBuilder(names);
            String[] names2 = { "age", "national_total", "national_male",
                    "national_female", "city_total",
                    "city_male", "city_female", "village_total", "village_male",
                    "village_female",
                    "east_total", "east_male", "east_female", "central_total", "central_male",
                    "central_female", "west_total", "west_male", "west_female" };
            List<List<String>> lists2 = listBuilder(names2);
            String[] names3 = { "cause", "", "num_incidence", "freq_incidence",
                    "0", "1", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55",
                    "60", "65", "70", "75",
                    "80", "85", "rate_crude", "asr_china_incidence", "asr_world_incidence",
                    "cum_rate_64_incidence",
                    "cum_rate_74_incidence" };
            List<List<String>> lists3 = listBuilder(names3);
            String[] names4 = { "cause", "", "num_death", "freq_death", "0", "1", "5",
                    "10", "15", "20", "25", "30",
                    "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85",
                    "rate_crude", "asr_china_death",
                    "asr_world_death", "cum_rate_64_death", "cum_rate_74_death" };
            List<List<String>> lists4 = listBuilder(names4);
            String[] names5 = { "cause", "", "num_incidence", "freq_incidence",
                    "rate_crude", "asr_world_incidence",
                    "cum_rate_64_incidence", "cum_rate_74_incidence", "num_incidence1",
                    "freq_incidence1",
                    "rate_crude1", "asr_world_incidence1", "cum_rate_64_incidence1",
                    "cum_rate_74_incidence1" };
            List<List<String>> lists5 = listBuilder(names5);
            String[] names6 = { "cause", "", "num_death", "freq_death", "rate_crude",
                    "asr_world_death",
                    "cum_rate_64_death", "cum_rate_74_death", "num_death1", "freq_death1",
                    "rate_crude1", "asr_world_death1", "cum_rate_64_death1",
                    "cum_rate_74_death1" };
            List<List<String>> lists6 = listBuilder(names6);

            // 指定sheet名
            String[] sheetNames = { "3-2", "4-1", "1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7", "1-8", "1-9",
                    "1-10",
                    "1-11", "1-12", "1-13", "1-14", "1-15", "1-16", "1-17", "1-18", "2-1", "2-2", "2-3", "2-4",
                    "2-5",
                    "2-6", "2-7", "2-8", "2-9", "2-10", "2-11", "2-12", "2-13", "2-14", "2-15", "2-16", "2-17",
                    "2-18" };
            // 校对参数
            int j = 3;
            if (input == 3) {
                j = 4;
            }

            // 遍历所有文件
            for (File file : fl) {
                String fn = Regex.regex(file.getName(), "^20\\d{2}_old\\.xlsx?$", 0);
                if (!fn.equals("")) {
                    log.warn("发现文件:" + fn);

                    // 定义文件
                    String fileRead = fn;
                    String year = fn.substring(0, 4);
                    String fileResult = year + "_regex.xlsx";

                    if (FileUtils.getFile(fileResult).exists()) {
                        log.error("-->" + year + ":上次生成的文件已存在!退出!请删除上次结果!");
                        break;
                    }

                    // 创建写对象
                    ExcelWriter excelWriter = EasyExcelFactory.write(fileResult).build();
                    // 计数
                    int count = 0;
                    for (int i = 0; i < sheetNames.length; i++) {
                        OldDataListener datasListener = new OldDataListener();
                        EasyExcelFactory.read(fileRead, OldData.class, datasListener)
                                .sheet(i)
                                .doRead();
                        List<OldData> datas = datasListener.getDatas();
                        String sheetName = sheetNames[i];
                        log.warn("\n================================\n处理表:" + sheetName
                                + "\n================================");
                        for (OldData data : datas) {
                            // 校正原数据，1校对汉字，2校对整数，3校对浮点，4补全小数
                            if (i == 0) {
                                data.setC(reFilter(data.getC(), 1));
                                data.setD(reFilter(data.getD(), 2));
                                data.setE(reFilter(data.getE(), 2));
                                data.setF(reFilter(data.getF(), 2));
                                data.setG(reFilter(data.getG(), j));
                                data.setH(reFilter(data.getH(), j));
                                data.setI(reFilter(data.getI(), j));
                                data.setJ(reFilter(data.getJ(), j));
                            } else if (i == 1) {
                                data.setB(reFilter(data.getB(), 2));
                                data.setC(reFilter(data.getC(), 2));
                                data.setD(reFilter(data.getD(), 2));
                                data.setE(reFilter(data.getE(), 2));
                                data.setF(reFilter(data.getF(), 2));
                                data.setG(reFilter(data.getG(), 2));
                                data.setH(reFilter(data.getH(), 2));
                                data.setI(reFilter(data.getI(), 2));
                                data.setJ(reFilter(data.getJ(), 2));
                                data.setK(reFilter(data.getK(), 2));
                                data.setL(reFilter(data.getL(), 2));
                                data.setM(reFilter(data.getM(), 2));
                                data.setN(reFilter(data.getN(), 2));
                                data.setO(reFilter(data.getO(), 2));
                                data.setP(reFilter(data.getP(), 2));
                                data.setQ(reFilter(data.getQ(), 2));
                                data.setR(reFilter(data.getR(), 2));
                                data.setS(reFilter(data.getS(), 2));
                            } else if (i > 1 && i < 20) {
                                data.setA(reFilter(data.getA(), 1));
                                data.setC(reFilter(data.getC(), 2));
                                data.setD(reFilter(data.getD(), j));
                                data.setE(reFilter(data.getE(), j));
                                data.setF(reFilter(data.getF(), j));
                                data.setG(reFilter(data.getG(), j));
                                data.setH(reFilter(data.getH(), j));
                                data.setI(reFilter(data.getI(), j));
                                data.setJ(reFilter(data.getJ(), j));
                                data.setK(reFilter(data.getK(), j));
                                data.setL(reFilter(data.getL(), j));
                                data.setM(reFilter(data.getM(), j));
                                data.setN(reFilter(data.getN(), j));
                                data.setO(reFilter(data.getO(), j));
                                data.setP(reFilter(data.getP(), j));
                                data.setQ(reFilter(data.getQ(), j));
                                data.setR(reFilter(data.getR(), j));
                                data.setS(reFilter(data.getS(), j));
                                data.setT(reFilter(data.getT(), j));
                                data.setU(reFilter(data.getU(), j));
                                data.setV(reFilter(data.getV(), j));
                                data.setW(reFilter(data.getW(), j));
                                data.setX(reFilter(data.getX(), j));
                                data.setY(reFilter(data.getY(), j));
                                data.setZ(reFilter(data.getZ(), j));
                                data.setAa(reFilter(data.getAa(), j));
                                data.setAb(reFilter(data.getAb(), j));
                            } else if (i > 19 && i < 38) {
                                data.setA(reFilter(data.getA(), 1));
                                data.setC(reFilter(data.getC(), 2));
                                data.setI(reFilter(data.getI(), 2));
                                data.setD(reFilter(data.getD(), j));
                                data.setE(reFilter(data.getE(), j));
                                data.setF(reFilter(data.getF(), j));
                                data.setG(reFilter(data.getG(), j));
                                data.setH(reFilter(data.getH(), j));
                                data.setJ(reFilter(data.getJ(), j));
                                data.setK(reFilter(data.getK(), j));
                                data.setL(reFilter(data.getL(), j));
                                data.setM(reFilter(data.getM(), j));
                                data.setN(reFilter(data.getN(), j));
                            }

                            count++;
                            log.info("校对了第" + count + "条数据");
                        }
                        count += datas.size();

                        // 表头判定
                        List<List<String>> nameLists = new ArrayList<>();
                        if (i == 0) {
                            nameLists = lists;
                        } else if (i == 1) {
                            nameLists = lists2;
                            // 2-10
                        } else if (i > 1 && i < 11) {
                            nameLists = lists3;
                            // 11-19
                        } else if (i > 10 && i < 20) {
                            nameLists = lists4;
                            // 20-28
                        } else if (i > 19 && i < 29) {
                            nameLists = lists5;
                            // 29-37
                        } else if (i > 28 && i < 38) {
                            nameLists = lists6;
                        }

                        // 写入sheet
                        excelWriter.write(datas,
                                EasyExcelFactory.writerSheet(i, sheetName).head(nameLists).build());
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

    public static String reFilter(String str, int j) {
        try {

            // 提示空
            if (str == null) {
                log.warn("注意!空字符串");
                return str;
            }

            // 去空格
            str = str.replaceAll(" ", "");

            // 匹配汉字，去除英文
            String newStr = null;
            if (j == 1) {
                str = str.replaceAll(",", "，");
                newStr = Regex.regex(str, "^[\\u4e00-\\u9fa5、，]+$", 0);
                if (newStr.equals("")) {
                    log.warn("病因字符有误：" + str);
                    return str;
                }
                return newStr;
            } else {
                // 数字纠正规则
                newStr = str.replaceAll("\\(\\)", "0");
                newStr = newStr.replaceAll("\\(", "1");
                newStr = newStr.replaceAll("\\)", "1");
                newStr = newStr.replaceAll("L", "1.");
                newStr = newStr.replaceAll("U", "0");
                newStr = newStr.replaceAll("H", "11");
                newStr = newStr.replaceAll("O", "0");
                newStr = newStr.replaceAll("o", "0");
                newStr = newStr.replaceAll("D", "0");
                newStr = newStr.replaceAll("S", "5");
                newStr = newStr.replaceAll("a", "0.");
                newStr = newStr.replaceAll("n", "0");
                newStr = newStr.replaceAll("c", "0");
                newStr = newStr.replaceAll("C", "0");
                newStr = newStr.replaceAll("z", "2");
                newStr = newStr.replaceAll("Z", "2.");
                newStr = newStr.replaceAll("g", "9");
                newStr = newStr.replaceAll("G", "0.");
                newStr = newStr.replaceAll("R", "8");
                newStr = newStr.replaceAll("J", "1");
                newStr = newStr.replaceAll("Q", "0.");
                newStr = newStr.replaceAll("q", "9");
                newStr = newStr.replaceAll("I", "1");
                newStr = newStr.replaceAll("i", "1");
                newStr = newStr.replaceAll("l", "1");
                newStr = newStr.replaceAll("!", "1");
                newStr = newStr.replaceAll("】", "1");
                newStr = newStr.replaceAll("\\[", "1");
                newStr = newStr.replaceAll("\\]", "1");
                newStr = newStr.replaceAll(",", ".");
                newStr = newStr.replaceAll("、", ".");
                newStr = newStr.replaceAll(":", ".");
                newStr = newStr.replaceAll("«", "8");
                newStr = newStr.replaceAll("»", "8");
                newStr = newStr.replaceAll("\\^", ".2");
                newStr = newStr.replaceAll("\\.\\.", "\\.");
                newStr = newStr.replaceAll("\\（x\\）", "00");
                newStr = newStr.replaceAll("^OJO", "0.10");
                newStr = newStr.replaceAll("1X1", "00");
                newStr = newStr.replaceAll("1M1", "00");

                // 输出纠正记录到日志
                if (!newStr.equals(str)) {
                    log.warn("自动校对字符:" + str + " -> " + newStr);
                }
                str = newStr;

                // 识别数字以外字符
                if (!Regex.regex(str, "[^\\d\\.\\-]", 0).equals("")) {
                    log.warn("需要手动校对字符:" + str);
                    return str;
                }

                // 正整数验证
                if (j == 2 && Regex.regex(str, "^\\-$|^\\d$|^[1-9]\\d+$", 0).equals("")) {
                    log.warn("数量应为正整数:" + str);
                    return str;
                }

                // 纠正横杠为小数点
                if (j > 2 && !Regex.regex(str, "^\\d+\\-\\d+$", 0).equals("")) {
                    newStr = str.replaceAll("-", ".");
                    log.warn("自动校对:" + str + " -> " + newStr);
                    str = newStr;
                }

                // 补齐小数点后位数
                if (j == 4 && !Regex.regex(str, "^\\-?\\d+$", 0).equals("")) {
                    newStr = str + ".00";
                    log.warn("自动补齐:" + str + " -> " + newStr);
                    str = newStr;
                } else if (j == 4 && !Regex.regex(str, "^\\-?\\d+\\.\\d$", 0).equals("")) {
                    newStr = str + "0";
                    log.warn("自动补齐:" + str + " -> " + newStr);
                    str = newStr;
                }

                // 自动校对小数点
                if (j == 3 && !Regex.regex(str, "^-?\\d{3,}$", 0).equals("")) {
                    newStr = str.substring(0, str.length() - 2) + "." + str.substring(str.length() - 2);
                    log.warn("自动校对小数点:" + str + " -> " + newStr);
                    str = newStr;
                }

                // 小数验证
                if (j > 2 && Regex.regex(str, "^\\-$|^\\-?0\\.\\d{2}$|^\\-?[1-9]\\d*\\.\\d{2}$", 0).equals("")) {
                    log.warn("小数有问题:" + str);
                    return str;
                }
            }
            return str;
        } catch (Exception e) {
            log.error(e.getMessage());
            return str;
        }
    }

    public static List<List<String>> listBuilder(String[] names) {
        // 封装表头对象
        List<List<String>> lists = new ArrayList<>();
        for (String name : names) {
            List<String> data = new ArrayList<>();
            data.add(name);
            lists.add(data);
        }
        return lists;
    }
}