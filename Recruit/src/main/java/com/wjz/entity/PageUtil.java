package com.wjz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageUtil implements Serializable {
    private Integer pageNum = 1; //页数
    private Integer pageSize = 10; //每页数据数
    private String keyWord; //搜索关键字
}
