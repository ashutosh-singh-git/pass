package com.pass.service;

import com.pass.model.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback create(Feedback feedback);

    List<Feedback> findAll();
}
