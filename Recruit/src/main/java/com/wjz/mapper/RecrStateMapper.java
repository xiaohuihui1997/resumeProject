package com.wjz.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.RecrRecruit;
import com.wjz.entity.RecrState;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Repository
@Mapper
public interface RecrStateMapper extends BaseMapper<RecrState> {

    //添加职位对应的状态
    int addState(Integer recruitId);

    //分配操作
    int updateRecrState(int recruit_id, @Param("assignee")String assignee, @Param("evaluation")String evaluation);

    //查询未被分配的
    IPage<RecrRecruit> selectNoAssP(IPage<RecrRecruit> page, @Param("ew") QueryWrapper<RecrRecruit> wrapper);
    //分页查询未被发布的
    IPage<RecrRecruit> noPublish(IPage<RecrRecruit> page, @Param("wq")QueryWrapper<RecrRecruit> wrapper);

    //查询发布的
    IPage<RecrRecruit> isPublish(IPage<RecrRecruit> page, @Param("qw")QueryWrapper<RecrRecruit> wrapper);

    //发布操作
    int publish(int recruit_id, String publish_channel);




}
