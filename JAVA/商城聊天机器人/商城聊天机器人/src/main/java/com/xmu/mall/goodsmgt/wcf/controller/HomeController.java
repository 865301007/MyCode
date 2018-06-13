package com.xmu.mall.goodsmgt.wcf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmu.mall.goodsmgt.wcf.model.Brand;
import com.xmu.mall.goodsmgt.wcf.service.BrandService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
*@author created by �����
*@date 2017��5��31��--����1:43:41
*/
@Controller
@RequestMapping(value="/GoodsMgt")
public class HomeController {
	


	@Autowired
	@Qualifier("wcf_BrandService")
	private BrandService brandService;
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "redirect:/manager/login";
		}else if(session.getAttribute("manager_id") == null)
		{
			return "redirect:/manager/login";
		}
		System.out.println("OK");
		//System.out.println(this.brandService.getBrandList().get(0).getDescription());
		List<Brand> brandList=this.brandService.getBrandList();
		model.addAttribute("brandList",brandList);
		return "wcf/home";
	}
	
	public HomeController()
	{
		System.out.println("HomeController��ʼ��!");
	}

}