package com.javaweb.dao.impl;

import java.util.List;

import com.javaweb.dao.ICategoryDAO;
import com.javaweb.mapper.CategoryMappper;
import com.javaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{
	
	@Override
	public List<CategoryModel> findAll() {
		String sql = "select * from category"; 
		return query(sql, new CategoryMappper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "select * from category where id = ?";
		List<CategoryModel> category = query(sql, new CategoryMappper(), id);
		return category.isEmpty() ? null : category.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "select * from category where code = ?";
		List<CategoryModel> category = query(sql, new CategoryMappper(), code);
		return category.isEmpty() ? null : category.get(0);
	}
}
