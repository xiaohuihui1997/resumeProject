package com.wjz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.RecrRecruit;
import com.wjz.entity.RecrState;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  需求职位的服务类
 * @author wjz
 * @since 2022-11-11
 */
public interface RecrStateService extends IService<RecrState> {
    //添加需求职位时候添加对应的状态
    int addState(Integer recruitId);
    //删除需求职位时候删除对应的状态
    int deleteState(Integer recruit_id);
    //分配操作
    int Assign(int recruit_id, String assignee, String evaluation);
    //根据id查询
    RecrState selectOne(int recruit_id);
    //查询未被发布的
    IPage<RecrRecruit> NoPublish(IPage<RecrRecruit> page, QueryWrapper<RecrRecruit> wrapper);
    //查询发布的
    IPage<RecrRecruit> isPublish(IPage<RecrRecruit> page, QueryWrapper<RecrRecruit> wrapper);
    //发布操作
    int Publish(int recruit_id, String publish_channel);
    //分页查询未被分配的
    IPage<RecrRecruit> selectNoAssP(IPage<RecrRecruit> page, QueryWrapper<RecrRecruit> wrapper);


}
