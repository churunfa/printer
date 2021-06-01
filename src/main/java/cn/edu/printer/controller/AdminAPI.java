package cn.edu.printer.controller;

import cn.edu.printer.pojo.Msg;
import cn.edu.printer.pojo.PageBean;
import cn.edu.printer.pojo.Submission;
import cn.edu.printer.pojo.User;
import cn.edu.printer.service.SubmissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("API/admin")
public class AdminAPI {

    SubmissionService submissionService;

    @Autowired
    public AdminAPI(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("user_submission_list")
    public Msg getSubmission(@RequestBody PageBean<Submission> pageBean, HttpSession session) {
        Msg msg = new Msg();
        msg.setSuccess(true);

        User user = (User) session.getAttribute("LOGIN_SESSION");

        if (user == null) {
            msg.setMsg("未登陆");
            msg.setData(pageBean);
            return msg;
        }

        if (user.getId() != 1) {
            msg.setMsg("您非管理员，无法进行此操作");
            msg.setData(pageBean);
            return msg;
        }

        int tot = submissionService.countSubmissionAll();
        pageBean.setTotalPages(tot);
        pageBean = submissionService.listSubmissionAll(pageBean);
        msg.setData(pageBean);
        return msg;
    }

    @PostMapping("submission_count")
    public Msg getSubmissionCount(HttpSession session) {
        Msg msg = new Msg();
        msg.setSuccess(true);

        User user = (User) session.getAttribute("LOGIN_SESSION");

        if (user == null) {
            msg.setSuccess(false);
            msg.setMsg("未登陆");
            return msg;
        }

        if (user.getId() != 1) {
            msg.setSuccess(false);
            msg.setMsg("您非管理员，无法进行此操作");
            return msg;
        }
        int tot = submissionService.countSubmissionAll();
        msg.setData(tot);
        return msg;
    }

    @PostMapping("update_status")
    public Msg updateStatus(@RequestBody Submission submission, HttpSession session) {
        Msg msg = new Msg();
        msg.setSuccess(true);

        User user = (User) session.getAttribute("LOGIN_SESSION");

        if (user == null) {
            msg.setSuccess(false);
            msg.setMsg("未登陆");
            return msg;
        }

        if (user.getId() != 1) {
            msg.setSuccess(false);
            msg.setMsg("您非管理员，无法进行此操作");
            return msg;
        }

        int tot = submissionService.updateStatus(submission.getStatus(), submission.getId());
        msg.setData(tot);
        return msg;
    }

    @RequestMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable int id, HttpSession session, HttpServletResponse response) throws IOException {

        User user = (User) session.getAttribute("LOGIN_SESSION");

        response.setContentType("text/html;charset=utf-8");

        if (user == null){
            Msg msg = new Msg();
            msg.setSuccess(false);
            msg.setMsg("请先登陆");

            MultiValueMap<String, String> headers = new HttpHeaders();
            String msgJson = new ObjectMapper().writeValueAsString(msg);
            byte[] bytes = msgJson.getBytes(StandardCharsets.UTF_8);
            return new ResponseEntity<byte[]>(bytes , headers, HttpStatus.OK);
        }

        if (user.getId() != 1){
            Msg msg = new Msg();
            msg.setSuccess(false);
            msg.setMsg("您没有权限进行此操作");

            MultiValueMap<String, String> headers = new HttpHeaders();
            String msgJson = new ObjectMapper().writeValueAsString(msg);
            byte[] bytes = msgJson.getBytes(StandardCharsets.UTF_8);
            return new ResponseEntity<byte[]>(bytes , headers, HttpStatus.OK);
        }

        Submission submission = submissionService.getCode(id);
        if (submission == null) return null;
        String context = submission.getContext();

        StringBuilder buffer = new StringBuilder("****************************************************************************").append("\n");
        buffer.append("打印申请：").append("\n");
        buffer.append("申请编号：").append(submission.getId()).append("\n");

        Date date = submission.getGmt_create();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String dateStr = format.format(date);

        buffer.append("申请时间：").append(submission.getGmt_create()).append("\n");
        buffer.append("用户名：").append(dateStr).append("\n");
        buffer.append("申请ip：").append(submission.getIp()).append("\n");
        buffer.append("申请人座号：").append(submission.getUser().getSeat_number()).append("\n");
        buffer.append("****************************************************************************").append("\n");
        buffer.append(context);


        context = buffer.toString();

        byte[] body = context.getBytes();
        String filename = "" + submission.getId() + "." +submission.getUser().getShow_name() + ".txt";


        response.setCharacterEncoding("UTF-8");

        HttpHeaders headers = new HttpHeaders();
        response.setHeader("Content-Type","application/x-www-form-urlencoded");
        headers.setContentDispositionFormData("attachment", new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }
}
