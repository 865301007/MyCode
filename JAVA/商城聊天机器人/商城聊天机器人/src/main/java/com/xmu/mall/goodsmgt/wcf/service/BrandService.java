package com.xmu.mall.goodsmgt.wcf.service;

import java.util.List;

import com.xmu.mall.goodsmgt.wcf.model.Brand;



/**
*@author created by �����
*@date 2017��5��31��--����1:44:33
*/
public interface BrandService {

	
	public List<Brand> getBrandList();
	public boolean addBrand(Brand brand);
	public boolean deleteBrandById(long id);
	public Brand getBrandById(long id);
	public boolean updateBrand(Brand brand);
	
	
}
