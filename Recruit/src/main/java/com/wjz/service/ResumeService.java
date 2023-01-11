package com.wjz.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjz.entity.Result;
import com.wjz.entity.Resume;
import com.wjz.entity.ResumePageInfo;
import com.wjz.entity.ResumeProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    int pushResume(int id, int supId, String resumeUrlName);

    JSONObject interviewTime(ResumeProcess resumeProcess);

    JSONObject interviewResult(ResumeProcess resumeProcess);

    JSONObject downloadResume(HttpServletRequest request, HttpServletResponse response, String url);
}
