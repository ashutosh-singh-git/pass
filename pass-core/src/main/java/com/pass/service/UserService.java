package com.pass.service;

import com.pass.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Ashutosh on 11-10-2016.
 */
public interface UserService extends UserDetailsService{

    User getRole(String user);

    User getUserByName(String name);
}
