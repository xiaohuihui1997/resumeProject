package com.wjz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.PageUtil;
import com.wjz.entity.RecrRecruit;
import com.wjz.entity.Result;
import com.wjz.service.RecrRecruitService;
import com.wjz.service.RecrStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 招聘职位需求控制层
 * @since 2022-11-11
 */
@RestController
@RequestMapping("/recruit")
public class RecrRecruitController {

    //需求职位service
    @Autowired
    private RecrRecruitService recrRecruitService;

    //状态service
    @Autowired
    private RecrStateService recrStateService;

    /**
     * 我的招聘
     * 查询未关闭的需求职位
     * 分页和模糊查询
     * pageUtil 分页实体类
     */
    @RequestMapping(value = "/pageRecruit",method = RequestMethod.GET)
    public Result<List> pageRecruit(PageUtil pageUtil){
        IPage<RecrRecruit> page = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        //条件控制器
        QueryWrapper<RecrRecruit> qw = new QueryWrapper<>();
        //判断搜索的关键字是否存在
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            qw.like("recruit_name",pageUtil.getKeyWord());
        }
            qw.eq("is_close",0);
        recrRecruitService.page(page,qw);
        return Result.sucess(page.getRecords(),"获取用户列表成功",page.getTotal());
    }

    /**
     * 已经关闭的招聘
     * 查询已经关闭的需求职位
     * 分页和模糊查询
     * 前提是存在的
     */
    @RequestMapping(value = "/closePageRecruit",method = RequestMethod.GET)
    public Result<List> closePageRecruit(PageUtil pageUtil){
        IPage<RecrRecruit> page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize());
        QueryWrapper<RecrRecruit> qw = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            qw.like("recruit_name",pageUtil.getKeyWord());
        }
            //先筛选存在的
            qw.eq("is_delete",0);
            //筛选关闭的
            qw.eq("is_close",1);
        recrRecruitService.page(page,qw);
        return Result.sucess(page.getRecords(),"获取用户列表成功",page.getTotal());
    }

    /**
     * 全部招聘
     * 查询所有，包括关闭和未关闭的
     * 前提是存在的
     */
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public Result<List> queryAll(PageUtil pageUtil){
        IPage<RecrRecruit> page = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        QueryWrapper<RecrRecruit> qw = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            qw.like("recruit_name",pageUtil.getKeyWord());
        }
            //筛选存在的
            qw.eq("is_delete",0);
        recrRecruitService.page(page,qw);
        return Result.sucess(page.getRecords(),"获取用户列表成功",page.getTotal());
    }

    /**
     * 新增需求
     * 增加需求职位
     * @param recrRecruit 需求职位
     */
    @RequestMapping(value = "/insertRecruit",method = RequestMethod.POST)
    private Result<RecrRecruit> insertRecruit(@RequestBody RecrRecruit recrRecruit){
        //增加数据
        int insert = recrRecruitService.insertRecruit(recrRecruit);
        //增加对应的状态表
        recrStateService.addState(recrRecruit.getRecruitId());
        //判断并且输出
        if(insert>0){
            return Result.sucess("添加成功!");
        }else{
            return Result.error("增加失败!");
        }
    }

    /**
     * 删除
     * 通过id删除需求职位
     * 逻辑删除
     */
    @RequestMapping(value = "/deleteRecruit/{recruit_id}",method = RequestMethod.POST)
    private Result<RecrRecruit> deleteRecruit(@PathVariable int recruit_id){
        //逻辑删除需求职位表
        int delete = recrRecruitService.deleteRecruit(recruit_id);
        //裸机删除附带的状态表
        recrStateService.deleteState(recruit_id);
        if(delete>0){
            return Result.sucess("删除成功!");
        }else{
            return Result.error("删除失败!");
        }
    }

    /**
     * 查看
     * 通过id查询需求职位
     * 为了展示数据到页面上
     * @param recruit_id  需求职位id
     */
    @RequestMapping(value = "/queryOne/{recruit_id}",method = RequestMethod.GET)
    private Result<RecrRecruit> queryOne(@PathVariable int recruit_id){
        RecrRecruit recrRecruit= recrRecruitService.queryOne(recruit_id);
        //判断
        if(recrRecruit==null){
            return Result.error("查询失败!");
        }else{
            return Result.sucess(recrRecruit,"查询成功!",1);
        }
    }

    /**
     * 编辑
     * 修改需求职位
     * @param recrRecruit  需求职位id
     */
    @RequestMapping(value = "/updateRecruit",method = RequestMethod.POST)
    private Result<RecrRecruit> updateRecruit(RecrRecruit recrRecruit){
        int update = recrRecruitService.updateRecruit(recrRecruit);
        //根据id查询单个信息
        RecrRecruit recrRecruit1= recrRecruitService.queryOne(recrRecruit.getRecruitId());
        //判断输出
        if(update>0){
            return Result.sucess("修改成功!");
        }else{
            return Result.error("修改失败!");
        }
    }

    /**
     * 关闭需求
     * 关闭需求职位
     * 批量关闭
     * 即设置字段is_close为1
     * @param recruit_id 需求职位id
     */
    @RequestMapping(value = "/closeRecruit/{recruit_id}",method = RequestMethod.POST)
    public Result<RecrRecruit> closeRecruit(@PathVariable int recruit_id[]) {
        //进行批量关闭
        for (int re_id : recruit_id) {
            int close = recrRecruitService.closeRecruit(re_id);
        }
        return Result.sucess("关闭成功!");
    }
}

