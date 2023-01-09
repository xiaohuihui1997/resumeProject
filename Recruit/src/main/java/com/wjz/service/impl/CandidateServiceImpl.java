package com.wjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjz.entity.CandResume;
import com.wjz.entity.Candidate;
import com.wjz.mapper.CandidateMapper;
import com.wjz.service.CandidateService;
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
public class CandidateServiceImpl extends ServiceImpl<CandidateMapper, Candidate> implements CandidateService {

    @Resource
    private CandidateMapper candidateMapper;


    @Override
    public IPage<Candidate> pageCand(IPage<Candidate> page, QueryWrapper<Candidate> wrapper) {
        return candidateMapper.pageCand(page,wrapper);
    }
}
