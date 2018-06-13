package com.xmu.mall.goodsindex.zjh.controller;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmu.mall.goodsindex.zjh.constant.RenderAjaxReturnResult;
import com.xmu.mall.goodsindex.zjh.model.AjaxReturnResult;
import com.xmu.mall.goodsindex.zjh.model.OrderInfo;
import com.xmu.mall.goodsindex.zjh.model.ShoppingCart;
import com.xmu.mall.goodsindex.zjh.service.IShoppingCartService;
import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.service.outter.IGoodsMapper;
import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.OrderGoods;
import com.xmu.mall.orderindex.tangkai.model.OrderReadyPay;
import com.xmu.mall.orderindex.tangkai.model.User;
import com.xmu.mall.orderindex.tangkai.model.UserAddress;
import com.xmu.mall.orderindex.tangkai.service.OrderGoodsService;
import com.xmu.mall.orderindex.tangkai.service.OrderService;
import com.xmu.mall.orderindex.tangkai.service.UserService;

@Controller
@RequestMapping(value="/cart")
public class ShoppingCartController {
	
	@Autowired
	@Qualifier("ShoppingCartService")
	private IShoppingCartService shoppingCartService;
	
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
	
	
	/**
	 * 跳转到购物车主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String cartIndex(Model model,HttpSession session)
	{
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/user/login";
			//return new ModelAndView("redirect:/toList");
		}
		Long userId=Long.valueOf(session.getAttribute("user_id").toString());
		model.addAttribute("shoppingCartList",shoppingCartService.getShoppingCartListByUserId(userId));
		return "zjh/cartIndex";
	}
	
	/**
	 * 添加购物车
	 * @param shopppingCart
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturnResult addShoppingCart(
			@ModelAttribute ShoppingCart shopppingCart,
			Model model,
			HttpSession session)
	{
		if(session.getAttribute("user_id") == null) {
			return RenderAjaxReturnResult.renderErrorResult("请先登录!");
		}
		//需要session
		shopppingCart.setUserId(Long.valueOf(session.getAttribute("user_id").toString()));
		return shoppingCartService.add(shopppingCart);
	}
	
	/**
	 * 删除购物车
	 * @param shoppingCarts
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturnResult deleteShoppingCarts(
			@RequestBody List<ShoppingCart> shoppingCarts
			)
	{
		return shoppingCartService.delete(shoppingCarts);
	}
	
	/**
	 * 更新购物车
	 * @param shoppingCarts
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturnResult updateShoppingCarts(
			@RequestBody List<ShoppingCart> shoppingCarts
			)
	{
		return shoppingCartService.update(shoppingCarts);
	}
	
	/**
	 * 处理订单页面
	 * @param goodsId
	 * @param goodsNumber
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/order",method=RequestMethod.POST)
	public String turnToOrderPage(
			String[]goodsId,
			String[]goodsNumber,
			Model model, HttpSession session)
	{
		Long user_id=Long.valueOf(session.getAttribute("user_id").toString());
		//System.out.println(user_id);
		//System.out.println("xmu.mall.goodsindex.zjh.controller.ShoppingCartController.turnToOrderPage");
		OrderReadyPay orderReadyPay = new OrderReadyPay();
				
		//User user = userService.getUserByUserId(user_id);
		UserAddress address = userService.getDefaultAddress(user_id);
		orderReadyPay.setOrder_status(0);
		orderReadyPay.setUser_id(user_id);
		orderReadyPay.setTotal_weight(0d);
		orderReadyPay.setOrder_price(0d);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String t = df.format(d);
		try {
			int time_now = (int) (df.parse(t).getTime() / 1000);
			
			orderReadyPay.setAdd_time(time_now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double total_weight = 0;
		
		double order_price = 0;
		List<Goods> goodsList=new ArrayList<Goods>();
		if(goodsId.length==goodsNumber.length)
		{
			for(int i=0;i<goodsId.length;++i)
			{
				Goods goods = (Goods) goodService.getGoodsByGoodsId(Long.parseLong(goodsId[i]));
				goodsList.add(goods);
				total_weight += goods.getWeight() * Integer.parseInt(goodsNumber[i]);
				order_price += goods.getReal_price() * Integer.parseInt(goodsNumber[i]);
			}
		}
		
		orderReadyPay.setTotal_weight(total_weight);
		orderReadyPay.setOrder_price(order_price);
		
		model.addAttribute("userAddress", address);
		model.addAttribute("goodslist", goodsList);
		
		model.addAttribute("orderReadyPay", orderReadyPay);
		
		//一下两个属性是为了在选择收货地址之后，拥有这两个属性
		session.setAttribute("goodslist", goodsList);
		session.setAttribute("orderReadyPay", orderReadyPay);
		
		//以下两个属性是为了后面提交订单后创建订单使用，用后销毁
		session.setAttribute("goodsId",goodsId);
		session.setAttribute("goodsNumber", goodsNumber);
		//model.addAttribute("user", user);
		return "tangkai/balance";
		
	}
}
