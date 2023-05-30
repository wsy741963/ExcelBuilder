package com.yzozhi;

import com.alibaba.excel.annotation.ExcelProperty;

//lombok生成所有构造器
@lombok.Data
public class NewData {
   // 写入需要按顺序需
   // 原表没有的列，需要处理的赋值
   @ExcelProperty("year")
   private String year;

   @ExcelProperty("location")
   private String location;

   @ExcelProperty("sex")
   private String sex;

   @ExcelProperty("age")
   private String age;

   @ExcelProperty("rate_incidence")
   private String rateIncidence;

   @ExcelProperty("rate_death")
   private String rateDeath;

   // 原表有的列
   // 发病
   @ExcelProperty("cause")
   private String cause;

   @ExcelProperty("num_incidence")
   private String numIncidence;

   @ExcelProperty("freq_incidence")
   private String freqIncidence;

   @ExcelProperty("asr_china_incidence")
   private String asrChinaIncidence;

   @ExcelProperty("asr_world_incidence")
   private String asrWorldIncidence;

   @ExcelProperty("cum_rate_64_incidence")
   private String cumRate64Incidence;

   @ExcelProperty("cum_rate_74_incidence")
   private String cumRate74Incidence;

   // 死亡
   @ExcelProperty("num_death")
   private String numDeath;

   @ExcelProperty("freq_death")
   private String freqDeath;

   @ExcelProperty("asr_china_death")
   private String asrChinaDeath;

   @ExcelProperty("asr_world_death")
   private String asrWorldDeath;

   @ExcelProperty("cum_rate_64_death")
   private String cumRate64Death;

   @ExcelProperty("cum_rate_74_death")
   private String cumRate74Death;

   // 地区层级数据
   @ExcelProperty("location_level")
   private String locationLevel;

   @ExcelProperty("cover_population")
   private String coverPopulation;

   @ExcelProperty("mv_percent")
   private String mvPercent;

   @ExcelProperty("dco_percent")
   private String dcoPercent;

   @ExcelProperty("m_i_rate")
   private String mIRate;

   @ExcelProperty("change_for_cr")
   private String changeForCr;

   // 暂时无数据的列
   @ExcelProperty("population")
   private String population;
}