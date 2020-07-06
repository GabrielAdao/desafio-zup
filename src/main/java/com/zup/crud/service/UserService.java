package com.zup.crud.service;

import com.zup.crud.entities.User;
import com.zup.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById (Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    public User insert(User obj){
        return  userRepository.save(obj);
    }

    public void delete (Long obj){
        userRepository.deleteById(obj);
    }

    public User update (User obj){
        return userRepository.save(obj);
    }
}
