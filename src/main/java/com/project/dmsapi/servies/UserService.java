package com.project.dmsapi.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dmsapi.entities.User;
import com.project.dmsapi.helper.BaseHelper;
import com.project.dmsapi.repository.JpaTemplateRepository;
import com.project.dmsapi.repository.UserRepository;

@Service
public class UserService {
	@Autowired
    UserRepository userRepository;
	@Autowired
    BaseHelper baseHelper;
	@Autowired
	JpaTemplateRepository jpaTemplateRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(int userId){
    	userRepository.deleteById(userId);
    }
    public User getUserByUserName(String userName) {
    	return jpaTemplateRepository.findUserByUserName(userName);
    }
    public User findUserByUserNameAndPassword(String userName, String password){
    	return jpaTemplateRepository.findUserByUserNameAndPassword(userName, baseHelper.generateHashedPassword(password));
    }
    
}
