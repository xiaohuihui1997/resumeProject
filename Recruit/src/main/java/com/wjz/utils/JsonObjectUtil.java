package com.wjz.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonObjectUtil {

    public static JSONObject returnData(Integer code, String msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject;
    }
}
