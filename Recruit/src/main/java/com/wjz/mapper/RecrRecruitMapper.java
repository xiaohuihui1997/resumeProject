package com.wjz.mapper;

import com.wjz.entity.RecrRecruit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Repository
@Mapper
public interface RecrRecruitMapper extends BaseMapper<RecrRecruit> {

    int closeRecruit(int recruit_id);
}
