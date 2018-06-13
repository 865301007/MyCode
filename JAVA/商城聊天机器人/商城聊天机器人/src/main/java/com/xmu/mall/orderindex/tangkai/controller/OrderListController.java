package com.xmu.mall.orderindex.tangkai.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.OrderGoods;
import com.xmu.mall.orderindex.tangkai.model.Page;
import com.xmu.mall.orderindex.tangkai.model.SearchOrder;
import com.xmu.mall.orderindex.tangkai.model.User;
import com.xmu.mall.orderindex.tangkai.service.OrderGoodsService;
import com.xmu.mall.orderindex.tangkai.service.OrderService;
import com.xmu.mall.orderindex.tangkai.service.UserService;



/**
 * @author tangkai
 * @data 2017年6月2日--下午12:48:00
 */

@Controller
@RequestMapping(value="/orderList")
public class OrderListController {
	
	@Autowired
	@Qualifier("tangkai_OrderService")
	private OrderService orderService;
	@Autowired
	@Qualifier("tangkai_OrderGoodsService")
	private OrderGoodsService orderGoodsService;
	@Autowired
	@Qualifier("tangkai_UserService")
	private UserService userService;
		
	@RequestMapping(method=RequestMethod.GET)
	public String getOrderList(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "redirect:/manager/login";
		}else if(session.getAttribute("manager_id") == null)
		{
			return "redirect:/manager/login";
		}
		
		List<Order> orders = orderService.getAllOrder();
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		
		if(page.getCurrentPage()==1){
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
	
		

		return "tangkai/orderlist";
	}

	@RequestMapping("lookupOrder")
	public String lookupOrder(Model model, HttpServletRequest request)
	{
		long order_id = Long.parseLong(request.getParameter("order_id"));
		List<OrderGoods> goods = orderGoodsService.getAllOrdersByOrderId(order_id);
		Order order = orderService.getOrderByOrderId(order_id);
		User user = userService.getUserByUserId(order.getUser_id());
		model.addAttribute("order", order);
		model.addAttribute("goodsList",goods);
		model.addAttribute("user", user);
		return "tangkai/mlookupOrder";
	}
	
	
	@RequestMapping("modifyOrder")
	public String modifyOrder(Model model, HttpServletRequest request)
	{
		long order_id = Long.parseLong(request.getParameter("order_id"));
		List<OrderGoods> goods = orderGoodsService.getAllOrdersByOrderId(order_id);
		Order order = orderService.getOrderByOrderId(order_id);
		model.addAttribute("order", order);
		model.addAttribute("goodsList",goods);
		return "tangkai/modifyOrder";
	}
	
