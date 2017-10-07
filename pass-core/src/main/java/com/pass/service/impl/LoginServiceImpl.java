package com.pass.service.impl;

import com.pass.model.FbLogin;
import com.pass.model.User;
import com.pass.repository.UserRepository;
import com.pass.service.LoginService;
import com.pass.service.UserService;
import com.pass.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ashutosh on 14-11-2016.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository repository;

    private final UserService userService;

    @Autowired
    public LoginServiceImpl(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public User create(FbLogin login) {
        User user = new User();
        user.setGender(login.getGender());
        user.setName(login.getName());
        user.setUsername(login.getEmail());
        user.setPassword(login.getId());
        user.setPicture(login.getPicture());
        user.setRole(2);
        user.setGcm_key(login.getGcm_key());
        user.setUtm_campaign(login.getUtm_campaign());
        user.setUtm_content(login.getUtm_content());
        user.setUtm_medium(login.getUtm_medium());
        user.setUtm_source(login.getUtm_source());
        user.setUtm_term(login.getUtm_term());
        User original = userService.getUserByName(login.getEmail());
        if(original != null){
            Util.copyNonNullProperties(user,original);
            return repository.save(original);
        }
        System.out.println("User Login : " + user.toString());
        return repository.save(user);
    }
}
