package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职位需求
 * </p>
 *
 * @author hcb
 * @since 2023-01-06
 */
@Data
@Accessors(chain = true)
public class Resume extends BaseEntity {
    private static final long serialVersionUID = 1L;

    public static final String POSITION_NAME = "position_name";
    public static final String IS_DELETE = "is_delete";
    public static final String IS_PUSH = "is_push";

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 推荐日期
     */
    private String suggestDate;

    /**
     * 项目
     */
    private String project;

    /**
     * 候选人姓名
     */
    private String candidateName;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 工作年限
     */
    private Integer workYear;

    //身份证号码
    private String identityCardNumber;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 学历
     */
    private String education;

    /**
     * 期望薪资
     */
    private String salaryExpectation;

    /**
     * 简历对接人
     */
    private String resumeContactPerson;

    /**
     * 初筛是否通过(1为通过，0为未通过)
     */
    private String isPass;

    /**
     * 简历存放路径
     */
    private String url;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")//设置存在为0，不存在为1
    private String isDelete;

    //简历是否已推送
    private Integer isPush;

    //简历状态，
}
