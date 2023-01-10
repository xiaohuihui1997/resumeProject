package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 我的面试对应的实体类
 * </p>
 *
 * @author wjz
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResumeInter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 面试id
     */
    @TableId(value = "inter_id", type = IdType.AUTO)
    private Integer interId;

    /**
     * 候选人面试id
     */
    private Integer resumeId;

    /**
     * 面试的时间
     */
    private Date interTime;

    /**
     * 职位
     */
    private String interPost;

    /**
     * 面试官id
     */
    private Integer interPersonId;

    /**
     * 初试还是复试
     */
    private String interType;

    /**
     * 线上面试还是线下面试
     */
    private String interForm;

    /**
     * 安排人
     */
    private Integer arrangId;

    /**
     * 应聘者是否签到（1代表已经签到，0代表未评价）
     */
    private String isSign;

    /**
     * 面试url
     */
    private String interUrl;

    /**
     * 是否通过面试
     */
    private String isPass;

    /**
     * 综合评价（不能超过500个字）
     */
    private String interAppraise;

    /**
     * 是否已经评价（已经评价为1，未评价为0）
     */
    private String isAppraise;

    /**
     * 转发招聘专员id
     */
    private Integer assignId;

    /**
     * 转发时候的综合评价（不能超过500个字）
     */
    private String assignAppraise;

    /**
     * 是否未到场
     */
    private String isAbsence;

    /**
     * 未到场的综合评价（不能超过500个字）
     */
    private String absenceAppraise;

    /**
     * 是否接受
     */
    private String isAccept;

    /**
     * 是否删除（逻辑删除）
     */
    @TableLogic(value = "0",delval = "1")//设置存在为0，不存在为1
    private String isDelete;


}
