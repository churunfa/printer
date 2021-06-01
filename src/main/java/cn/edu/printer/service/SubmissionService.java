package cn.edu.printer.service;

import cn.edu.printer.pojo.PageBean;
import cn.edu.printer.pojo.Status;
import cn.edu.printer.pojo.Submission;
import cn.edu.printer.pojo.User;

public interface SubmissionService {
    public int insertSubmission(Submission submission);

    public PageBean<Submission> listSubmissionAll(PageBean<Submission> pageBean);

    public PageBean<Submission> listSubmissionByUser(PageBean<Submission> pageBean, User user);

    public PageBean<Submission> listSubmissionByStatus(PageBean<Submission> pageBean, Status status);

    public int countSubmissionAll();
    public int countSubmissionByStatus(Status status);
    public int countSubmissionByUser(User user);

    public int updateStatus(Status status, int id);
    public Submission getCode(int id);

}
