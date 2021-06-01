package cn.edu.printer.service.impl;

import cn.edu.printer.mapper.SubmissionMapper;
import cn.edu.printer.pojo.PageBean;
import cn.edu.printer.pojo.Status;
import cn.edu.printer.pojo.Submission;
import cn.edu.printer.pojo.User;
import cn.edu.printer.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    SubmissionMapper submissionMapper;

    @Autowired
    public SubmissionServiceImpl(SubmissionMapper submissionMapper) {
        this.submissionMapper = submissionMapper;
    }

    @Override
    public int insertSubmission(Submission submission) {
        return submissionMapper.insertSubmission(submission);
    }

    @Override
    public PageBean<Submission> listSubmissionAll(PageBean<Submission> pageBean) {
        int st = (pageBean.getPageNo() - 1) * pageBean.getPageSize();
        int lim = pageBean.getPageSize();
        List<Submission> submissions = submissionMapper.listSubmissionAll(st, lim);
        pageBean.setPageData(submissions);
        pageBean.setRecordCount(submissions.size());
        return pageBean;
    }

    @Override
    public PageBean<Submission> listSubmissionByUser(PageBean<Submission> pageBean, User user) {
        int st = (pageBean.getPageNo() - 1) * pageBean.getPageSize();
        int lim = pageBean.getPageSize();
        List<Submission> submissions = submissionMapper.listSubmissionByUser(user, st, lim);
        pageBean.setPageData(submissions);
        pageBean.setRecordCount(submissions.size());
        return pageBean;
    }

    @Override
    public PageBean<Submission> listSubmissionByStatus(PageBean<Submission> pageBean, Status status) {
        int st = (pageBean.getPageNo() - 1) * pageBean.getPageSize();
        int lim = pageBean.getPageSize();
        List<Submission> submissions = submissionMapper.listSubmissionByStatus(status, st, lim);
        pageBean.setPageData(submissions);
        pageBean.setRecordCount(submissions.size());
        return pageBean;
    }

    @Override
    public int countSubmissionAll() {
        return submissionMapper.countSubmissionAll();
    }

    @Override
    public int countSubmissionByStatus(Status status) {
        return submissionMapper.countSubmissionByStatus(status);
    }

    @Override
    public int countSubmissionByUser(User user) {
        return submissionMapper.countSubmissionByUser(user);
    }

    @Override
    public int updateStatus(Status status, int id) {
        return submissionMapper.updateStatus(status, id);
    }

    @Override
    public Submission getCode(int id) {
        return submissionMapper.getCode(id);
    }
}
