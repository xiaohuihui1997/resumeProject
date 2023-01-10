package com.wjz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjz.entity.ResumeInter;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2023-01-09
 */
public interface ResumeInterService extends IService<ResumeInter> {

    int appraise(int resume_id, int is_pass, String inter_appraise);

    int transmitte(int resume_id, int assign_id, String assign_appraise);

    int NotPresent(int resume_id, String absence_appraise);
}
