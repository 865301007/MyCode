package com.xmu.mall.goodsmgt.wcf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmu.mall.goodsmgt.wcf.model.Brand;
import com.xmu.mall.goodsmgt.wcf.service.BrandService;


/**
*@author created by �����
*@date 2017��5��31��--����1:43:25
*/
@Controller
@RequestMapping(value="brandManage/")
public class BrandManageController {

	@Autowired
	@Qualifier("wcf_BrandService")
	private BrandService brandService;

	@RequestMapping(value="showList",method=RequestMethod.GET)
	public String showList(Model model)
	{

		List<Brand> brandList=this.brandService.getBrandList();
	//	Brand b=brandList.get(0);
	//	System.out.println(b.getWebsite());
		model.addAttribute("brandList",brandList);
		return "wcf/brandList";
	}

	@RequestMapping(value="add")
	public String add()
	{
		return "wcf/addBrand";
	}

	@RequestMapping(value="added")
	public String added(Brand brand,Model model)
	{
		if(brand.getType()!=1)
			brand.setType(0);
		brand.setId(null);
		String s=brand.getWebsite();
		if(s.indexOf("http://")!=0)
			brand.setWebsite("http://"+brand.getWebsite());
		System.out.println(brand.getName());
		this.brandService.addBrand(brand);
		model.addAttribute("brandList", this.brandService.getBrandList());
		return "wcf/brandList";
	}
	
	@RequestMapping(value="delete")
	public String delete(@RequestParam(value="id") int id,Model model)
	{
		this.brandService.deleteBrandById(id);
		model.addAttribute("brandList",this.brandService.getBrandList());
		return "wcf/brandList";
	}

	@RequestMapping(value="modify")
	public String modify(@RequestParam(value="id") int id,Model model)
	{
		Brand brand=this.brandService.getBrandById(id);
		model.addAttribute("brand",brand);
		return "wcf/modifyBrand";
	}
	
	@RequestMapping(value="modifyed")
	public String modifyed(Brand brand,Model model)
	{
		if(brand.getType()!=1)
			brand.setType(0);
		this.brandService.updateBrand(brand);
		model.addAttribute("brandList",this.brandService.getBrandList());
		return "wcf/brandList";
	}
}
