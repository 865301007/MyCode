package com.xmu.mall.goodsmgt.wcf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmu.mall.goodsmgt.wcf.mapper.BrandMapper;
import com.xmu.mall.goodsmgt.wcf.model.Brand;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:37
*/
@Transactional
@Service("wcf_BrandService")
public class BrandServiceImp implements BrandService{

	@Autowired
	private BrandMapper brandMapper;
	
	public BrandServiceImp()
	{
		System.out.println("BrandServiceImp");
	}
	public List<Brand> getBrandList() {
		// TODO Auto-generated method stub
		//System.out.println(this.brandMapper.getBrandList());
		return this.brandMapper.getBrandList();
	}
	public boolean addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return this.brandMapper.addBrand(brand);
	}
	public boolean deleteBrandById(long id) {
		// TODO Auto-generated method stub
		return this.brandMapper.deleteBrandById(id);
	}
	public Brand getBrandById(long id) {
		// TODO Auto-generated method stub
		return this.brandMapper.getBrandById(id);
	}
	public boolean updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return this.brandMapper.updateBrand(brand);
	}
	

}
