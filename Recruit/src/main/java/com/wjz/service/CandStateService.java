package com.wjz.service;

import com.wjz.entity.CandState;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2022-12-27
 */
public interface CandStateService extends IService<CandState> {
    int updateTime(int candidate_id, String inter_time);

    int updateResult(int candidate_id, String retest_time, int state_number, String inter_eval);

    int updateForward(int candidate_id, String ass_name, String ass_eval);

    int updateCon(int candidate_id, int state_number, String retest_evaluation);
}
