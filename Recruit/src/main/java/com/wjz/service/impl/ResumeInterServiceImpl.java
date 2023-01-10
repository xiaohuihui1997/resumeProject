package com.wjz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjz.entity.ResumeInter;
import com.wjz.mapper.ResumeInterMapper;
import com.wjz.service.ResumeInterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjz
 * @since 2023-01-09
 */
@Service
public class ResumeInterServiceImpl extends ServiceImpl<ResumeInterMapper, ResumeInter> implements ResumeInterService {

    @Resource
    private ResumeInterMapper resumeInterMapper;

    @Override
    public int appraise(int resume_id, int is_pass, String inter_appraise) {
        return resumeInterMapper.appraise(resume_id,is_pass,inter_appraise);
    }

    @Override
    public int transmitte(int resume_id, int assign_id, String assign_appraise) {
        return resumeInterMapper.transmitte(resume_id,assign_id,assign_appraise);
    }

    @Override
    public int NotPresent(int resume_id, String absence_appraise) {
        return resumeInterMapper.NotPresent(resume_id,absence_appraise);
    }
}
