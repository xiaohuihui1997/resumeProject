package com.wjz.service;

import com.wjz.entity.CandResume;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2022-12-27
 */
public interface CandResumeService extends IService<CandResume> {

    CandResume queryResume(int resume_id);
}
