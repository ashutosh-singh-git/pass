package com.pass.service.impl;

import com.pass.model.Template;
import com.pass.repository.TemplateRepository;
import com.pass.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashutosh on 25-09-2016.
 */
@Service
public class TemplateServiceImpl implements TemplateService{

    private final TemplateRepository repository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Template> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean addTemplate(String template) {
        return false;
    }
}
