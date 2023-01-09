package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 状态类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecrState extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 状态的id
     */
    @TableId(value = "state_id", type = IdType.AUTO)
    private Integer stateId;

    /**
     * 外键,信息表的id
     */
    private Integer recruitId;

    /**
     * 是否已分配
     */
    private String isAss;

    /**
     * 被分配人
     */
    private String assignee;

    /**
     * 综合评价
     */
    private String evaluation;

    /**
     * 是否已发布
     */
    private String isPublish;

    /**
     * 发布渠道
     */
    private String publishChannel;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")//设置存在为0，不存在为1
    private String isDelete;

}
