package com.wjz.service.impl;

import com.wjz.entity.CandState;
import com.wjz.mapper.CandStateMapper;
import com.wjz.service.CandStateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CandStateServiceImpl extends ServiceImpl<CandStateMapper, CandState> implements CandStateService {

    @Resource
    private CandStateMapper candStateMapper;

    @Override
    public int updateTime(int candidate_id, String inter_time) {
        return candStateMapper.updateTime(candidate_id,inter_time);
    }

    @Override
    public int updateResult(int candidate_id, String retest_time, int state_number, String inter_eval) {
        return candStateMapper.updateResult(candidate_id,retest_time,state_number,inter_eval);
    }

    @Override
    public int updateForward(int candidate_id, String ass_name, String ass_eval) {
        return candStateMapper.updateForward(candidate_id,ass_name,ass_eval);
    }

    @Override
    public int updateCon(int candidate_id, int state_number, String retest_evaluation) {
        return candStateMapper.updateCon(candidate_id,state_number,retest_evaluation);
    }
}
