package com.pass.service;

import com.pass.model.Categories;

/**
 * Created by Ashutosh on 25-09-2016.
 */
public interface CategoryService {

    Categories getAllCategories();

    boolean addCategories(Categories category);

    boolean deleteCategories(Categories categories);
}
