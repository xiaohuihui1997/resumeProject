package com.wjz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.PageUtil;
import com.wjz.entity.Result;
import com.wjz.entity.ResumeInter;
import com.wjz.service.ResumeInterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjz
 * @since 2023-01-09
 */
@RestController
@RequestMapping("/resume-inter")
public class ResumeInterController {

    @Resource
    private ResumeInterService resumeInterService;

    /**
     * 查询待面试的
     * @param pageUtil
     * @return
     */
    /**
     * 查询待面试的
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/queryPenInter",method = RequestMethod.GET)
    public Result<List> queryPenInter(PageUtil pageUtil,int interPersonId){
        IPage<ResumeInter> page = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        //条件控制器
        QueryWrapper<ResumeInter> qw = new QueryWrapper<>();
        //判断搜索的关键字是否存在
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            qw.like("inter_post",pageUtil.getKeyWord());
        }
        qw.eq("is_close",0);
        qw.eq("inter_person_id",interPersonId);
        qw.eq("inter_state",1);
        resumeInterService.page(page,qw);
        return Result.sucess(page.getRecords(),"获取用户列表成功",page.getTotal());
    }


    /**
     * 评价
     * @param resume_id 候选人的简历id
     * @param is_pass 是否通过
     * @param inter_appraise 综合评价
     * @return
     */
    @RequestMapping(value = "/queryPenInter",method = RequestMethod.POST)
    public Result<ResumeInter> appraise(int resume_id,int is_pass,String inter_appraise){
        int update = resumeInterService.appraise(resume_id,is_pass,inter_appraise);
        if(update>0){
            return Result.sucess("评价成功！！");
        }else{
            return Result.sucess("评价失败！！");
        }

    }

    /**
     * 转交
     * @param resume_id
     * @param assign_id
     * @param assign_appraise
     * @return
     */
    @RequestMapping(value = "/transmitte",method = RequestMethod.POST)
    public Result<ResumeInter> transmitte(int resume_id,int assign_id,String assign_appraise){
        int update = resumeInterService.transmitte(resume_id,assign_id,assign_appraise);
        if(update>0){
            return Result.sucess("转交成功！！");
        }else{
            return Result.sucess("转交失败！！");
        }

    }

    /**
     * 未到场
     * @param resume_id
     * @param absence_appraise
     * @return
     */
    @RequestMapping(value = "/NotPresent",method = RequestMethod.POST)
    public Result<ResumeInter> NotPresent(int resume_id,String absence_appraise){
        int update = resumeInterService.NotPresent(resume_id,absence_appraise);
        if(update>0){
            return Result.sucess("提交成功！！");
        }else{
            return Result.sucess("提交失败！！");
        }

    }


    /**
     * 查询未评价的
     * @param pageUtil
     * interPersonId 面试官id
     * @return
     */
    @RequestMapping(value = "/queryNoRate",method = RequestMethod.GET)
    public Result<List> queryNoRate(PageUtil pageUtil,int interPersonId){
        IPage<ResumeInter> page = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        //条件控制器
        QueryWrapper<ResumeInter> qw = new QueryWrapper<>();
        //判断搜索的关键字是否存在
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            qw.like("inter_post",pageUtil.getKeyWord());
        }
        qw.eq("is_close",0);
        qw.eq("inter_state",2);
        qw.eq("inter_person_id",interPersonId);
        resumeInterService.page(page,qw);
        return Result.sucess(page.getRecords(),"获取用户列表成功",page.getTotal());
    }

    /**
     * 查询已经完成的
     * @param pageUtil
     * interPersonId 面试官id
     * @return
     */
    @RequestMapping(value = "/queryCompleted",method = RequestMethod.GET)
    public Result<List> queryCompleted(PageUtil pageUtil,int interPersonId){
        IPage<ResumeInter> page = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        //条件控制器
        QueryWrapper<ResumeInter> qw = new QueryWrapper<>();
        //判断搜索的关键字是否存在
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            qw.like("inter_post",pageUtil.getKeyWord());
        }
        qw.eq("is_close",0);
        qw.eq("inter_state",3);
        qw.eq("inter_person_id",interPersonId);
        resumeInterService.page(page,qw);
        return Result.sucess(page.getRecords(),"获取用户列表成功",page.getTotal());
    }

}

