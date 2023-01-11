package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
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

    public static final String STATUS = "status";
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
     * 主管id
     */
    private Integer supId;

    /**
     * 简历存放路径
     */
    private String url;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")//设置存在为0，不存在为1
    private String isDelete;

    //简历是否已推送
    private Integer isPush;

    //初筛是否通过(待定0，通过1，未通过2)
    @TableField(exist = false)
    private Integer firstScreenIsPass;

    //简历状态(简历状态，0为待定，10为初筛中,11为初筛通过，12为初筛未通过，20为初试中，21为初试通过，22为初试未通过，30为复试中，31为复试通过，32为复试未通过)
    private Integer status;

    //简历具体状态
    @TableField(exist = false)
    private String detailStatus;

    @TableField(exist = false)
    private ResumeProcess resumeProcess;
}
