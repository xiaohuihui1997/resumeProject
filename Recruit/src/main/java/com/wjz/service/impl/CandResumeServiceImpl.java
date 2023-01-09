package com.wjz.service.impl;

import com.wjz.entity.CandResume;
import com.wjz.mapper.CandResumeMapper;
import com.wjz.service.CandResumeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjz
 * @since 2022-12-27
 */
@Service
public class CandResumeServiceImpl extends ServiceImpl<CandResumeMapper, CandResume> implements CandResumeService {

    @Resource
    private CandResumeMapper candResumeMapper;

    @Override
    public CandResume queryResume(int resume_id) {
        return candResumeMapper.selectById(resume_id);
    }
}
