package com.wjz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjz.entity.RecrRecruit;
import com.wjz.entity.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface ResumeMapper extends BaseMapper<Resume> {
    Resume queryResumeByIdCard(@Param("identityCardNumber") String identityCardNumber);

    int updateIsDeleteById(@Param("id") Integer id);
}
