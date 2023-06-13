package com.yzozhi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallableExcelBuilder implements Callable<String> {
      public final String year;

      public CallableExcelBuilder(String year) {
            this.year = year;
      }

      @Override
      public String call() {
            try {
                  // 计时开始
                  long start = System.currentTimeMillis();
                  log.warn("启动!\t" + year + "开始计时");

                  // 得到文件路径
                  String fileRead = year + "_regex.xlsx";
                  String fileResult = year + "_result.xlsx";

                  // 判断是否存在上次文件
                  if (FileUtils.getFile(fileResult).exists()) {
                        log.error("-->" + year + ":上次生成的文件已存在!退出!请删除上次结果!");
                        return year + "失败!";
                  }

                  // 创建读对象,用于缓存所有数据
                  List<NewData> newDatasAll = new ArrayList<NewData>();
                  // 创建写对象,用于写流
                  ExcelWriter excelWriter = EasyExcelFactory.write(fileResult).build();

                  // 记录数据总数
                  int count = 0;
                  // 循环处理每个sheet
                  String[] sheetName = { "地区数据", "覆盖人口", "全国合计", "全国男", "全国女", "城市合计", "城市男", "城市女", "农村合计", "农村男",
                              "农村女", "全国合计死亡", "全国男死亡", "全国女死亡", "城市合计死亡", "城市男死亡", "城市女死亡", "农村合计死亡", "农村男死亡",
                              "农村女死亡", "东部", "东部城市", "东部农村", "中部", "中部城市", "中部农村", "西部", "西部城市", "西部农村", "东部死亡",
                              "东部城市死亡", "东部农村死亡", "中部死亡", "中部城市死亡", "中部农村死亡", "西部死亡", "西部城市死亡", "西部农村死亡" };
                  for (int i = 0; i < sheetName.length; i++) {
                        // 读取sheet关联数据
                        String sheet = sheetName[i];
                        log.warn("读取sheet:" + sheet);
                        // 读取sheet
                        DataListener datasListener = new DataListener();
                        EasyExcelFactory.read(fileRead, Data.class, datasListener)
                                    .sheet(i)
                                    .doRead();
                        List<Data> datas = datasListener.getDatas();
                        // 读取表头获取返回的结果
                        // Map<Integer, ReadCellData<?>> heads = datasListener.getHeads();

                        // 跳过
                        if (sheet.equals("1")) {
                              log.warn("跳过表" + i);
                        } else {
                              // 判断地区、性别
                              String location = "";
                              String sex = "";
                              if (Regex.regex(sheet, "(城市)", 0).equals("城市")) {
                                    location = "城市";
                              } else if (Regex.regex(sheet, "(农村)", 0).equals("农村")) {
                                    location = "农村";
                              } else if (Regex.regex(sheet, "(全国)", 0).equals("全国")) {
                                    location = "全国";
                              }

                              if (Regex.regex(sheet, "(东部)", 0).equals("东部")) {
                                    location = "东部" + location;
                              } else if (Regex.regex(sheet, "(中部)", 0).equals("中部")) {
                                    location = "中部" + location;
                              } else if (Regex.regex(sheet, "(西部)", 0).equals("西部")) {
                                    location = "西部" + location;
                              }

                              if (Regex.regex(sheet, "(合计)", 0).equals("合计")) {
                                    sex = "合计";
                              } else if (Regex.regex(sheet, "(男)", 0).equals("男")) {
                                    sex = "男";
                              } else if (Regex.regex(sheet, "(女)", 0).equals("女")) {
                                    sex = "女";
                              }
                              log.warn("\n处理了location:" + location + "\tsex:" + sex + "\tage:" + year);
                              // 调用自定义方法，重构数据
                              List<NewData> newDatas = DatasBuilder.datasBuild(datas, year, location, sex);
                              newDatasAll.addAll(newDatas);
                              count += newDatas.size();
                              log.warn("\n================================\n缓存:" + newDatas.size()
                                          + "条数据,释放旧缓存\n================================");
                              datasListener.clean();
                              datas.clear();
                              newDatas.clear();
                        }
                  }
                  // 写入流
                  excelWriter.write(newDatasAll,
                              EasyExcelFactory.writerSheet(0, "result").head(NewData.class).build());
                  excelWriter.finish();

                  // 计时结束
                  long end = System.currentTimeMillis();
                  log.warn("\n================================\n" + year + "完成!共" + count
                              + "条数据\n耗时:" + (end - start) / 1000 + "s\n================================");
            } catch (Exception e) {
                  log.error(e.getMessage());
                  return year + "失败!";
            }
            return year + "成功!";
      }
}