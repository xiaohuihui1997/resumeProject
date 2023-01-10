package com.wjz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjz.entity.ResumeInter;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjz
 * @since 2023-01-09
 */
public interface ResumeInterMapper extends BaseMapper<ResumeInter> {

    int appraise(int resume_id, int is_pass, String inter_appraise);

    int transmitte(int resume_id, int assign_id, String assign_appraise);

    int NotPresent(int resume_id, String absence_appraise);
}
