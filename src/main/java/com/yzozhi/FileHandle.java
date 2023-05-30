package com.yzozhi;

import java.io.File;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileHandle {
    public static void fileRename(String year) {
        try {
            if (FileUtils.getFile(year + "_cache.xlsx").exists()) {
                FileUtils.forceDelete(new File(year + "_cache.xlsx"));
                // Thread.sleep(200);
                log.info("-->" + year + "_cache.xlsx删除成功");
            } else {
                log.info("-->" + year + "_cache.xlsx不存在");
            }

            if (FileUtils.getFile(year + "_result.xlsx").exists()) {
                FileUtils.moveFile(new File(year + "_result.xlsx"), new File(year + "_cache.xlsx"));
                // Thread.sleep(200);
                log.info("-->" + year + "_result.xlsx重命名成功");
            } else {
                log.info("-->" + year + "_result.xlsx不存在");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}