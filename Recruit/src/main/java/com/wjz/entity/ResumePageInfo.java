package com.wjz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResumePageInfo extends PageUtil {
    private static final long serialVersionUID = 1L;

    //是否已推送，0为未推送，1为已推送
    private Integer isPush; //页数
}
