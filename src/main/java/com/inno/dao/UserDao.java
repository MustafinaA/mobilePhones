package com.inno.dao;

import com.inno.pojo.User;

@Deprecated
public interface UserDao {
    boolean addUser(User user);
    User getUserByLoginPassword(String login, String password);
}
