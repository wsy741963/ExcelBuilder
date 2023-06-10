package com.yzozhi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    @org.junit.Test
    public void file() {
        FileHandle.fileRename("2020");
        String s = ExcelRegex.reFilter("L()7)!oJ、");
        log.info("Success:" + s);
    }
}