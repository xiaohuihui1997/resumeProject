package com.wjz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjz.entity.Resume;
import com.wjz.entity.ResumeProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hcb
 * @since 2023-01-09
 */
@Repository
@Mapper
public interface ResumeProcessMapper extends BaseMapper<ResumeProcess> {

}
