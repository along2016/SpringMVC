package com.spring.util;

import java.util.StringTokenizer;

/**
 * Created by wangjiliang on 2017/1/3.
 */
public class CommonUtils {

    /**
     * 将String字符串转化成Integer数组
     * @param str
     * @return
     */
    public static Integer[] StringtoIntArr(String str) {
        Integer ret[] = new Integer[str.length()];
        StringTokenizer toKenizer = new StringTokenizer(str, ",");
        int i = 0;
        while (toKenizer.hasMoreElements()) {
            ret[i++] = Integer.valueOf(toKenizer.nextToken());
        }
        return ret;
    }
}
