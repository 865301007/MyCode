package com.xmu.mall.goodsmgt.wcf.service;

import java.util.List;

import com.xmu.mall.goodsmgt.wcf.model.Category;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:41
*/
public interface CategoryService {
	public List<Category> getCategoryList();
	public boolean addCategory(Category category);
	public boolean deleteCategoryById(long id);
	public Category getCategoryById(long id);
	public boolean updateCategory(Category category);

}
