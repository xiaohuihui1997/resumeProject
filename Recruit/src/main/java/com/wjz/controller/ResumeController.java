package com.wjz.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjz.entity.*;
import com.wjz.service.ResumeService;
import com.wjz.utils.JsonObjectUtil;
import com.wjz.utils.MFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
     * 推送简历
     * @param file 简历文件
     * @throws IOException
     */
    @RequestMapping(value = "/pushResume",method = RequestMethod.POST)
    public Result<Resume> pushResume(int id, int supId, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            if((file.getOriginalFilename().endsWith(".rar"))||
                    (file.getOriginalFilename().endsWith(".arj"))||
                    (file.getOriginalFilename().endsWith(".jar"))||
                    (file.getOriginalFilename().endsWith(".zip"))||
                    (file.getOriginalFilename().endsWith(".doc"))||
                    (file.getOriginalFilename().endsWith(".jpg"))||
                    (file.getOriginalFilename().endsWith(".png"))||
                    (file.getOriginalFilename().endsWith(".pdf"))||
                    (file.getOriginalFilename().endsWith(".docx"))){
                //根据文件创建一个文件名字
                String s1 = MFileUtils.MakeFileName(file.getOriginalFilename());
                //把这个文件写入到FILE_PATH路径下
                File filePath = new File(MFileUtils.FILE_PATH + s1);
                filePath.mkdirs();
                file.transferTo(filePath);
                //记录该路径名称
                String resumeUrlName = MFileUtils.FILE_PATH+s1;
                //推送简历
                int update = resumeService.pushResume(id,supId,resumeUrlName);
                if(update>0){
                    return Result.sucess("上传成功!");
                }else{
                    return Result.sucess("上传失败!");
                }
            }else{
                return Result.error("文件格式不对!应该是rar,arj,zip,doc,jpg,png,pdf,docx其中一种");
            }
        }else{
            return Result.error("文件为空!");
        }
    }

    /**
     * 简历下载
     * @param url 文件路径
     * @return
     */
    @RequestMapping(value = "/downloadResume",method = RequestMethod.GET)
    public JSONObject downloadResume(HttpServletRequest request, HttpServletResponse response, @RequestParam("url") String url){
        return resumeService.downloadResume(request, response, url);
    }

    /**
     * 未推送的简历
     * 分页查询
     * @param pageUtil 分页工具
     * @return
     */
    @RequestMapping(value = "/pageResumeOne",method = RequestMethod.GET)
    public Result<List> pageResumeOne(PageUtil pageUtil){
        IPage<Resume> page = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        //条件控制器
        QueryWrapper<Resume> qw = new QueryWrapper<>();
        //判断搜索的关键字是否存在
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            qw.like("position_name",pageUtil.getKeyWord());
        }
        qw.eq("is_push",0);
        resumeService.page(page,qw);
        List<Resume> candResumeList = page.getRecords();
        return Result.sucess(candResumeList,"查询成功!",page.getTotal());
    }

    /**
     * 已经推送的简历
     * 分页查询
     * @param pageUtil 分页工具
     * @return
     */
    @RequestMapping(value = "/pageResumeTwo",method = RequestMethod.GET)
    public Result<List> pageResumeTwo(PageUtil pageUtil){
        IPage<Resume> page = new Page(pageUtil.getPageNum(),pageUtil.getPageSize());
        //条件控制器
        QueryWrapper<Resume> qw = new QueryWrapper<>();
        //判断搜索的关键字是否存在
        if(pageUtil.getKeyWord()!=null && !pageUtil.getKeyWord().equals("")){
            qw.like("position_name",pageUtil.getKeyWord());
        }
        qw.eq("is_push",1);
        resumeService.page(page,qw);
        List<Resume> candResumeList = page.getRecords();
        return Result.sucess(candResumeList,"查询成功!",page.getTotal());
    }

    /**
     * 查看单个简历
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
     * @param resume
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private Result<List<Resume>> list(@RequestBody Resume resume, Integer pageNum, Integer pageSize, Boolean isPass) {
        return resumeService.pageByPositionName(pageNum, pageSize, resume, isPass);
    }

    /**
     * 用人部门设置面试时间和面试官
     * @param resumeProcess
     * @return
     */
    @RequestMapping(value = "/interviewTime", method = RequestMethod.POST)
    private JSONObject interviewTime(@RequestBody ResumeProcess resumeProcess) {
        if (resumeProcess.getInterviewer() == null || "".equals(resumeProcess.getInterviewer())){
            return JsonObjectUtil.returnData(400, "面试官不能为空!");
        }
        return resumeService.interviewTime(resumeProcess);
    }

    /**
     * 用人部门设置面试结论
     * @param resumeProcess
     * @return
     */
    @RequestMapping(value = "/interviewResult", method = RequestMethod.POST)
    private JSONObject interviewResult(@RequestBody ResumeProcess resumeProcess) {
        if (resumeProcess.getId() == null || resumeProcess.getResumeId() == null || resumeProcess.getInterviewResult() == null){
            return JsonObjectUtil.returnData(400, "参数不能为空!");
        }
        if (resumeProcess.getInterviewTime() != null && !"".equals(resumeProcess.getInterviewTime())){
            if (resumeProcess.getInterviewer() == null || "".equals(resumeProcess.getInterviewer())){
                return JsonObjectUtil.returnData(400, "面试官不能为空!");
            }
        }
        return resumeService.interviewResult(resumeProcess);
    }
}

