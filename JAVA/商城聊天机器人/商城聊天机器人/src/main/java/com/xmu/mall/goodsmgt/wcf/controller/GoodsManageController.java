package com.xmu.mall.goodsmgt.wcf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.File;  
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xmu.mall.goodsmgt.wcf.model.Brand;
import com.xmu.mall.goodsmgt.wcf.model.Category;
import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.service.BrandService;
import com.xmu.mall.goodsmgt.wcf.service.CategoryService;
import com.xmu.mall.goodsmgt.wcf.service.GoodsService;
import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.Page;


/**
*@author created by �����
*@date 2017��5��31��--����1:43:32
*/
@Controller
@RequestMapping(value="GoodsManage/")
public class GoodsManageController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private int page_size=10;
	@Autowired
	@Qualifier("wcf_GoodsService")
	private GoodsService goodsService;
	@Autowired
	@Qualifier("wcf_CategoryService")
	private CategoryService categoryService;
	@Autowired
	@Qualifier("wcf_BrandService")
	private BrandService brandService;
	@Autowired 
	private HttpServletRequest request;  
	@RequestMapping(value="showList",method=RequestMethod.GET)
	public String showList(Model model)
	{

		
		List<Goods> goodsList=this.goodsService.getGoodsList();
		
		Page page=new Page();
		page.setEverypage(page_size);
		page.setTotalPage(goodsList.size()/page_size+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage());
		page.setHasPrePage(page.getCurrentPage()>1);
		page.setTotalCount(goodsList.size());
		model.addAttribute("page",page);
		
		if(page.getCurrentPage()==1)
		{
			if(page.getTotalCount()<=page_size)
				model.addAttribute("goodsList",goodsList.subList(0, page.getTotalCount()));
			else
				model.addAttribute("goodsList",goodsList.subList(0, page_size));
		}
		
		//model.addAttribute("goodsList", goodsList);
		return "wcf/goodsList";
	}

	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model)
	{

		List<Category> CategoryList=this.categoryService.getCategoryList();
		List<Brand> brandList=this.brandService.getBrandList();
		model.addAttribute("CategoryList", CategoryList);
		model.addAttribute("brandList", brandList);
		return "wcf/addGoods";
	}
	
	@RequestMapping(value="added",method=RequestMethod.POST)
	public String added(@RequestParam("file") CommonsMultipartFile  file,Goods goods,Model model)
	{
	
		
		logger.debug("added");
		String path=this.request.getServletContext().getRealPath("")+"resources/images/wcf/"+file.getOriginalFilename();
		goods.setImage_path("/resources/images/wcf/"+file.getOriginalFilename());
		System.out.println(path);
		File newFile=new File(path);
		try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		goods.setAdd_time(df.format(new Date()));
		goods.setLast_modified_time(df.format(new Date()));
		goods.setRedeem_point(goods.getMarket_price().intValue());
		goods.setPre_sal_number(goods.getStock_count());
		this.goodsService.addGoods(goods);
		
		List<Goods> goodsList=this.goodsService.getGoodsList();
		
		Page page=new Page();
		page.setEverypage(page_size);
		page.setTotalPage(goodsList.size()/page_size+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage());
		page.setHasPrePage(page.getCurrentPage()>1);
		page.setTotalCount(goodsList.size());
		model.addAttribute("page",page);
		
		if(page.getCurrentPage()==1)
		{
			if(page.getTotalCount()<=page_size)
				model.addAttribute("goodsList",goodsList.subList(0, page.getTotalCount()));
			else
				model.addAttribute("goodsList",goodsList.subList(0, page_size));
		}
		//model.addAttribute("goodsList", this.goodsService.getGoodsList());
		return "wcf/goodsList";
	}
	
	@RequestMapping(value="delete")
	public String delete(@RequestParam(value="id") int id,Model model)
	{

		this.goodsService.deleteGoodsById(id);
		List<Goods> goodsList=this.goodsService.getGoodsList();
		
		Page page=new Page();
		page.setEverypage(page_size);
		page.setTotalPage(goodsList.size()/page_size+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage());
		page.setHasPrePage(page.getCurrentPage()>1);
		page.setTotalCount(goodsList.size());
		model.addAttribute("page",page);
		
		if(page.getCurrentPage()==1)
		{
			if(page.getTotalCount()<=page_size)
				model.addAttribute("goodsList",goodsList.subList(0, page.getTotalCount()));
			else
				model.addAttribute("goodsList",goodsList.subList(0, page_size));
		}
		//model.addAttribute("goodsList", this.goodsService.getGoodsList());
		return "wcf/goodsList";
	}
	
	@RequestMapping(value="modify")
	public String modify(@RequestParam(value="id") int id,Model model)
	{
		
		Goods goods=this.goodsService.getGoodsById(id);
		List<Category> CategoryList=this.categoryService.getCategoryList();
		List<Brand> brandList=this.brandService.getBrandList();
		model.addAttribute("CategoryList", CategoryList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("goods", goods);
		return "wcf/modifyGoods";
	}
	
	@RequestMapping(value="modifyed")
	public String modifyed(@RequestParam("file") CommonsMultipartFile  file,Goods goods,Model model)
	{
		if(!file.isEmpty())
		{
			String basePath = "src/main/webapp/resources/images/wcf/";
			String path=basePath+file.getOriginalFilename();
			goods.setImage_path("/resources/images/wcf/"+file.getOriginalFilename());
			//System.out.println(path);
			File newFile=new File(path);
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.goodsService.updateGoods(goods);
		
		
		
List<Goods> goodsList=this.goodsService.getGoodsList();
		
		Page page=new Page();
		page.setEverypage(page_size);
		page.setTotalPage(goodsList.size()/page_size+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage());
		page.setHasPrePage(page.getCurrentPage()>1);
		page.setTotalCount(goodsList.size());
		model.addAttribute("page",page);
		
		if(page.getCurrentPage()==1)
		{
			if(page.getTotalCount()<=page_size)
				model.addAttribute("goodsList",goodsList.subList(0, page.getTotalCount()));
			else
				model.addAttribute("goodsList",goodsList.subList(0, page_size));
		}
		//model.addAttribute("goodsList", this.goodsService.getGoodsList());
		return "wcf/goodsList";
	}
	
	@RequestMapping("prePage")
	public String prePage(Model model,HttpServletRequest request)
	{
		
		List<Goods> goodsList =goodsService.getGoodsList();
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		Page page=new Page();
		page.setEverypage(page_size);
		page.setTotalPage(goodsList.size()/page_size+1);
		page.setCurrentPage(currentPage-1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(goodsList.size());
		model.addAttribute("page", page);
		if(page.getCurrentPage()>1&&page.getCurrentPage()<page.getTotalPage())
			model.addAttribute("goodsList", goodsList.subList((page.getCurrentPage()-1)*page_size, page.getCurrentPage()*page_size));
		if(page.getCurrentPage()==1){
			if(page.getTotalCount()<=page_size)
				model.addAttribute("goodsList", goodsList.subList(0, page.getTotalCount()));
			else
				model.addAttribute("goodsList", goodsList.subList(0, page_size));
		}
		else if(page.getCurrentPage()==page.getTotalPage())
			model.addAttribute("goodsList", goodsList.subList((page.getCurrentPage()-1)*page_size, page.getTotalCount()));
		return "wcf/goodsList";
		
	}
	
	@RequestMapping("nextPage")
	public String nextPage(Model model, HttpServletRequest request)
	{
		List<Goods> goodsList=goodsService.getGoodsList();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		Page page=new Page();
		page.setEverypage(page_size);
		page.setTotalPage(goodsList.size()/page_size+1);
		page.setCurrentPage(currentPage+1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(goodsList.size());
		model.addAttribute("page", page);
		//model.addAttribute("orderlist",orders);
		if(page.getCurrentPage()>1&&page.getCurrentPage()<page.getTotalPage())//涓嶆槸绗竴椤典篃涓嶆槸鏈�鍚庝竴椤�
			model.addAttribute("goodsList", goodsList.subList((page.getCurrentPage()-1)*page_size, page.getCurrentPage()*page_size));
		if(page.getCurrentPage()==1){//鏄涓�椤�
			if(page.getTotalCount()<=page_size)
				model.addAttribute("goodsList", goodsList.subList(0, page.getTotalCount()));
			else
				model.addAttribute("goodsList", goodsList.subList(0, page_size));
		}
		else if(page.getCurrentPage()==page.getTotalPage())//鏈�鍚庝竴椤�
			model.addAttribute("goodsList", goodsList.subList((page.getCurrentPage()-1)*page_size, page.getTotalCount()));
		return "wcf/goodsList";
	}
	

}
