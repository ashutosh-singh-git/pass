package com.pass.service;

import com.pass.model.FbLogin;
import com.pass.model.User;

public interface LoginService {

    User create(FbLogin feedback);

//    List<Feedback> findAll();
}
