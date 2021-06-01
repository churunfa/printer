package cn.edu.printer.mapper;

import cn.edu.printer.pojo.PageBean;
import cn.edu.printer.pojo.Status;
import cn.edu.printer.pojo.Submission;
import cn.edu.printer.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubmissionMapper {
    @Insert("insert into submission(gmt_create, user_id, context, status, ip) values(#{gmt_create}, #{user.id}, #{context}, #{status}, #{ip})")
    int insertSubmission(Submission submission);

    @Select("select count(*) from submission")
    int countSubmissionAll();

    @Select("select count(*) from submission where status = #{status}")
    int countSubmissionByStatus(Status status);

    @Select("select count(*) from submission where user_id = #{id}")
    int countSubmissionByUser(User user);

    @Select("select *,u.id uid,u.gmt_create u_gmt_create from submission s, `user` u where  s.user_id = u.id order by status, s.gmt_create limit #{st}, #{limit}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "context", property = "context"),
            @Result(column = "status", property = "status"),
            @Result(column = "ip", property = "ip"),
            @Result(column = "uid", property = "user.id"),
            @Result(column = "u_gmt_create", property = "user.gmt_create"),
            @Result(column = "last_login", property = "user.last_login"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "show_name", property = "user.show_name"),
            @Result(column = "seat_number", property = "user.seat_number")
    })
    List<Submission> listSubmissionAll(int st, int limit);

    @Select("select *,u.id uid,u.gmt_create u_gmt_create from submission s, `user` u where  s.user_id = u.id and s.user_id = #{user.id} order by s.gmt_create desc limit #{st}, #{limit}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "context", property = "context"),
            @Result(column = "status", property = "status"),
            @Result(column = "ip", property = "ip"),
            @Result(column = "uid", property = "user.id"),
            @Result(column = "u_gmt_create", property = "user.gmt_create"),
            @Result(column = "last_login", property = "user.last_login"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "show_name", property = "user.show_name"),
            @Result(column = "seat_number", property = "user.seat_number"),
    })
    List<Submission> listSubmissionByUser(User user, int st, int limit);

    @Select("select *,u.id uid,u.gmt_create u_gmt_create from submission s, `user` u where  s.user_id = u.id and status = #{status}  order by s.gmt_create desc limit #{st}, #{limit}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "context", property = "context"),
            @Result(column = "status", property = "status"),
            @Result(column = "ip", property = "ip"),
            @Result(column = "uid", property = "user.id"),
            @Result(column = "u_gmt_create", property = "user.gmt_create"),
            @Result(column = "last_login", property = "user.last_login"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "show_name", property = "user.show_name"),
            @Result(column = "seat_number", property = "user.seat_number")
    })
    List<Submission> listSubmissionByStatus(Status status, int st, int limit);

    @Update("update submission set status = #{status} where id = #{id}")
    int updateStatus(Status status, int id);

//    @Select("select * from submission where id = #{id}")
    @Select("select *,u.id uid,u.gmt_create u_gmt_create from submission s, `user` u where  s.user_id = u.id and s.id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "gmt_create", property = "gmt_create"),
            @Result(column = "context", property = "context"),
            @Result(column = "status", property = "status"),
            @Result(column = "ip", property = "ip"),
            @Result(column = "uid", property = "user.id"),
            @Result(column = "u_gmt_create", property = "user.gmt_create"),
            @Result(column = "last_login", property = "user.last_login"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "show_name", property = "user.show_name"),
            @Result(column = "seat_number", property = "user.seat_number")
    })
    Submission getCode(int id);


}
