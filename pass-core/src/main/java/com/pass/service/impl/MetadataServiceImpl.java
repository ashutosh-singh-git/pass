package com.pass.service.impl;

import com.pass.model.Metadata;
import com.pass.repository.MetadataRepository;
import com.pass.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashutosh on 05-11-2016.
 */
@Service
public class MetadataServiceImpl implements MetadataService {

    private final MetadataRepository repository;

    @Autowired
    public MetadataServiceImpl(MetadataRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Metadata> findAll() {
        return repository.findAll();
    }
}
