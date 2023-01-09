package com.wjz.controller;


import com.wjz.entity.RecrCodeDefine;
import com.wjz.entity.Result;
import com.wjz.service.RecrCodeDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @since 2022-11-11
 */
@RestController
@RequestMapping("/code")
public class RecrCodeDefineController {

    @Autowired
    private RecrCodeDefineService recrCodeDefineService;

    /**
     * 查询所有的codedefine
     * @return
     */
    @RequestMapping("/queryAllCode")
    public Result<List> queryAllCode(){
        List<RecrCodeDefine> list = recrCodeDefineService.queryAllCode();
        return Result.sucess(list,"查询成功",list.size());
    }

    /**
     * 通过code查询单个
     * @return
     */
    @RequestMapping("/queryByCode/{code}")
    public Result<RecrCodeDefine> queryByCode(@PathVariable int code){
        RecrCodeDefine codeDefine = recrCodeDefineService.queryByCode(code);
        return Result.sucess(codeDefine,"查询成功",1);
    }


}

