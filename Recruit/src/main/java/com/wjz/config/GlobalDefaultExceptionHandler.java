package com.wjz.config;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> defaultErrorHandler(HttpServletRequest req, Exception e) {
        System.out.println("自定义异常：");
        e.printStackTrace();
        System.out.println("excHandler.defaultExceptionHandler执行结束");
        return ImmutableMap.<String,Object>builder().put("error","系统出现错误").put("code",502).put("msg",e.getMessage()).put("isSuccess",false).build();
//        HashMap<String, Object> obj = new HashMap<>();
//        obj.put("code", 502);
//        obj.put("error","系统出现错误");
//        obj.put("msg", e.getMessage());
//        obj.put("isSuccess", false);
//        return obj;
    }
}
