package cn.edu.printer.service;

import cn.edu.printer.pojo.User;

public interface UserService {
    public User login(String username, String password);
}
