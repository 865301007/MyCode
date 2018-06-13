package com.xmu.mall.orderindex.tangkai.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmu.mall.goodsindex.zjh.constant.RenderAjaxReturnResult;
import com.xmu.mall.goodsindex.zjh.model.AjaxReturnResult;
import com.xmu.mall.orderindex.tangkai.model.NewUser;
import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.OrderGoods;
import com.xmu.mall.orderindex.tangkai.model.ShowOrder;
import com.xmu.mall.orderindex.tangkai.model.UserAddress;
import com.xmu.mall.orderindex.tangkai.service.OrderGoodsService;
import com.xmu.mall.orderindex.tangkai.service.OrderService;
import com.xmu.mall.orderindex.tangkai.service.UserService;
import com.xmu.rebot.alice.service.AliceService;
import com.xmu.rebot.alice.service.OtherService;

import bitoflife.chatterbean.ChatterBean;
import bsh.This;



@Controller
@RequestMapping(value="/user/")
public class UserController {
	private final Logger logger=LoggerFactory.getLogger(This.class);
	@Autowired
	@Qualifier("tangkai_UserService")
	private UserService userService;
	
	@Autowired
	@Qualifier("tangkai_OrderService")
	private OrderService orderService;
	
	@Autowired
	@Qualifier("tangkai_OrderGoodsService")
	private OrderGoodsService orderGoodsService;
	
	/*
	@Autowired
	private AliceService aliceService;
	*/
	@Autowired
	private OtherService otherService;
	
	/*
	private ChatterBean chatterBean;
	
	@PostConstruct
	public void Init()
	{
		this.chatterBean=aliceService.getChatterBean();
	}
	*/
	
	@RequestMapping("index")
	public String getUserInfo(Model model, HttpServletRequest request)
	{
		if(request.getSession().getAttribute("user_id") == null) {
			model.addAttribute("username", "");
			model.addAttribute("result","");
			return "tangkai/login";
		}
	    return "tangkai/userIndex";		
	}
	
	@RequestMapping("login")
	public String login(Model model, HttpServletRequest request)
	{
		//long address_id = Long.parseLong(request.getParameter("address_id"));
		//long order_id = Long.parseLong(request.getParameter("order_id"));
		//long user_id = Long.parseLong(request.getParameter("user_id"));
		
	    //return "tangkai/userIndex";
		model.addAttribute("username", "");
		model.addAttribute("result","");
		return "tangkai/login";
	}
	
	@RequestMapping("register")
	public String register(Model model, HttpServletRequest request)
	{
		model.addAttribute("result","");
		return "tangkai/register";
	}
	
	@RequestMapping("addUser")
	public String addUser(Model model, HttpServletRequest request)
	{
		String mobile_phone = request.getParameter("mobile_phone");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("psw");
		long user_id = Long.parseLong(mobile_phone);
		
		if(userService.getUserByUserId(user_id) != null) {
			model.addAttribute("result","注册失败，该手机号已存在！");
			return "tangkai/register";
		}
		if(userService.getUserByUserName(user_name)!=null) {
			model.addAttribute("result","注册失败，该用户名已存在！");
			return "tangkai/register";
		}
		NewUser user = new NewUser();
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setMobile_phone(mobile_phone);
		user.setPassword(password);
		
		userService.addUser(user);
		
		HttpSession session = request.getSession(false);
		
		session.setAttribute("user_name", user_name);
		
		session.setAttribute("user_id", mobile_phone);
		
		model.addAttribute("result","");
		return "tangkai/registerSuccess";
	}
	
	@RequestMapping("loginCheck")
	public String loginCheck(Model model, HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");
		
		if(userService.checkUser(username, psw) == 0) {
			model.addAttribute("username", username);
			model.addAttribute("result","用户名或密码错误！");
			return "tangkai/login";
		}else {
			HttpSession session = request.getSession(false);
			String user_id = userService.getUserIdByName(username);
			
			//此时表示是用户ID登录，即手机号登录
			if(user_id == null) {
				user_id = username;
				username = userService.getUserByUserId(Long.parseLong(username)).getUser_name();
			}
			
			//System.out.println("userController中user_id为：" + user_id);
			//设置用户session
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_name", username);
			//转向主页，主要是跳转到根目录的控制器方法中
			return "forward:/";
		}
	    
		
	}
	
	@RequestMapping("exit")
	public String exitlogin(Model model, HttpServletRequest request) {
		//false代表：不创建session对象，只是从request中获取。
		HttpSession session = request.getSession(false);
		//设置用户session
		session.removeAttribute("user_id");
		session.removeAttribute("user_name");
		//转向主页，主要是跳转到根目录的控制器方法中
		return "forward:/";
	}
	
	@RequestMapping("chat")
	public String chat(Model model, HttpServletRequest request) {
		//false代表：不创建session对象，只是从request中获取。
		if(request.getSession(false).getAttribute("user_id") == null) {
			return "redirect:/user/logion";
		}else {
			return "tangkai/chat";
		}
	}
	
