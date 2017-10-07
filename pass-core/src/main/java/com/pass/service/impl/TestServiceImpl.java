package com.pass.service.impl;

import com.pass.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by deepeshuniyal on 19/09/16.
 */
@Service
public class TestServiceImpl implements TestService {

    public String testing(){
        return "Success";
    }
}
