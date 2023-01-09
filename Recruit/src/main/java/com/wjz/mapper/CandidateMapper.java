package com.wjz.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjz.entity.Candidate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjz
 * @since 2022-12-27
 */
public interface CandidateMapper extends BaseMapper<Candidate> {

    IPage<Candidate> pageCand(IPage<Candidate> page,  @Param("wq")QueryWrapper<Candidate> wrapper);
}
