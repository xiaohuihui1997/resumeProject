package com.wjz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.*;
import com.wjz.service.RecrRecruitService;
import com.wjz.service.RecrStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  主管分配和发布
 *  @since 2022-11-11
 */
@RestController
@RequestMapping("/state")
public class RecrStateController {

    //需求职位service
    @Autowired
    private RecrRecruitService recrRecruitService;

    //状态service
    @Autowired
    private RecrStateService recrStateService;

    /**
     * 未分配的招聘需求
     * 分页和查询
     * 1、是需求职位存在
     * 2、状态为未分配，即is_ass为0
     * 3、使用需求职位名称即key查询
     * pageUtil 分页实体类
     */
    @RequestMapping(value = "/noAssignmentPage",method = RequestMethod.GET)
    public Result<List> noAssignmentPage(PageUtil pageUtil){
        //分页查询
        //条件控制器
        QueryWrapper<RecrRecruit> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.recruit_name",pageUtil.getKeyWord());
        }
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<RecrRecruit> iPage = recrStateService.selectNoAssP(page,wrapper);
        //获取集合数据
        List<RecrRecruit> recrRecruitList = page.getRecords();
        return Result.sucess(recrRecruitList,"获取未分配的需求列表成功",recrRecruitList.size());
    }


    /**
     * 分配
     * @param recruit_id 需求职位id
     * @param assignee 分配人
     * @param evaluation 评价
     */
    @RequestMapping(value = "/Assign",method = RequestMethod.POST)
    public Result<RecrState> Assign(int recruit_id,String assignee,String evaluation){
        int i = recrStateService.Assign(recruit_id,assignee,evaluation);
        //查单个
        RecrState recrState = recrStateService.selectOne(recruit_id);
        if(i>0){
            return Result.sucess("分配成功");
        }else{
            return Result.error("分配失败");
        }
    }

    /**
     * 分页查询未被发布
     */
    @RequestMapping(value = "/NoPublish",method = RequestMethod.GET)
    public Result<List> NoPublish(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<RecrRecruit> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.recruit_name",pageUtil.getKeyWord());
        }
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<RecrRecruit> iPage = recrStateService.NoPublish(page,wrapper);
        List<RecrRecruit> recrRecruitList = page.getRecords();
        return Result.sucess(recrRecruitList,"查询成功!",page.getTotal());
    }

    /*
     * 分页查询发布的
     */
    @RequestMapping(value = "/isPublish",method = RequestMethod.GET)
    public Result<List> isPublish(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<RecrRecruit> wrapper = new QueryWrapper<>();
        //使用条件控制器完成模糊查询
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.recruit_name",pageUtil.getKeyWord());
        }
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<RecrRecruit> iPage = recrStateService.isPublish(page,wrapper); //调用recrStateService
        List<RecrRecruit> recrRecruitList = page.getRecords();
        return Result.sucess(recrRecruitList,"查询成功!",page.getTotal());
    }

    /**
     * 岗位发布
     * @param recruit_id 需求职位id，也就是状态id
     * @param publish_channel 发布的渠道的code值
     */
    @RequestMapping(value = "/Publish",method = RequestMethod.POST)
    public Result<RecrState> Publish(int recruit_id, String publish_channel){
        //发布
        int i = recrStateService.Publish(recruit_id,publish_channel);
        //查询对应id对应的数据供显示
        RecrState recrState = recrStateService.selectOne(recruit_id);
        //判断
        if(i>0){
            return Result.sucess("发布成功!");
        }else{
            return Result.error("发布失败!");
        }
    }

}

