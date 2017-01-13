package com.spring.controller;

import com.spring.util.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjiliang on 2016/12/30.
 */
public abstract class BasicController {

    protected Map<String, Object> resultMap(int code, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.RET_FLAG, code);
        map.put(Constant.RET_MESSAGE, msg);
        return map;
    }
}