	@RequestMapping("check")
	public String checkOrder(Model model, HttpServletRequest request) throws ParseException
	{
		long order_id = Long.parseLong(request.getParameter("order_id"));
		Order order = orderService.getOrderByOrderId(order_id);
		 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String t = df.format(d);
		long time_now = df.parse(t).getTime() / 1000;
		if(order.getOrder_status() == 1)
		{
			order.setOrder_status(2);
			order.setShipped_time((int)time_now);
		}
		orderService.updateOrder(order);
		List<Order> orders = orderService.getAllOrder();
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		
		if(page.getCurrentPage()==1){
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
	
		return "tangkai/orderlist";
	}
	
	@RequestMapping("checkExpressCode")
	public String checkExpressCode(Model model, HttpServletRequest request) throws ParseException
	{		
		long order_id = Long.parseLong(request.getParameter("order_id"));
		List<OrderGoods> goods = orderGoodsService.getAllOrdersByOrderId(order_id);
		Order order = orderService.getOrderByOrderId(order_id);
		User user = userService.getUserByUserId(order.getUser_id());
		model.addAttribute("order", order);
		model.addAttribute("goodsList",goods);
		model.addAttribute("user", user);
		return "tangkai/shipOrder";
	}
	
	@RequestMapping("checkedExpressCode")
	public String checkedExpressCode(Model model,  HttpServletRequest request) throws ParseException
	{
		long order_id = Long.parseLong(request.getParameter("order_id"));
		Order order = orderService.getOrderByOrderId(order_id);
		
		order.setExpressCode(request.getParameter("expressCode"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String t = df.format(d);
		long time_now = df.parse(t).getTime() / 1000;
		if(order.getOrder_status() == 2)
		{
			order.setOrder_status(3);
			order.setMake_time((int)time_now);
		}
		orderService.updateOrder(order);
		List<Order> orders = orderService.getAllOrder();
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		
		if(page.getCurrentPage()==1){
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
		return "tangkai/orderlist";
	}
	
	@RequestMapping("updateOrder")
    public String updateOrder(Model model,long order_id, int order_status,String remark, String expressCode, 
    		String isUrgency, String consignee, String address,String telephone){	
		Order order =orderService.getOrderByOrderId(order_id);
		//order.setOrder_id(order_id);
		order.setConsignee(consignee);
		order.setAddress(address);
		order.setTelephone(telephone);
		order.setOrder_status(order_status);
		order.setExpressCode(expressCode);
		order.setRemark(remark);
		order.setIsUrgency(isUrgency);
		if(order.getOrder_status() < 3)
			order.setExpressCode("");
		orderService.updateOrder(order);
		List<Order> orders = orderService.getAllOrder();
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		
		if(page.getCurrentPage()==1){
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
	
		
		return "tangkai/orderlist";
    }
	
	@RequestMapping("searchOrder")
	public String searchOrder()
	{
		return "searchOrder";
	}
	
	@RequestMapping("searchResult")
    public String searchResult(Model model,String order_sn, String before_add_time,
    		String after_add_time,int order_status,String isUrgency,String goods_name,String consignee){
		long longOrder_sn=0l;
		
		if(order_sn != "")
			longOrder_sn=Long.parseLong(order_sn);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		long long_before = 0;
		
		if(before_add_time!="" && !before_add_time.equals("choose time")) {
			try{				
				long_before = sdf.parse(before_add_time + " 00:00:00").getTime()/1000;  
			}catch(Exception e){
				long_before=0;
				e.printStackTrace(); 
			}			
		}
		long long_after = 0;
		if(after_add_time!="" && !after_add_time.equals("choose time")) {
			try{
				long_after = sdf.parse(after_add_time+" 23:59:59").getTime()/100;  
				//System.out.println(long_after);
			}catch(Exception e){
				long_after=0;
				e.printStackTrace(); 
			}			
		}
		
		SearchOrder searchOrder = new SearchOrder(longOrder_sn,order_status,consignee,goods_name,long_before,long_after,isUrgency);
		
		List<Order> orders = orderService.searchOrder(searchOrder);
		
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		
		if(page.getCurrentPage()==1){
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
	
		
		return "tangkai/orderlist";
    }
	
	@RequestMapping("prePage")
	public String prePage(Model model, HttpServletRequest request)
	{
		List<Order> orders = orderService.getAllOrder();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(currentPage-1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		if(page.getCurrentPage()>1&&page.getCurrentPage()<page.getTotalPage())
			model.addAttribute("orderlist", orders.subList((page.getCurrentPage()-1)*3, page.getCurrentPage()*3));
		if(page.getCurrentPage()==1){
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
		else if(page.getCurrentPage()==page.getTotalPage())
			model.addAttribute("orderlist", orders.subList((page.getCurrentPage()-1)*3, page.getTotalCount()));
		return "tangkai/orderlist";
	}
	
	@RequestMapping("nextPage")
	public String nextPage(Model model, HttpServletRequest request)
	{
		List<Order> orders = orderService.getAllOrder();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(currentPage+1);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		//model.addAttribute("orderlist",orders);
		if(page.getCurrentPage()>1&&page.getCurrentPage()<page.getTotalPage())//涓嶆槸绗竴椤典篃涓嶆槸鏈�鍚庝竴椤�
			model.addAttribute("orderlist", orders.subList((page.getCurrentPage()-1)*3, page.getCurrentPage()*3));
		if(page.getCurrentPage()==1){//鏄涓�椤�
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
		else if(page.getCurrentPage()==page.getTotalPage())//鏈�鍚庝竴椤�
			model.addAttribute("orderlist", orders.subList((page.getCurrentPage()-1)*3, page.getTotalCount()));
		return "tangkai/orderlist";
	}
	
	@RequestMapping("jumpPage")
	public String jumpPage(Model model, HttpServletRequest request)
	{
		List<Order> orders = orderService.getAllOrder();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		Page page=new Page();
		page.setEverypage(3);
		page.setTotalPage(orders.size()/3+1);
		page.setCurrentPage(currentPage);
		page.setHasNextPage(page.getTotalPage()>page.getCurrentPage()?true:false);
		page.setHasPrePage(page.getCurrentPage()>1?true:false);
		page.setTotalCount(orders.size());
		model.addAttribute("page", page);
		//model.addAttribute("orderlist",orders);
		if(page.getCurrentPage()>1&&page.getCurrentPage()<page.getTotalPage())//涓嶆槸绗竴椤典篃涓嶆槸鏈�鍚庝竴椤�
			model.addAttribute("orderlist", orders.subList((page.getCurrentPage()-1)*3, page.getCurrentPage()*3));
		if(page.getCurrentPage()==1){//鏄涓�椤�
			if(page.getTotalCount()<=3)
				model.addAttribute("orderlist", orders.subList(0, page.getTotalCount()));
			else
				model.addAttribute("orderlist", orders.subList(0, 3));
		}
		else if(page.getCurrentPage()==page.getTotalPage())//鏈�鍚庝竴椤�
			model.addAttribute("orderlist", orders.subList((page.getCurrentPage()-1)*3, page.getTotalCount()));
		return "tangkai/orderlist";
	}

}
