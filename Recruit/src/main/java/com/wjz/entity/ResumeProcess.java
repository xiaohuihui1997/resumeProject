package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职位需求
 * </p>
 *
 * @author hcb
 * @since 2023-01-09
 */
@Data
@Accessors(chain = true)
public class ResumeProcess extends BaseEntity {
    private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    //主键id
    private Integer id;

    //resume表的主键id
    private Integer resumeId;

    //简历状态,初筛0,初试1,复试2,发送offer3,正式录用4;
    private Integer status;

    //综合评价
    private String comprehensiveAssessment;

    //可面试时间
    private String interviewAvailableTime;

    //面试时间
    private String interviewTime;

    //面试形式
    private String interviewWay;

    //面试官
    private String interviewer;

    //面试结果(0待定,1通过,2未通过)
    private Integer interviewResult;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")//设置存在为0，不存在为1
    private String isDelete;

}
