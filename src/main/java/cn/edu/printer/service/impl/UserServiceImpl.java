package cn.edu.printer.service.impl;

import cn.edu.printer.mapper.UserMapper;
import cn.edu.printer.pojo.User;
import cn.edu.printer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.getUserByNameAndPassword(username, password);
        userMapper.updateDate(user, new Date());
        return user;
    }

}
