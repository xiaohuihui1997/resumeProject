package com.wjz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjz.entity.ResumeProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    int updateIsDeleteByResumeId(@Param("resumeId") Integer resumeId);

    ResumeProcess selectByResumeId(Integer resumeId, Integer status, String isDelete);

    List<ResumeProcess> selectByResumeIds(List<Integer> resumeIds, String isDelete);
}
