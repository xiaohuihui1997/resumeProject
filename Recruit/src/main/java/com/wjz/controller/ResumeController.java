package com.wjz.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.*;
import com.wjz.service.ResumeService;
import com.wjz.utils.CommonVariable;
import com.wjz.utils.JsonObjectUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 简历管理
 *
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    /**
     * 录入简历
     *
     * @param resume 简历信息
     */
    @RequestMapping(value = "/entering", method = RequestMethod.POST)
    private JSONObject enteringResume(@RequestBody Resume resume) {
        //校验参数，身份证号不能为空
        if (resume.getIdentityCardNumber() == null || "".equals(resume.getIdentityCardNumber())) {
            JsonObjectUtil.returnData(400, "身份证号码不能为空！");
        }
        //查看该数据是否已经被录入
        Resume queryEntity = resumeService.queryResumeByIdCard(resume.getIdentityCardNumber());
        if (queryEntity != null) {
            return JsonObjectUtil.returnData(400, "简历信息已被录入，请不要重复录入！");
        }
        //录入数据
        resumeService.enteringResume(resume);
        return JsonObjectUtil.returnData(200, "录入成功！");
    }

    /**
     * 查看单个简历
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    private JSONObject checkResume(@RequestParam Integer id) {
        //查看数据
        Resume resume = resumeService.checkResume(id);
        JSONObject jsonObject = JsonObjectUtil.returnData(200, "查看成功！");
        jsonObject.put("data", resume);
        return jsonObject;
    }

    /**
     * 删除单个简历
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private JSONObject deleteResume(@RequestParam Integer id) {
        //删除数据
        resumeService.deleteResume(id);
        return JsonObjectUtil.returnData(200, "删除成功！");
    }

    /**
     * 修改单个简历
     *
     * @param resume
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    private JSONObject updateResume(@RequestBody Resume resume) {
        //校验参数，身份证号不能为空
        if (resume.getIdentityCardNumber() == null || "".equals(resume.getIdentityCardNumber())) {
            return JsonObjectUtil.returnData(400, "身份证号码不能为空！");
        }
        return resumeService.updateResume(resume);
    }

    /**
     * 简历列表展示
     * @param resumePageInfo
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private Result<List<Resume>> listForNoPush(@RequestBody ResumePageInfo resumePageInfo) {
        return resumeService.pageByPositionName(resumePageInfo);
    }
}

