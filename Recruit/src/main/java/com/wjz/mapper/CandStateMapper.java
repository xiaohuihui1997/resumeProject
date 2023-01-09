package com.wjz.mapper;

import com.wjz.entity.CandState;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjz
 * @since 2022-12-27
 */
public interface CandStateMapper extends BaseMapper<CandState> {

    int updateTime(int candidate_id, @Param("inter_time") String inter_time);

    int updateResult(int candidate_id, @Param("retest_time")String retest_time, int state_number, @Param("inter_eval")String inter_eval);

    int updateForward(int candidate_id, @Param("ass_name")String ass_name, @Param("ass_eval")String ass_eval);

    int updateCon(int candidate_id, int state_number, @Param("retest_evaluation")String retest_evaluation);
}
