package com.wjz.service.impl;

import com.wjz.entity.RecrCodeDefine;
import com.wjz.mapper.RecrCodeDefineMapper;
import com.wjz.service.RecrCodeDefineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Service
public class RecrCodeDefineServiceImpl extends ServiceImpl<RecrCodeDefineMapper, RecrCodeDefine> implements RecrCodeDefineService {

    @Resource
    private RecrCodeDefineMapper recrCodeDefineMapper;

    //查询所有的RecrCodeDefine
    @Override
    public List<RecrCodeDefine> queryAllCode() {
        return recrCodeDefineMapper.selectList(null);
    }

    //通过code查询单个
    @Override
    public RecrCodeDefine queryByCode(int code) {
        return recrCodeDefineMapper.selectById(code);
    }
}
