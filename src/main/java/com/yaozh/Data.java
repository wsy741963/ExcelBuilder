package com.yaozh;

// import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

//lombok生成所有构造器
@lombok.Data
public class Data {
   // 私有成员变量，匹配列名,无顺序
   // 原表没有的列，需要处理
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

   // 原表有的列，处理后舍弃的列
   @ExcelProperty("rate_crude")
   private String rateCrude;

   @ExcelProperty("rate_crude1")
   private String rateCrude1;

   @ExcelProperty("num_incidence1")
   private String numIncidence1;

   @ExcelProperty("freq_incidence1")
   private String freqIncidence1;

   @ExcelProperty("asr_world_incidence1")
   private String asrWorldIncidence1;

   @ExcelProperty("cum_rate_64_incidence1")
   private String cumRate64Incidence1;

   @ExcelProperty("cum_rate_74_incidence1")
   private String cumRate74Incidence1;

   @ExcelProperty("num_death1")
   private String numDeath1;

   @ExcelProperty("freq_death1")
   private String freqDeath1;

   @ExcelProperty("asr_world_death1")
   private String asrWorldDeath1;

   @ExcelProperty("cum_rate_64_death1")
   private String cumRate64Death1;

   @ExcelProperty("cum_rate_74_death1")
   private String cumRate74Death1;

   @ExcelProperty("0")
   private String age0;

   @ExcelProperty("1")
   private String age1;

   @ExcelProperty("5")
   private String age5;

   @ExcelProperty("10")
   private String age10;

   @ExcelProperty("15")
   private String age15;

   @ExcelProperty("20")
   private String age20;

   @ExcelProperty("25")
   private String age25;

   @ExcelProperty("30")
   private String age30;

   @ExcelProperty("35")
   private String age35;

   @ExcelProperty("40")
   private String age40;

   @ExcelProperty("45")
   private String age45;

   @ExcelProperty("50")
   private String age50;

   @ExcelProperty("55")
   private String age55;

   @ExcelProperty("60")
   private String age60;

   @ExcelProperty("65")
   private String age65;

   @ExcelProperty("70")
   private String age70;

   @ExcelProperty("75")
   private String age75;

   @ExcelProperty("80")
   private String age80;

   @ExcelProperty("85")
   private String age85;

   @ExcelProperty("national_total")
   private String nationalTotal;

   @ExcelProperty("national_male")
   private String nationalMale;

   @ExcelProperty("national_female")
   private String nationalFemale;

   @ExcelProperty("city_total")
   private String cityTotal;

   @ExcelProperty("city_male")
   private String cityMale;

   @ExcelProperty("city_female")
   private String cityFemale;

   @ExcelProperty("village_total")
   private String villageTotal;

   @ExcelProperty("village_male")
   private String villageMale;

   @ExcelProperty("village_female")
   private String villageFemale;

   @ExcelProperty("east_total")
   private String eastTotal;

   @ExcelProperty("east_male")
   private String eastMale;

   @ExcelProperty("east_female")
   private String eastFemale;

   @ExcelProperty("central_total")
   private String centralTotal;

   @ExcelProperty("central_male")
   private String centralMale;

   @ExcelProperty("central_female")
   private String centralFemale;

   @ExcelProperty("west_total")
   private String westTotal;

   @ExcelProperty("west_male")
   private String westMale;

   @ExcelProperty("west_female")
   private String westFemale;
}