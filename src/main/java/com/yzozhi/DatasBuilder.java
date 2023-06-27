package com.yzozhi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatasBuilder {
    public static List<NewData> datasBuild(List<Data> datas, String year, String location, String sex) {
        try {
            // 新建返回对象
            List<NewData> newDatas = new ArrayList<>();
            Class<?> dataClass = Data.class;
            String[] ageStrings = { "0", "1", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60",
                    "65", "70", "75", "80", "85" };
            String[] locationAndSex = { "national_total", "national_male", "national_female", "city_total", "city_male",
                    "city_female", "village_total", "village_male", "village_female", "east_total", "east_male",
                    "east_female", "central_total", "central_male", "central_female", "west_total", "west_male",
                    "west_female" };
            // 新建计数
            int count = 0;
            int count1 = 0;
            // 获取真实年份
            year = String.valueOf(Integer.parseInt(year) - 3);

            // 数据遍历重构
            for (Data data : datas) {
                if (data.getChangeForCr() != null) {
                    NewData newData = new NewData();
                    newData.setYear(year);
                    newData.setLocation(data.getLocation());
                    newData.setLocationLevel(data.getLocationLevel());
                    newData.setSex("合计");
                    newData.setAge("全部");
                    newData.setCause("全部");
                    newData.setNumIncidence(data.getNumIncidence());
                    newData.setNumDeath(data.getNumDeath());
                    newData.setCoverPopulation(data.getCoverPopulation());
                    newData.setMvPercent(data.getMvPercent());
                    newData.setDcoPercent(data.getDcoPercent());
                    newData.setMIRate(data.getMIRate());
                    newData.setChangeForCr(data.getChangeForCr());
                    newDatas.add(newData);
                    count++;
                    log.info(year + "处理3-2第" + count + "条数据");
                } else if (data.getAge() != null) {
                    String age = data.getAge();
                    for (int i = 0; i < locationAndSex.length; i++) {
                        NewData newData = new NewData();
                        // 匹配地区和性别
                        location = Regex.regex(locationAndSex[i], "(\\w+)_(\\w+)", 1);
                        sex = Regex.regex(locationAndSex[i], "(\\w+)_(\\w+)", 2);
                        String funcName = location + sex.substring(0, 1).toUpperCase() + sex.substring(1);
                        // 反射获取覆盖率
                        Field field = dataClass.getDeclaredField(funcName);
                        field.setAccessible(true);
                        String coverPopulation = (String) field.get(data);
                        newData.setLocation(location);
                        newData.setSex(sex);
                        newData.setAge(age);
                        newData.setCoverPopulation(coverPopulation);
                        newData.setYear(year);
                        newData.setCause("待处理");
                        newDatas.add(newData);
                        count++;
                        log.info(year + "处理4-1第" + count + "条数据");
                    }
                } else if (data.getAsrWorldIncidence1() != null) {
                    NewData newData = new NewData();
                    newData.setYear(year);
                    newData.setLocation(location);
                    newData.setSex("男");
                    newData.setAge("全部");
                    newData.setCause(data.getCause());
                    newData.setNumIncidence(data.getNumIncidence());
                    newData.setFreqIncidence(data.getFreqIncidence());
                    newData.setRateIncidence(data.getRateCrude());
                    newData.setAsrWorldIncidence(data.getAsrWorldIncidence());
                    newData.setCumRate64Incidence(data.getCumRate64Incidence());
                    newData.setCumRate74Incidence(data.getCumRate74Incidence());
                    newDatas.add(newData);
                    count++;
                    log.info(year + "处理附录2-发病第" + count + "条数据");

                    NewData newData1 = new NewData();
                    newData1.setYear(year);
                    newData1.setLocation(location);
                    newData1.setSex("女");
                    newData1.setAge("全部");
                    newData1.setCause(data.getCause());
                    newData1.setNumIncidence(data.getNumIncidence1());
                    newData1.setFreqIncidence(data.getFreqIncidence1());
                    newData1.setRateIncidence(data.getRateCrude1());
                    newData1.setAsrWorldIncidence(data.getAsrWorldIncidence1());
                    newData1.setCumRate64Incidence(data.getCumRate64Incidence1());
                    newData1.setCumRate74Incidence(data.getCumRate74Incidence1());
                    newDatas.add(newData1);
                    count++;
                    log.info(year + "处理附录2-发病第" + count + "条数据");
                } else if (data.getAsrWorldDeath1() != null) {
                    NewData newData = new NewData();
                    newData.setYear(year);
                    newData.setLocation(location);
                    newData.setSex("男");
                    newData.setAge("全部");
                    newData.setCause(data.getCause());
                    newData.setNumDeath(data.getNumDeath());
                    newData.setFreqDeath(data.getFreqDeath());
                    newData.setRateDeath(data.getRateCrude());
                    newData.setAsrWorldDeath(data.getAsrWorldDeath());
                    newData.setCumRate64Death(data.getCumRate64Death());
                    newData.setCumRate74Death(data.getCumRate74Death());
                    newDatas.add(newData);
                    count++;
                    log.info(year + "处理附录2-死亡第" + count + "条数据");

                    NewData newData1 = new NewData();
                    newData1.setYear(year);
                    newData1.setLocation(location);
                    newData1.setSex("女");
                    newData1.setAge("全部");
                    newData1.setCause(data.getCause());
                    newData1.setNumDeath(data.getNumDeath1());
                    newData1.setFreqDeath(data.getFreqDeath1());
                    newData1.setRateDeath(data.getRateCrude1());
                    newData1.setAsrWorldDeath(data.getAsrWorldDeath1());
                    newData1.setCumRate64Death(data.getCumRate64Death1());
                    newData1.setCumRate74Death(data.getCumRate74Death1());
                    newDatas.add(newData1);
                    count++;
                    log.info(year + "处理附录2-死亡第" + count + "条数据");
                } else if (data.getAge0() != null) {
                    for (int i = 0; i < ageStrings.length + 1; i++) {
                        // 新建要写入的每条数据
                        NewData newData = new NewData();
                        // 判断发病或死亡数据
                        if (data.getNumIncidence() != null) {
                            if (i != ageStrings.length) {
                                log.info(year + "处理附录1-发病");
                                // 反射提取年龄数据
                                Field field = dataClass.getDeclaredField("age" + ageStrings[i]);
                                field.setAccessible(true);
                                String rateIncidence = (String) field.get(data);

                                newData.setYear(year);
                                newData.setLocation(location);
                                newData.setSex(sex);
                                newData.setAge(ageStrings[i]);
                                newData.setCause(data.getCause());
                                newData.setRateIncidence(rateIncidence);
                            } else {
                                log.info(year + "处理附录1-全部发病");
                                newData.setYear(year);
                                newData.setLocation(location);
                                newData.setSex(sex);
                                newData.setAge("全部");
                                newData.setCause(data.getCause());
                                newData.setNumIncidence(data.getNumIncidence());
                                newData.setRateIncidence(data.getRateCrude());
                                newData.setFreqIncidence(data.getFreqIncidence());
                                newData.setAsrChinaIncidence(data.getAsrChinaIncidence());
                                newData.setAsrWorldIncidence(data.getAsrWorldIncidence());
                                newData.setCumRate64Incidence(data.getCumRate64Incidence());
                                newData.setCumRate74Incidence(data.getCumRate74Incidence());
                            }
                        } else {
                            // 赋值、处理逻辑
                            if (i != ageStrings.length) {
                                log.info(year + "处理附录1-死亡");
                                // 反射提取年龄数据
                                Field field = dataClass.getDeclaredField("age" + ageStrings[i]);
                                field.setAccessible(true);
                                String rateDeath = (String) field.get(data);

                                newData.setYear(year);
                                newData.setLocation(location);
                                newData.setSex(sex);
                                newData.setAge(ageStrings[i]);
                                newData.setCause(data.getCause());
                                newData.setRateDeath(rateDeath);
                            } else {
                                log.info(year + "处理附录1-全部死亡");
                                newData.setYear(year);
                                newData.setLocation(location);
                                newData.setSex(sex);
                                newData.setAge("全部");
                                newData.setCause(data.getCause());
                                newData.setNumDeath(data.getNumDeath());
                                newData.setRateDeath(data.getRateCrude());
                                newData.setFreqDeath(data.getFreqDeath());
                                newData.setAsrChinaDeath(data.getAsrChinaDeath());
                                newData.setAsrWorldDeath(data.getAsrWorldDeath());
                                newData.setCumRate64Death(data.getCumRate64Death());
                                newData.setCumRate74Death(data.getCumRate74Death());
                            }
                        }
                        // 加入总数据
                        newDatas.add(newData);
                        count++;
                        log.info("处理" + year + "第" + count + "条数据");
                    }
                } else {
                    count1++;
                    log.error(year + "识别到" + count1 + "条不明数据");
                    Field[] fields = dataClass.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        String value = (String) field.get(data);
                        if (value != null) {
                            log.error(value);
                        }
                    }
                }
            }
            return newDatas;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}