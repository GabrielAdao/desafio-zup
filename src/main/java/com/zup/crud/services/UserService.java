package com.zup.crud.services;

import com.zup.crud.entities.User;
import com.zup.crud.dao.UserDAO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserService {

    @Resource
    UserDAO userDAO;

    @GetMapping(value = "/userList")
    public List<User> findAll(){
        return userDAO.findAll();
    }

    @PostMapping(value = "/createUser")
    public void insert(@RequestBody User user){
        userDAO.insertUser(user);
    }

    @PutMapping(value = "/updateUser")
    public void update(@RequestBody User user){
        userDAO.updateUser(user);
    }

    @DeleteMapping(value = "/deleteUser")
    public void delete (@RequestBody User user){
        userDAO.deleteUser(user);
    }

}
