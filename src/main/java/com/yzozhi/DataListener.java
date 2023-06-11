package com.yzozhi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataListener extends AnalysisEventListener<Data> {

    // 用于接收解析的所有数据
    private Map<Integer, ReadCellData<?>> headMaps = null;
    private List<Data> allDatas = new ArrayList<>();

    // 提供一个对外访问的方法,返回数据
    public List<Data> getDatas() {
        return allDatas;
    }

    public void clean() {
        allDatas.clear();
    }

    public Map<Integer, ReadCellData<?>> getHeads() {
        return headMaps;
    }

    // 记录解析的数据总数
    int count = 0;

    // 监听表头
    // @Override
    // public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext
    // context) {
    // try {
    // headMaps.putAll(headMap);
    // log.warn("解析到表头:{}", headMap.values());
    // } catch (Exception e) {
    // log.error(e.getMessage());
    // }
    // }

    @Override
    public void invoke(Data data, AnalysisContext Context) {
        // 读取数据加入总数据
        allDatas.add(data);
        count++;
        log.info("解析了第" + count + "条数据");
    }

    // 所有都解析完毕后要执行的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.warn("\n\n================================\n读取了共" + count
                + "条数据\n================================\n");
    }
}
