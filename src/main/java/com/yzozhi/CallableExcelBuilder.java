package com.yzozhi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;

import com.alibaba.excel.EasyExcelFactory;

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
                  String fileRead = year + ".xlsx";
                  String fileCache = year + "_cache.xlsx";
                  String fileResult = year + "_result.xlsx";

                  // 判断是否存在上次文件
                  if (FileUtils.getFile(fileResult).exists()
                              || FileUtils.getFile(fileCache).exists()) {
                        log.error("-->" + year + ":上次生成的文件已存在!退出!请删除上次结果!");
                        return year + "失败!";
                  }

                  // 写入表头
                  List<NewData> emptyDatas = new ArrayList<>();
                  EasyExcelFactory.write(fileCache, NewData.class).sheet("result").needHead(true)
                              .doWrite(emptyDatas);

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
                              if (Regex.regex(sheet, "(城市)", 1).equals("城市")) {
                                    location = "城市";
                              } else if (Regex.regex(sheet, "(农村)", 1).equals("农村")) {
                                    location = "农村";
                              } else if (Regex.regex(sheet, "(全国)", 1).equals("全国")) {
                                    location = "全国";
                              }

                              if (Regex.regex(sheet, "(东部)", 1).equals("东部")) {
                                    location = "东部" + location;
                              } else if (Regex.regex(sheet, "(中部)", 1).equals("中部")) {
                                    location = "中部" + location;
                              } else if (Regex.regex(sheet, "(西部)", 1).equals("西部")) {
                                    location = "西部" + location;
                              }

                              if (Regex.regex(sheet, "(合计)", 1).equals("合计")) {
                                    sex = "合计";
                              } else if (Regex.regex(sheet, "(男)", 1).equals("男")) {
                                    sex = "男";
                              } else if (Regex.regex(sheet, "(女)", 1).equals("女")) {
                                    sex = "女";
                              }
                              log.warn("location:" + location + "\tsex:" + sex + "\tage:" + year);
                              // 调用自定义方法，重构数据
                              List<NewData> newDatas = DatasBuilder.datasBuild(datas, year, location, sex);

                              // 写入主表格
                              EasyExcelFactory.write(fileResult, NewData.class)
                                          // 是否在原有数据上追加写入数据，否则直接清空
                                          .withTemplate(fileCache)
                                          .sheet()
                                          // 是否写入表头
                                          .needHead(false)
                                          .doWrite(newDatas);
                              count += newDatas.size();
                              log.warn("\n================================\n写入主表格:" + newDatas.size()
                                          + "条数据,释放缓存\n================================");
                              FileHandle.fileRename(year);
                              datasListener.clean();
                              datas.clear();
                              newDatas.clear();
                              // Thread.sleep(1000);
                        }
                  }

                  // 重命名
                  if (FileUtils.getFile(fileCache).exists()) {
                        FileUtils.moveFile(new File(fileCache), new File(fileResult));
                        // Thread.sleep(200);
                        log.info("-->" + fileCache + "重命名成功");
                  } else {
                        log.warn("-->" + fileCache + "不存在");
                  }

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