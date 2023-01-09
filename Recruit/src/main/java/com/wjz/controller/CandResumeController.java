package com.wjz.controller;


import com.wjz.entity.CandResume;
import com.wjz.entity.Result;
import com.wjz.service.CandResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 候选人对应的简历类
 * @author wjz
 * @since 2022-12-27
 */
@RestController
@RequestMapping("/cand_resume")
public class CandResumeController {

    @Autowired
    private CandResumeService candResumeService;

    /**
     * 查看简历
     */
    @RequestMapping(value = "/queryResume/{resume_id}",method = RequestMethod.GET)
    private Result<CandResume> queryResume(@PathVariable int resume_id){
        CandResume candResume = candResumeService.queryResume(resume_id);
        //判断
        if(candResume==null){
            return Result.error("查看失败!");
        }else{
            return Result.sucess(candResume,"查看成功!",1);
        }
    }

}

