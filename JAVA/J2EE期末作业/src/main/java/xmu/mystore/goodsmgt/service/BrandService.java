package xmu.mystore.goodsmgt.service;

import java.util.List;

import xmu.mystore.goodsmgt.model.Brand;


public interface BrandService {

	
	public List<Brand> getBrandList();
	public boolean addBrand(Brand brand);
	public boolean deleteBrandById(long id);
	public Brand getBrandById(long id);
	public boolean updateBrand(Brand brand);
	
	
}
