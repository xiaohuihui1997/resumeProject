package com.wjz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjz.entity.Result;
import com.wjz.entity.Resume;
import com.wjz.entity.ResumePageInfo;
import com.wjz.entity.ResumeProcess;
import com.wjz.mapper.ResumeMapper;
import com.wjz.mapper.ResumeProcessMapper;
import com.wjz.service.ResumeService;
import com.wjz.utils.CommonVariable;
import com.wjz.utils.JsonObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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

    @Autowired
    private ResumeProcessMapper resumeProcessMapper;

    @Transactional
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
        Resume resume = resumeMapper.selectById(id);
        if (resume == null){
            return null;
        }
        if (resume.getIsPush() == 0){
            return resume;
        }
        //简历已推送，那就需要查看初筛是否通过
        if (resume.getStatus() == CommonVariable.ResumeVariable.PRIMARY_SCREENING){
            resume.setFirstScreenIsPass(CommonVariable.UNDETERMINED);
        }else if(resume.getStatus() == CommonVariable.ResumeVariable.PRIMARY_SCREENING_NOT_PASS){
            resume.setFirstScreenIsPass(CommonVariable.IS_NOT_PASS);
        } else {
            resume.setFirstScreenIsPass(CommonVariable.IS_PASS);
        }
        return resume;
    }

    @Override
    public void deleteResume(Integer id) {
        resumeMapper.updateIsDeleteById(id);
        resumeProcessMapper.updateIsDeleteByResumeId(id);
    }

    @Override
    public JSONObject updateResume(Resume resume) {
        //查询原来的数据
        Resume originResume = resumeMapper.selectById(resume.getId());
        if (!resume.getIdentityCardNumber().equals(originResume.getIdentityCardNumber())) {
            //如果身份证号码发生了改变，则在修改之前要先查看库里是否已存在现在要修改成的身份证号码
            Resume queryEntity = queryResumeByIdCard(resume.getIdentityCardNumber());
            if (queryEntity != null) {
                //如果库里已存在该简历
                return JsonObjectUtil.returnData(400, "该身份证号码已存在！");
            }
        }
        updateById(resume);
        if (resume.getResumeProcess() != null){
            resumeProcessMapper.updateById(resume.getResumeProcess());
        }
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
        //筛选存在的
        qw.eq(Resume.IS_DELETE, CommonVariable.IS_NOT_DELETE).eq(Resume.IS_PUSH, resumePageInfo.getIsPush());
        page(page, qw);
        List<Resume> resumeList = page.getRecords();
        if (resumePageInfo.getIsPush() == CommonVariable.IS_PUSH && !CollectionUtils.isEmpty(resumeList)){
//            //如果简历已推送，需要查询简历状态
//            List<Integer> resumeIdList = resumeList.stream().map(Resume::getId).collect(Collectors.toList());
//            List<ResumeProcess> resumeProcessList = resumeProcessMapper.selectByResumeIds(resumeIdList, CommonVariable.IS_NOT_DELETE);
//            //筛选出简历最新状态
//            Map<Integer, ResumeProcess> resumeIdResumeProcessMap = resumeProcessList.stream().collect(Collectors.toMap(ResumeProcess::getResumeId, Function.identity(), (key1, key2) -> {
//                if (key2.getStatus() > key1.getStatus()) {
//                    return key2;
//                }
//                return key1;
//            }));
            //设置简历状态
            for (Resume resume : resumeList){
//                ResumeProcess resumeProcess = resumeIdResumeProcessMap.get(resume.getId());
//                //简历状态
//                Integer status = resumeProcess.getStatus();
//                //简历面试结果
//                Integer interviewResult = resumeProcess.getInterviewResult();
                Integer status = resume.getStatus();
                if (status == CommonVariable.ResumeVariable.PRIMARY_SCREENING){
                    resume.setDetailStatus(CommonVariable.PRELIMINARY_SCREENING_TO_BE_CONFIRMED);
                } else if (status == CommonVariable.ResumeVariable.PRIMARY_SCREENING_PASS){
                    resume.setDetailStatus(CommonVariable.PRIMARY_SCREENING_PASS);
                } else if (status == CommonVariable.ResumeVariable.PRIMARY_SCREENING_NOT_PASS){
                    resume.setDetailStatus(CommonVariable.PRIMARY_SCREENING_NOT_PASS);
                } else if (status == CommonVariable.ResumeVariable.PRELIMINARY_TEST){
                    resume.setDetailStatus(CommonVariable.PRELIMINARY_TESTING);
                } else if (status == CommonVariable.ResumeVariable.PRELIMINARY_TEST_PASS){
                    resume.setDetailStatus(CommonVariable.PRELIMINARY_TEST_PASS);
                } else if (status == CommonVariable.ResumeVariable.PRELIMINARY_TEST_NOT_PASS){
                    resume.setDetailStatus(CommonVariable.PRELIMINARY_TEST_NOT_PASS);
                } else if (status == CommonVariable.ResumeVariable.SECONDARY_EXAMINATION){
                    resume.setDetailStatus(CommonVariable.SECONDARY_TESTING);
                } else if (status == CommonVariable.ResumeVariable.SECONDARY_TEST_PASS){
                    resume.setDetailStatus(CommonVariable.SECONDARY_TEST_PASS);
                } else if (status == CommonVariable.ResumeVariable.SECONDARY_TEST_NOT_PASS){
                    resume.setDetailStatus(CommonVariable.SECONDARY_TEST_NOT_PASS);
                }
            }
        }
        return Result.sucess(resumeList, "查询列表成功！", page.getTotal());
    }

    @Transactional
    @Override
    public int pushResume(int id, int supId, String resumeUrlName) {
        resumeMapper.pushResume(id,supId,resumeUrlName, CommonVariable.IS_PUSH, CommonVariable.ResumeVariable.PRIMARY_SCREENING);
        ResumeProcess resumeProcess = new ResumeProcess();
        resumeProcess.setResumeId(id);
        resumeProcess.setStatus(CommonVariable.ResumeProcessVariable.PRIMARY_SCREENING);
        return resumeProcessMapper.insert(resumeProcess);
    }

    @Transactional
    @Override
    public JSONObject interviewTime(ResumeProcess resumeProcess) {
        //先查看该简历流程到哪里了
//        Integer status = resumeMapper.selectStatusById(resumeProcess.getResumeId());
//        if (status == CommonVariable.ResumeVariable.PRIMARY_SCREENING_PASS){
//            //如果是初筛通过，则设置初面时间和面试官
//            Resume resume = new Resume();
//            resume.setId(resumeProcess.getResumeId());
//            resume.setStatus(CommonVariable.ResumeVariable.PRELIMINARY_TEST);
//            resumeMapper.updateById(resume);
//            resumeProcessMapper.updateById(resumeProcess);
//        } else if (status == CommonVariable.ResumeVariable.PRELIMINARY_TEST_PASS){
//            //如果是初试通过，则约复试
//            //修改简历状态，以及简历流程
//            Resume resume = new Resume();
//            resume.setId(resumeProcess.getResumeId());
//            resume.setStatus(CommonVariable.ResumeVariable.SECONDARY_EXAMINATION);
//            resumeMapper.updateById(resume);
//            resumeProcess.setStatus(CommonVariable.ResumeProcessVariable.SECONDARY_EXAMINATION);
//            resumeProcessMapper.insert(resumeProcess);
//        } else {
//            return JsonObjectUtil.returnData(500, "该候选人还不能面试！");
//        }
        resumeProcessMapper.updateById(resumeProcess);
        return JsonObjectUtil.returnData(200, "成功！");
    }

    @Transactional
    @Override
    public JSONObject interviewResult(ResumeProcess resumeProcess) {
        //更新面试流程
        resumeProcessMapper.updateById(resumeProcess);
        //更新简历
        resumeMapper.updateStatusById(resumeProcess.getResumeId(), resumeProcess.getInterviewResult());
        return JsonObjectUtil.returnData(200, "成功！");
    }

    @Override
    public JSONObject downloadResume(HttpServletRequest request, HttpServletResponse response, String url) {
        InputStream inStream = null;
        try {
            File file=new File(url);
            inStream = new FileInputStream(file);
            //octet-stream
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition","attachment;filename="+setFileDownloadHeader(request, file.getName()));
            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
            response.getOutputStream().close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return JsonObjectUtil.returnData(200, "下载成功！");
    }


    public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT");
        String filename = null;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(fileName, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(fileName, "utf-8");
        }
        return filename;
    }

}
