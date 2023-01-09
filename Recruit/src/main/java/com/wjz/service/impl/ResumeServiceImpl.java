package com.wjz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjz.entity.*;
import com.wjz.mapper.RecrRecruitMapper;
import com.wjz.mapper.ResumeMapper;
import com.wjz.service.RecrRecruitService;
import com.wjz.service.ResumeService;
import com.wjz.utils.CommonVariable;
import com.wjz.utils.JsonObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 简历管理实现类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;


    @Override
    public void enteringResume(Resume resume) {
        resumeMapper.insert(resume);
    }

    @Override
    public Resume queryResumeByIdCard(String idCard) {
        return resumeMapper.queryResumeByIdCard(idCard);
    }

    @Override
    public Resume checkResume(Integer id) {
        return resumeMapper.selectById(id);
    }

    @Override
    public void deleteResume(Integer id) {
        resumeMapper.updateIsDeleteById(id);
    }

    @Override
    public JSONObject updateResume(Resume resume) {
        //查询原来的数据
        Resume originResume = checkResume(resume.getId());
        if (!resume.getIdentityCardNumber().equals(originResume.getIdentityCardNumber())) {
            //如果身份证号码发生了改变，则在修改之前要先查看库里是否已存在现在要修改成的身份证号码
            Resume queryEntity = queryResumeByIdCard(resume.getIdentityCardNumber());
            if (queryEntity != null) {
                //如果库里已存在该简历
                return JsonObjectUtil.returnData(400, "该身份证号码已存在！");
            }
        }
        updateById(resume);
        return JsonObjectUtil.returnData(200, "修改成功！");
    }

    @Override
    public Result<List<Resume>> pageByPositionName(ResumePageInfo resumePageInfo) {
        IPage<Resume> page = new Page(resumePageInfo.getPageNum(), resumePageInfo.getPageSize());
        QueryWrapper<Resume> qw = new QueryWrapper<>();
        if (resumePageInfo.getKeyWord() != null && !"".equals(resumePageInfo.getKeyWord())) {
            //模糊查询
            qw.like(Resume.POSITION_NAME, resumePageInfo.getKeyWord());
        }
        if (resumePageInfo.getIsPush() != null) {
            qw.eq(Resume.IS_PUSH, resumePageInfo.getIsPush());
        }
        //筛选存在的
        qw.eq(Resume.IS_DELETE, CommonVariable.IS_NOT_DELETE);
        page(page, qw);
        return Result.sucess(page.getRecords(), "查询列表成功！", page.getTotal());
    }

    @Override
    public int pushResume(int id, int sup_id, String resumeUrlName) {
        return resumeMapper.pushResume(id,sup_id,resumeUrlName);
    }
}
