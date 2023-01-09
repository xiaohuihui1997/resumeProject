package com.wjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.RecrRecruit;
import com.wjz.entity.RecrState;
import com.wjz.mapper.RecrStateMapper;
import com.wjz.service.RecrStateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Service
public class RecrStateServiceImpl extends ServiceImpl<RecrStateMapper, RecrState> implements RecrStateService {

    @Autowired
    private RecrStateMapper recrStateMapper;

    //增加职位对应的状态
    @Override
    public int addState(Integer recruitId) {
        return recrStateMapper.addState(recruitId);
    }

    //删除职位的时候该对应的状态也要删除
    @Override
    public int deleteState(Integer recruit_id) {
        return recrStateMapper.deleteById(recruit_id);
    }

    //分配操作
    @Override
    public int Assign(int recruit_id, String assignee, String evaluation) {
        return recrStateMapper.updateRecrState(recruit_id,assignee,evaluation);
    }

    //根据id查询一个
    @Override
    public RecrState selectOne(int recruit_id) {
        return recrStateMapper.selectById(recruit_id);
    }



    //查询未被分配的
    @Override
    public IPage<RecrRecruit> selectNoAssP(IPage<RecrRecruit> page, QueryWrapper<RecrRecruit> wrapper) {
        return recrStateMapper.selectNoAssP(page,wrapper);
    }
    /**
     * 未被发布
     */
    @Override
    public IPage<RecrRecruit> NoPublish(IPage<RecrRecruit> page, QueryWrapper<RecrRecruit> wrapper) {
        return recrStateMapper.NoPublish(page,wrapper);
    }

    //被发布的
    public IPage<RecrRecruit> isPublish(IPage<RecrRecruit> page, QueryWrapper<RecrRecruit> wrapper) {
        return recrStateMapper.isPublish(page,wrapper);
    }

    //发布操作
    public int Publish(int recruit_id, String publish_channel) {
        return recrStateMapper.Publish(recruit_id,publish_channel);
    }




}
