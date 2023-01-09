package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职位需求
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecrRecruit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 需求编号
     */
    @TableId(value = "recruit_id", type = IdType.AUTO)
    private Integer recruitId;

    /**
     * 需求职位名称
     */
    private String recruitName;

    /**
     * 级别
     */
    private String level;

    /**
     * 工作年限
     */
    private String workYear;

    /**
     * 薪资范围
     */
    private String salaryLimit;

    /**
     * 工作地址
     */
    private String workAddress;

    /**
     * 学历要求
     */
    private String academic;

    /**
     * 到岗时间
     */
    private String arrivalTime;

    /**
     * 优先等级
     */
    private Integer prLevel;

    /**
     * 推送提醒
     */
    private String pushRem;

    /**
     * 项目性质
     */
    private String projectNature;

    /**
     * 职位描述
     */
    private String recruitDesc;

    /**
     * 需求状态
     */
    private String demandStatud;

    /**
     * 是否关闭
     */
    private String isClose;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")//设置存在为0，不存在为1
    private String isDelete;
}
