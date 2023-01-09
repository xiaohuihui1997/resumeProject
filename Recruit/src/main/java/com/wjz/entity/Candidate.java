package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 候选人类
 * @author wjz
 * @since 2022-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Candidate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "candidate_id", type = IdType.AUTO)
    private Integer candidateId;

    /**
     * 候选人姓名
     */
    private String candidateName;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 工作年限
     */
    private Integer workYears;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 学历
     */
    private String degree;

    /**
     * 期望薪资
     */
    private String expSalary;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")//设置存在为0，不存在为1
    private String isDelete;


}
