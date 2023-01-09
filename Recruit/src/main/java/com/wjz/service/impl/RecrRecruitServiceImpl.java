package com.wjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjz.entity.RecrRecruit;
import com.wjz.mapper.RecrRecruitMapper;
import com.wjz.service.RecrRecruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Service
public class RecrRecruitServiceImpl extends ServiceImpl<RecrRecruitMapper, RecrRecruit> implements RecrRecruitService {

    @Resource
    private RecrRecruitMapper recrRecruitMapper;

    /**
     * 增加
     * @param recrRecruit
     * @return
     */
    @Override
    public int insertRecruit(RecrRecruit recrRecruit) {
        return recrRecruitMapper.insert(recrRecruit);
    }

    /**
     * 删除
     * @param recruit_id
     * @return
     */
    @Override
    public int deleteRecruit(int recruit_id) {
        return recrRecruitMapper.deleteById(recruit_id);
    }

    /**
     * 通过id查询
     * @param recruit_id
     * @return
     */
    @Override
    public RecrRecruit queryOne(int recruit_id) {
        return recrRecruitMapper.selectById(recruit_id);
    }

    /**
     * 修改
     * @param recrRecruit
     * @return
     */
    @Override
    public int updateRecruit(RecrRecruit recrRecruit) {
        return recrRecruitMapper.updateById(recrRecruit);
    }

    /**
     * 关闭需求职位
     * @param recruit_id
     * @return
     */
    @Override
    public int closeRecruit(int recruit_id) {
        return recrRecruitMapper.closeRecruit(recruit_id);
    }
}
