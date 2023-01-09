package com.wjz.service;

import com.wjz.entity.RecrRecruit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
public interface RecrRecruitService extends IService<RecrRecruit> {

    int insertRecruit(RecrRecruit recrRecruit);

    int deleteRecruit(int recruit_id);

    RecrRecruit queryOne(int recruit_id);

    int updateRecruit(RecrRecruit recrRecruit);

    int closeRecruit(int recruit_id);
}
