package com.xmu.mall.orderindex.tangkai.controller;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmu.mall.goodsindex.zjh.service.IShoppingCartService;
import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.service.outter.IGoodsMapper;
import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.OrderGoods;
import com.xmu.mall.orderindex.tangkai.model.OrderReadyPay;
import com.xmu.mall.orderindex.tangkai.model.UserAddress;
import com.xmu.mall.orderindex.tangkai.service.OrderGoodsService;
import com.xmu.mall.orderindex.tangkai.service.OrderService;
import com.xmu.mall.orderindex.tangkai.service.UserService;


/**
 * @author tangkai
 * @data 2017年6月2日--下午4:17:11
 */
@Controller
@RequestMapping(value="/cart/")
public class CartController {
	
	@Autowired
	@Qualifier("tangkai_OrderService")
	private OrderService orderService;
	@Autowired
	@Qualifier("tangkai_OrderGoodsService")
	private OrderGoodsService orderGoodsService;
	@Autowired
	@Qualifier("tangkai_UserService")
	private UserService userService;
	@Autowired
	@Qualifier("wcfIGoodsMapper")
	private IGoodsMapper goodService;
	@Autowired
	@Qualifier("ShoppingCartService")
	private IShoppingCartService shoppingCartService;
	
	
	@RequestMapping("addOrder")
	public String addOrder(Model model, HttpServletRequest request)
	{
		long address_id = Long.parseLong(request.getParameter("address_id"));
		HttpSession session = request.getSession(false);
		long user_id = Long.parseLong(session.getAttribute("user_id").toString());
		Order order = new Order();
        
        order.setOrder_sn(orderService.createOrder_sn()); 
        
		//User user = userService.getUserByUserId(user_id);
		UserAddress address = userService.getAddressById(address_id);
		if(address!=null) {
			order.setConsignee(address.getConsignee());		
			order.setZipcode(address.getZipcode());		
			order.setAddress(address.getDetail());
			order.setTelephone(address.getTelephone());
		}
		order.setOrder_status(0);
		order.setIsUrgency("N");
		order.setUser_id(user_id);
		order.setTotal_weight(0d);
		order.setOrder_price(0d);
		order.setRemark("");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String t = df.format(d);
		try {
			int time_now = (int) (df.parse(t).getTime() / 1000);
			
			order.setAdd_time(time_now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		orderService.addOrder(order);
		
		double total_weight = 0;
		
		double order_price = 0;
		
		String[] goodsId = (String[])session.getAttribute("goodsId");
		String[] goodsNumber = (String[])session.getAttribute("goodsNumber");
		
		List<OrderGoods> orderGoodsList=new ArrayList<OrderGoods>();
		if(goodsId.length==goodsNumber.length)
		{
			for(int i=0;i<goodsId.length;++i)
			{
				OrderGoods orderGoods=new OrderGoods();
				Goods goods = (Goods) goodService.getGoodsByGoodsId(Long.parseLong(goodsId[i]));
				orderGoods.setOrder_id(order.getOrder_id());
				orderGoods.setGoods_id(Long.parseLong(goodsId[i]));
				orderGoods.setName(goods.getName());
				orderGoods.setPrice(goods.getReal_price());
				orderGoods.setDescription(goods.getDescription());
				orderGoods.setNum(Integer.parseInt(goodsNumber[i]));
				total_weight += goods.getWeight() * Integer.parseInt(goodsNumber[i]);
				order_price += goods.getReal_price() * Integer.parseInt(goodsNumber[i]);
				orderGoodsService.addOrderGoods(orderGoods);
				goodService.deleteGoodsNumById(goodsId[i], Integer.parseInt(goodsNumber[i]));
				shoppingCartService.deleteByUserIdAndGoodsId(user_id, Long.parseLong(goodsId[i]));
				orderGoodsList.add(orderGoods);
			}
		}
		
		order.setTotal_weight(total_weight);
		order.setOrder_price(order_price);
		//System.out.println(order.getTotal_weight() + "   " + order.getOrder_price());
		orderService.updateOrder(order);
		
		session.removeAttribute("goodsId");
		session.removeAttribute("goodsNumber");
		session.removeAttribute("goodslist");
		session.removeAttribute("orderReadyPay");
		
		model.addAttribute("order_id", order.getOrder_id());
		
		model.addAttribute("pay_price", order_price);
		return "tangkai/pay";
	}
	
	@RequestMapping("chooseAddress")
	public String chooseAddress(Model model, HttpServletRequest request)
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
	
	@RequestMapping("getAddress")
	public String getAddress(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		//long user_id = Long.parseLong(session.getAttribute("user_id").toString());
		//从界面处获得
		long address_id = Long.parseLong(request.getParameter("address_id"));
		
		/*List<Order> orders = orderService.getShoppingCart(user_id);			
		if(orders.size() == 0)
			return "test";*/		
		//Order order = orders.get(0);		
		//User user = userService.getUserByUserId(user_id);		
		//List<OrderGoods> goodslist = orderGoodsService.getAllOrdersByOrderId(order.getOrder_id());
		
		UserAddress address = userService.getAddressById(address_id);
		
		List<OrderGoods> goodslist = (List<OrderGoods>)session.getAttribute("goodslist");
		
		
		
		OrderReadyPay orderReadyPay = (OrderReadyPay)session.getAttribute("orderReadPay");
		
		model.addAttribute("userAddress", address);
		
		model.addAttribute("goodslist", goodslist);
		model.addAttribute("orderReadyPay", orderReadyPay);
		
		//model.addAttribute("user", user);
		return "tangkai/balance";
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
		
		//List<UserAddress> addresses = userService.getNotDefaultAddress(user_id);
		
		//address = addresses.get(addresses.size() - 1);	
		
		List<OrderGoods> goodslist = (List<OrderGoods>)session.getAttribute("goodslist");

		OrderReadyPay orderReadyPay = (OrderReadyPay)session.getAttribute("orderReadPay");
		
		model.addAttribute("userAddress", address);
		
		model.addAttribute("goodslist", goodslist);
		model.addAttribute("orderReadyPay", orderReadyPay);
		return "tangkai/balance";
		
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
}