	/**
	 * 删除购物车
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/response",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturnResult chatResponse(@RequestBody String request)
	{
		this.logger.debug(request);
		
		return RenderAjaxReturnResult.renderSuccessResult(otherService.getAnswer(request.replace("\"", "")));
	}
	
	@RequestMapping("getAllOrder")
	public String getAllOrder(Model model, HttpServletRequest request) {
		//false代表：不创建session对象，只是从request中获取。
		if(request.getSession(false).getAttribute("user_id") == null) {
			return "redirect:/user/logion";
		}
		long user_id = Long.parseLong(request.getSession(false).getAttribute("user_id").toString());
		
		List<Order> orders = orderService.getOrderByUser_id(user_id);
		List<ShowOrder> orderGoodsList = new ArrayList<ShowOrder>();
		for(Order order:orders) {
			List<OrderGoods> goodslist = orderGoodsService.getAllOrdersByOrderId(order.getOrder_id());
			orderGoodsList.add(new ShowOrder(order.getOrder_id(), goodslist));
		}
		
		model.addAttribute("orderGoodsList", orderGoodsList);
		
		return "tangkai/allAndPendingDelivery";
	}
	
	@RequestMapping("getPendingDeliveryOrder")
	public String getPendingDeliveryOrder(Model model, HttpServletRequest request) {
		//false代表：不创建session对象，只是从request中获取。
		if(request.getSession(false).getAttribute("user_id") == null) {
			return "redirect:/user/logion";
		}
		long user_id = Long.parseLong(request.getSession(false).getAttribute("user_id").toString());
		
		//未定制订单
		List<Order> orders = orderService.getOrderByStatus(user_id, 1);
		
		List<ShowOrder> orderGoodsList = new ArrayList<ShowOrder>();
		for(Order order:orders) {
			List<OrderGoods> goodslist = orderGoodsService.getAllOrdersByOrderId(order.getOrder_id());
			orderGoodsList.add(new ShowOrder(order.getOrder_id(), goodslist));
		}
		
		//定制中订单
		orders = orderService.getOrderByStatus(user_id, 2);
		
		for(Order order:orders) {
			List<OrderGoods> goodslist = orderGoodsService.getAllOrdersByOrderId(order.getOrder_id());
			orderGoodsList.add(new ShowOrder(order.getOrder_id(), goodslist));
		}
		
		model.addAttribute("orderGoodsList", orderGoodsList);
		
		return "tangkai/allAndPendingDelivery";
	}
	
	@RequestMapping("getPendingReceiveOrder")
	public String getPendingReceiveOrder(Model model, HttpServletRequest request) {
		//false代表：不创建session对象，只是从request中获取。
		if(request.getSession(false).getAttribute("user_id") == null) {
			return "redirect:/user/logion";
		}
		long user_id = Long.parseLong(request.getSession(false).getAttribute("user_id").toString());
		
		//未定制订单
		List<Order> orders = orderService.getOrderByStatus(user_id, 3);
		
		List<ShowOrder> orderGoodsList = new ArrayList<ShowOrder>();
		for(Order order:orders) {
			List<OrderGoods> goodslist = orderGoodsService.getAllOrdersByOrderId(order.getOrder_id());
			orderGoodsList.add(new ShowOrder(order.getOrder_id(), goodslist));
		}
		
		model.addAttribute("orderGoodsList", orderGoodsList);
		
		return "tangkai/pendingReceive";
	}
	
	@RequestMapping("getPendingPayOrder")
	public String getPendingPayOrder(Model model, HttpServletRequest request) {
		//false代表：不创建session对象，只是从request中获取。
		if(request.getSession(false).getAttribute("user_id") == null) {
			return "redirect:/user/logion";
		}
		long user_id = Long.parseLong(request.getSession(false).getAttribute("user_id").toString());
		
		//未付款订单
		List<Order> orders = orderService.getOrderByStatus(user_id, 0);
		
		List<ShowOrder> orderGoodsList = new ArrayList<ShowOrder>();
		for(Order order:orders) {
			List<OrderGoods> goodslist = orderGoodsService.getAllOrdersByOrderId(order.getOrder_id());
			orderGoodsList.add(new ShowOrder(order.getOrder_id(), goodslist));
		}
		
		model.addAttribute("orderGoodsList", orderGoodsList);
		
		return "tangkai/pendingPay";
	}
	
	@RequestMapping("lookupOrder")
	String lookupOrder(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		long order_id = Long.parseLong(request.getParameter("order_id"));
		//long order_id = Long.parseLong(session.getAttribute("order_id").toString());
		//session.removeAttribute("order_id");
		long user_id = Long.parseLong(session.getAttribute("user_id").toString());
		Order order = orderService.getOrderByOrderId(order_id);
		List<OrderGoods> goods = orderGoodsService.getAllOrdersByOrderId(order_id);
		model.addAttribute("order", order);
		model.addAttribute("goodsList",goods);
		model.addAttribute("user", userService.getUserByUserId(user_id));
		
	    return "tangkai/lookupOrder";
	}
	
	@RequestMapping("received")
	String received(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		long order_id = Long.parseLong(request.getParameter("order_id"));
		//long order_id = Long.parseLong(session.getAttribute("order_id").toString());
		long user_id = Long.parseLong(session.getAttribute("user_id").toString());
		//Order order = orderService.getOrderByOrderId(order_id);
		
		orderService.receiveOrder(order_id);
		Order order = orderService.getOrderByOrderId(order_id);
		List<OrderGoods> goods = orderGoodsService.getAllOrdersByOrderId(order_id);
		model.addAttribute("order", order);
		model.addAttribute("goodsList",goods);
		model.addAttribute("user", userService.getUserByUserId(user_id));
		
	    return "tangkai/lookupOrder";
	}
	
	@RequestMapping("toPay")
	String toPay(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		long order_id = Long.parseLong(request.getParameter("order_id"));
		//long order_id = Long.parseLong(session.getAttribute("order_id").toString());
		long user_id = Long.parseLong(session.getAttribute("user_id").toString());
		//Order order = orderService.getOrderByOrderId(order_id);
		
		Order order = orderService.getOrderByOrderId(order_id);
		model.addAttribute("order_id", order_id);
		
		model.addAttribute("pay_price", order.getOrder_price());
		return "tangkai/pay";
	}
	
	@RequestMapping("pay")
	String payOrder(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		long order_id = Long.parseLong(request.getParameter("order_id"));
		//long order_id = Long.parseLong(session.getAttribute("order_id").toString());
		//session.removeAttribute("order_id");
		long user_id = Long.parseLong(session.getAttribute("user_id").toString());
		Order order = orderService.getOrderByOrderId(order_id);
		List<OrderGoods> goods = orderGoodsService.getAllOrdersByOrderId(order_id);
		
		order.setOrder_status(1);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String t = df.format(d);
		try {
			int pay_time = (int) (df.parse(t).getTime() / 1000);
			
			order.setPay_time(pay_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		orderService.updateOrder(order);
		model.addAttribute("order", order);
		model.addAttribute("goodsList",goods);
		model.addAttribute("user", userService.getUserByUserId(user_id));
		
	    return "tangkai/lookupOrder";
	}
	
	@RequestMapping("getAllAddress")
	public String getAllAddress(Model model, HttpServletRequest request)
	{
		long user_id = Long.parseLong(request.getSession(false).getAttribute("user_id").toString());
		List<UserAddress> addresses = userService.getNotDefaultAddress(user_id);
		UserAddress defaultAddress = userService.getDefaultAddress(user_id);
		model.addAttribute("defaultAddress", defaultAddress);
		//model.addAttribute("user_id", user_id);
		model.addAttribute("userAddresses", addresses);
		return "tangkai/deliveryAddress";
	}
	
	@RequestMapping("getAddress")
	public String getAddress(Model model, HttpServletRequest request)
	{
		long user_id = Long.parseLong(request.getSession(false).getAttribute("user_id").toString());
		List<UserAddress> addresses = userService.getNotDefaultAddress(user_id);
		UserAddress defaultAddress = userService.getDefaultAddress(user_id);
		model.addAttribute("defaultAddress", defaultAddress);
		//model.addAttribute("user_id", user_id);
		model.addAttribute("userAddresses", addresses);
		return "tangkai/deliveryAddress";
	}
	
	@RequestMapping("newAddress")
	public String newAddress(Model model, HttpServletRequest request)
	{
		long user_id = Long.parseLong(request.getParameter("user_id"));
		//List<UserAddress> addresses = userService.getUserAddressesByUserId(user_id);
		//model.addAttribute("userAddresses", addresses);
		model.addAttribute("user_id", user_id);
		return "tangkai/newAddress";
	}
	
	@RequestMapping("addNewAddress")
	public String addNewAddress(Model model, HttpServletRequest request)
	{
		//long user_id = Long.parseLong(request.getParameter("user_id"));
		
		HttpSession session = request.getSession(false);
		long user_id = Long.parseLong(session.getAttribute("user_id").toString());
		
		String consignee = request.getParameter("consignee");
		String telephone = request.getParameter("telephone");
		String zipcode = request.getParameter("zipcode");
		String detail = request.getParameter("detail");
		UserAddress address = new UserAddress();
		address.setUser_id(user_id);
		address.setZipcode(zipcode);
		address.setConsignee(consignee);
		address.setDetail(detail);
		address.setTelephone(telephone);
		
		UserAddress defaultAddress = userService.getDefaultAddress(user_id);
		
		if(defaultAddress == null) {
			address.setMydefault("Y");
		}
		
		userService.addUserAddress(address);
		List<UserAddress> addresses = userService.getNotDefaultAddress(user_id);
		defaultAddress = userService.getDefaultAddress(user_id);
		model.addAttribute("defaultAddress", defaultAddress);
		//model.addAttribute("user_id", user_id);
		model.addAttribute("userAddresses", addresses);
		return "tangkai/deliveryAddress";
		
	}
}
