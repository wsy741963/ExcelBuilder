package com.yzozhi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    @org.junit.Test
    public void file() {
        String s = Regex.regex("national_total", "(\\w+)_(\\w+)", 2);
        log.info("Success:" + s);
        // FileHandle.fileRename("2017");
        System.out.println("你好");
        log.info("你好");
    }
}