package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 候选人状态类
 * @author wjz
 * @since 2022-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CandState extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 候选人状态id
     */
    @TableId(value = "cand_state_id", type = IdType.AUTO)
    private Integer candStateId;

    private Integer candidateId;

    /**
     * 筛选状态（0代表到简历库，1代表在待初筛，2代表在初筛，3代表在面试，4代表在复试，5代表已经发送offer，6代表已经正式聘用
     */
    private String stateNumber;

    /**
     * 待初筛不通过理由
     */
    private String reasonOne;

    /**
     * 初筛不通过理由
     */
    private String reasonTwo;

    /**
     * 初试时间
     */
    private String interTime;

    /**
     * 可复试时间
     */
    private Date retestTime;

    /**
     * 初试综合评价
     */
    private String interEval;

    /**
     * 转发招聘专员姓名
     */
    private String assName;

    /**
     * 转发综合评价
     */
    private String assEval;

    /**
     * 复试的综合评价
     */
    private String retestEvaluation;

    /**
     * 是否删除
     */
    private String isDelete;


}
