package cn.edu.printer.mapper;

import cn.edu.printer.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface UserMapper {

    @Select("select * from `user` where username = #{username}")
    public User getUserByName(String username);

    @Select("select * from `user` where username = #{username} and password = #{password}")
    public User getUserByNameAndPassword(String username, String password);

    @Update("update `user` set last_login = #{date} where id = #{user.id}")
    public int updateDate(User user, Date date);

}
