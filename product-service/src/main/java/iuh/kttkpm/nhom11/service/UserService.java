package iuh.kttkpm.nhom11.service;


import iuh.kttkpm.nhom11.entity.User;
import iuh.kttkpm.nhom11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }


    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public List<User> getListUser(){
       return userRepository.findAll();
    }


}
