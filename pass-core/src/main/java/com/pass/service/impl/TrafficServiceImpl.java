package com.pass.service.impl;

import com.pass.model.Traffic;
import com.pass.repository.TrafficRepository;
import com.pass.service.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ashutosh on 06-11-2016.
 */
@Service
public class TrafficServiceImpl implements TrafficService {

    private final TrafficRepository repository;

    @Autowired
    public TrafficServiceImpl(TrafficRepository repository) {
        this.repository = repository;
    }

    @Override
    public Traffic create(Traffic traffic) {
        return repository.save(traffic);
    }
}
