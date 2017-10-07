package com.pass.service;

import com.pass.model.Tags;

/**
 * Created by Ashutosh on 25-09-2016.
 */
public interface TagsService {

    Tags getAllTags();

    boolean addTags(Tags tag);

}
