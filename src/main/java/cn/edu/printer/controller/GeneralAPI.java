package cn.edu.printer.controller;

import cn.edu.printer.mapper.UserMapper;
import cn.edu.printer.pojo.*;
import cn.edu.printer.service.SubmissionService;
import cn.edu.printer.service.UserService;
import cn.edu.printer.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("API")
public class GeneralAPI {

    UserService userService;
    SubmissionService submissionService;

    @Autowired
    public GeneralAPI(UserService userService, SubmissionService submissionService) {
        this.userService = userService;
        this.submissionService = submissionService;
    }

    @PostMapping("login")
    public Msg login(@RequestBody User user, HttpSession session, HttpServletResponse response) {
        Msg msg = new Msg();
        user = userService.login(user.getUsername(), user.getPassword());

        if (user == null) {
            msg.setSuccess(false);
            msg.setMsg("用户名或密码错误");
            return msg;
        }

        session.setAttribute("LOGIN_SESSION", user);
        session.setMaxInactiveInterval(60*60*60*24);

        Cookie c = new Cookie("JSESSIONID", session.getId());
        c.setPath("/");
        //先设置cookie有效期为2天
        c.setMaxAge(60 * 60 * 24);
        response.addCookie(c);


        msg.setSuccess(true);
        msg.setData(user);
        msg.setMsg("登陆成功～");
        return msg;
    }

    @GetMapping("login")
    public Msg loginStatus(HttpSession session) {
        Msg msg = new Msg();
        User user = (User) session.getAttribute("LOGIN_SESSION");
        if (user == null) msg.setSuccess(false);
        else msg.setSuccess(true);
        msg.setData(user);
        return msg;
    }

    @GetMapping("logout")
    public Msg logout(HttpSession session) {
        session.invalidate();
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setData(new User());
        return msg;
    }

    @PostMapping(value = "submission")
    public Msg submission(@RequestBody Submission submission, HttpSession session, HttpServletRequest request) {
        Msg msg = new Msg();
        if (submission.getContext() == null) {
            msg.setSuccess(false);
            msg.setMsg("请输入打印内容");
            return msg;
        }
        if (submission.getContext().length() == 0) {
            msg.setSuccess(false);
            msg.setMsg("请输入打印内容");
            return msg;
        }
        User user = (User) session.getAttribute("LOGIN_SESSION");
        if (user == null) {
            msg.setSuccess(false);
            msg.setMsg("请先登陆");
            return msg;
        }
        submission.setUser(user);
        String ip = IpUtils.getIp(request);
        submission.setIp(ip);

        int count = submissionService.insertSubmission(submission);
        if (count == 0) {
            msg.setSuccess(false);
            msg.setMsg("提交失败");
            return msg;
        }
        msg.setSuccess(true);
        msg.setMsg("提交成功");
        return msg;
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

        int tot = submissionService.countSubmissionByUser(user);
        pageBean.setTotalPages(tot);
        pageBean = submissionService.listSubmissionByUser(pageBean, user);
        msg.setData(pageBean);
        return msg;
    }
}
