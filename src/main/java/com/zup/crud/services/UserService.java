package com.zup.crud.services;

import com.zup.crud.entities.User;
import com.zup.crud.repositories.UserRepository;
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
        return userRepository.save(obj);
    }

    public void delete (Long id){

        userRepository.deleteById(id);
    }

    public User update (User obj){
        return userRepository.save(obj);
    }


}
