package com.wjz.service;

import com.wjz.entity.RecrCodeDefine;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
public interface RecrCodeDefineService extends IService<RecrCodeDefine> {


    //查询所有的RecrCodeDefine
    List<RecrCodeDefine> queryAllCode();

    //通过code查询单个
    RecrCodeDefine queryByCode(int code);
}
