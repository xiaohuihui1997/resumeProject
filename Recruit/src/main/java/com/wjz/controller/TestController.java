package com.wjz.controller;


import com.wjz.entity.Result;
import com.wjz.utils.EmailUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private EmailUtil emailUtil;

    /**
     * 发送邮件测试
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @return
     */
    @RequestMapping(value = "/testEmail",method = RequestMethod.POST)
    public Result<String> testEmail(String to,String subject,String content){
        emailUtil.sendMessage(to,subject,content);
        return Result.sucess("发送成功！！！");
    }

}
