package com.wjz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjz.entity.CandResume;
import com.wjz.entity.Candidate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjz.entity.RecrRecruit;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2022-12-27
 */
public interface CandidateService extends IService<Candidate> {

    IPage<Candidate> pageCand(IPage<Candidate> page, QueryWrapper<Candidate> wrapper);
}
