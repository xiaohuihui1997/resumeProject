package com.wjz.controller;


import com.wjz.entity.CandState;
import com.wjz.entity.RecrRecruit;
import com.wjz.entity.Result;
import com.wjz.service.CandStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 候选人对应的状态类
 * @author wjz
 * @since 2022-12-27
 */
@RestController
@RequestMapping("/cand_state")
public class CandStateController {

    @Autowired
    private CandStateService candStateService;

    /**
     * 面试
     * 设置面试时间
     */
    @RequestMapping(value = "/updateTime",method = RequestMethod.POST)
    private Result<CandState> updateTime(int candidate_id,String inter_time){
        int update = candStateService.updateTime(candidate_id,inter_time);
        //判断输出
        if(update>0){
            return Result.sucess("设置面试时间成功!");
        }else{
            return Result.error("设置面试时间失败!");
        }
    }

    /**
     * 面试结论
     * 设置面试结论
     */
    @RequestMapping(value = "/updateResult",method = RequestMethod.POST)
    private Result<CandState> updateResult(int candidate_id,String retest_time,int state_number,String inter_eval){
        int update = candStateService.updateResult(candidate_id,retest_time,state_number,inter_eval);
        //判断输出
        if(update>0){
            return Result.sucess("设置面试结论成功!");
        }else{
            return Result.error("设置面试结论失败!");
        }
    }

    /**
     * 转发
     */
    @RequestMapping(value = "/updateForward",method = RequestMethod.POST)
    private Result<CandState> updateForward(int candidate_id,String ass_name,String ass_eval){
        int update = candStateService.updateForward(candidate_id,ass_name,ass_eval);
        //判断输出
        if(update>0){
            return Result.sucess("转发成功!");
        }else{
            return Result.error("转发失败!");
        }
    }

    /**
     * 复试综合评价以及结论
     */
    @RequestMapping(value = "/updateCon",method = RequestMethod.POST)
    private Result<CandState> updateCon(int candidate_id,int state_number,String retest_evaluation){
        int update = candStateService.updateCon(candidate_id,state_number,retest_evaluation);
        //判断输出
        if(update>0){
            return Result.sucess("转发成功!");
        }else{
            return Result.error("转发失败!");
        }
    }

}

