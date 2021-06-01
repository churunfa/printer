package cn.edu.printer;

import cn.edu.printer.mapper.SubmissionMapper;
import cn.edu.printer.mapper.UserMapper;
import cn.edu.printer.pojo.PageBean;
import cn.edu.printer.pojo.Status;
import cn.edu.printer.pojo.Submission;
import cn.edu.printer.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class PrinterApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SubmissionMapper submissionMapper;

    @Test
    void contextLoads() {
        User admin = userMapper.getUserByNameAndPassword("admin", "123456");
        System.out.println(admin);
    }

    @Test
    void submissionTest() {
        User user = userMapper.getUserByName("admin");
        Submission submission = new Submission();
        submission.setContext("1313561616+");
        submission.setUser(user);
        submission.setStatus(Status.等待打印);
        int i = submissionMapper.insertSubmission(submission);
        System.out.println(i);
    }

    @Test
    void listSubmissionTest() {
        List<Submission> submission = submissionMapper.listSubmissionAll(0, 2);
        System.out.println(submission);
    }

    @Test
    void listSubmissionByUserTest(){
        User user = new User();
        user.setId(1);
        List<Submission> submissions = submissionMapper.listSubmissionByUser(user, 0, 100);
        System.out.println(submissions);
    }

    @Test
    void listSubmissionByStatusTest() {
        List<Submission> submissions = submissionMapper.listSubmissionByStatus(Status.等待打印, 0, 100);
        System.out.println(submissions);
    }
    @Test
    void updateDateTest() {
        User user = new User();
        user.setId(1);
        int i = userMapper.updateDate(user, new Date());
        System.out.println(i);
    }

    @Test
    void updateStatusTest() {
        int i = submissionMapper.updateStatus(Status.打印完成, 2);
        System.out.println(i);
    }

    @Test
    void statusTest() {
        int ordinal1 = Status.等待打印.ordinal();
        int ordinal2 = Status.打印完成.ordinal();
        int ordinal3 = Status.拒绝打印.ordinal();
        System.out.println(ordinal1);
        System.out.println(ordinal2);
        System.out.println(ordinal3);
    }

    @Test
    void getCodeTest() {
        Submission code = submissionMapper.getCode(2);
        System.out.println(code);
    }
}
