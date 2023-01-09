package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 简历类
 * @author wjz
 * @since 2022-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CandResume extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 简历id
     */
    @TableId(value = "resume_id", type = IdType.AUTO)
    private Integer resumeId;

    private Integer candidateId;

    /**
     * 姓名
     */
    private String candName;

    /**
     * 性别
     */
    private String candSex;

    /**
     * 年龄
     */
    private Integer candAge;

    /**
     * 电话
     */
    private String candPhone;

    /**
     * 现所在地
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 求职意向
     */
    private String jobIntent;

    /**
     * 工作栈
     */
    private String tecStack;

    /**
     * 工作经历
     */
    private String workExp;


}
