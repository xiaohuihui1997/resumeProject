package com.wjz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * code
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecrCodeDefine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "code_define_id", type = IdType.AUTO)
    private Integer codeDefineId;

    /**
     * code类型
     */
    private String cType;

    /**
     * code
     */
    private String code;

    /**
     * code表示含义
     */
    private String cValue;


}
