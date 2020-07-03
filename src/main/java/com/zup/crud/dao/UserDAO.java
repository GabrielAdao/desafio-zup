package com.zup.crud.dao;

import com.zup.crud.entities.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    void insertUser(User user);

    void updateUser(User user);

    void executeUpdateUser(User user);

    public void deleteUser(User user);
}
