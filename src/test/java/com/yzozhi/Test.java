package com.yzozhi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    @org.junit.Test
    public void file() {
        FileHandle.fileRename("2020");
        String s = ExcelRegex.reFilter("Q,IL()7)!oJ、i");
        log.info("result:" + s);
    }
}