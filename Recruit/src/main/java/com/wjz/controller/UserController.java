package com.wjz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.PageUtil;
import com.wjz.entity.Result;
import com.wjz.entity.User;
import com.wjz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制层
 * @since 2022-11-11
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有
     * 没有分页
     */
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public Result<List> queryAll(){
        List<User> userList = userService.queryAll();
        return Result.sucess(userList,"查询成功!!",userList.size());
    }

    /**
     * 分页
     */
    @RequestMapping(value = "/queryPageAll/{pn}/{rn}",method = RequestMethod.GET)
    public Result<List> queryPageAll(PageUtil pageUtil){
        IPage iPage = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        userService.page(iPage);
        return Result.sucess(iPage.getRecords(),"获取用户列表成功", iPage.getTotal());
    }


}

