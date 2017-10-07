package com.pass.service;

import com.pass.model.Survey;

/**
 * Created by Ashutosh on 25-09-2016.
 */

public interface SurveyService {

    Survey getAll();

    boolean addSurvey(String option);
}
