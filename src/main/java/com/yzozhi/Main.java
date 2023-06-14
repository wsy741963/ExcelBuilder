package com.yzozhi;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("\n请选择运行的程序:\n1.数据处理\n2.数据校验\n");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            scanner.close();

            if (input != 1) {
                ExcelRegex.excelRegex();
                return;
            }

            long start = System.currentTimeMillis();
            // 创建线程池
            ExecutorService pool = new ThreadPoolExecutor(5, 8, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5),
                    Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
            // 遍历目录下文件，获取集合
            Collection<File> fl = FileUtils.listFiles(new File("./"), null, false);
            List<Callable<String>> tasks = new ArrayList<>();
            for (File file : fl) {
                String fn = Regex.regex(file.getName(), "^20\\d{2}_regex\\.xlsx?$", 0);
                if (!fn.equals("")) {
                    log.warn("发现文件:" + fn);
                    String year = fn.substring(0, 4);
                    // 使用文件参数生成线程对象
                    Callable<String> call = new CallableExcelBuilder(year);
                    // 加入线程集合
                    tasks.add(call);
                }
            }

            // 执行所有线程，返回执行结果
            List<Future<String>> result = pool.invokeAll(tasks);
            for (Future<String> future : result) {
                log.warn(future.get());
            }
            pool.shutdown();
            long end = System.currentTimeMillis();
            log.warn("\n================================\n总耗时:" + (end - start) / 1000
                    + "s\n================================");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}