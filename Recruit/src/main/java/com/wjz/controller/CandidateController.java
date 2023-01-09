package com.wjz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.*;
import com.wjz.service.CandidateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 候选人控制类
 * @author wjz
 * @since 2022-12-27
 */
@RestController
@RequestMapping("/cand")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    /**
     * 查询候选人(在简历库的)
     */
    @RequestMapping(value = "/pageResLib",method = RequestMethod.GET)
    public Result<List> pageResLib(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.post_name",pageUtil.getKeyWord());
        }
        wrapper.eq("t2.state_number",0);
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<Candidate> iPage = candidateService.pageCand(page,wrapper);
        List<Candidate> candidateList = page.getRecords();
        return Result.sucess(candidateList,"查询成功!",page.getTotal());
    }

    /**
     * 查询候选人(在待初筛的)
     */
    @RequestMapping(value = "/pageCandOne",method = RequestMethod.GET)
    public Result<List> pageCandOne(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.post_name",pageUtil.getKeyWord());
        }
        wrapper.eq("t2.state_number",1);
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<Candidate> iPage = candidateService.pageCand(page,wrapper);
        List<Candidate> candidateList = page.getRecords();
        return Result.sucess(candidateList,"查询成功!",page.getTotal());
    }

    /**
     * 查询候选人(在初筛的)
     */
    @RequestMapping(value = "/pageCandTwo",method = RequestMethod.GET)
    public Result<List> pageCandTwo(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.post_name",pageUtil.getKeyWord());
        }
        wrapper.eq("t2.state_number",2);
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<Candidate> iPage = candidateService.pageCand(page,wrapper);
        List<Candidate> candidateList = page.getRecords();
        return Result.sucess(candidateList,"查询成功!",page.getTotal());
    }

    /**
     * 查询候选人(在面试的)
     */
    @RequestMapping(value = "/pageCandThree",method = RequestMethod.GET)
    public Result<List> pageCandThree(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.post_name",pageUtil.getKeyWord());
        }
        wrapper.eq("t2.state_number",3);
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<Candidate> iPage = candidateService.pageCand(page,wrapper);
        List<Candidate> candidateList = page.getRecords();
        return Result.sucess(candidateList,"查询成功!",page.getTotal());
    }

    /**
     * 查询候选人(在复试的)
     */
    @RequestMapping(value = "/pageCandFour",method = RequestMethod.GET)
    public Result<List> pageCandFour(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.post_name",pageUtil.getKeyWord());
        }
        wrapper.eq("t2.state_number",4);
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<Candidate> iPage = candidateService.pageCand(page,wrapper);
        List<Candidate> candidateList = page.getRecords();
        return Result.sucess(candidateList,"查询成功!",page.getTotal());
    }

    /**
     * 查询候选人(在发送offer的)
     */
    @RequestMapping(value = "/pageCandFive",method = RequestMethod.GET)
    public Result<List> pageCandFive(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.post_name",pageUtil.getKeyWord());
        }
        wrapper.eq("t2.state_number",5);
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<Candidate> iPage = candidateService.pageCand(page,wrapper);
        List<Candidate> candidateList = page.getRecords();
        return Result.sucess(candidateList,"查询成功!",page.getTotal());
    }

    /**
     * 查询候选人(被聘用的)
     */
    @RequestMapping(value = "/pageCandSix",method = RequestMethod.GET)
    public Result<List> pageCandSix(PageUtil pageUtil){
        //复杂的分页查询
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            //模糊查询
            wrapper.like("t1.post_name",pageUtil.getKeyWord());
        }
        wrapper.eq("t2.state_number",6);
        IPage page = new Page(pageUtil.getPageNum(), pageUtil.getPageSize()); //设置页索引入
        //调用recrStateService完成多表联查
        IPage<Candidate> iPage = candidateService.pageCand(page,wrapper);
        List<Candidate> candidateList = page.getRecords();
        return Result.sucess(candidateList,"查询成功!",page.getTotal());
    }



}

