package com.yzozhi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
public class Regex {
    // 输入参数:需匹配原字符串，正则表达式用括号分组，分组参数
    public static String regex(String str, String regex, int group) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String re = matcher.group(group);
                // log.info("匹配到:" + re);
                return re;
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
}