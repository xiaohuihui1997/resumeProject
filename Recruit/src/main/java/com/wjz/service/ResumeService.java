package com.wjz.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjz.entity.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
public interface ResumeService extends IService<Resume> {

    void enteringResume(Resume resume);

    Resume queryResumeByIdCard(String idCard);

    Resume checkResume(Integer id);

    void deleteResume(Integer id);

    JSONObject updateResume(Resume resume);

    Result<List<Resume>> pageByPositionName(ResumePageInfo resumePageInfo);

    int pushResume(int id, int sup_id, String resumeUrlName);

}
