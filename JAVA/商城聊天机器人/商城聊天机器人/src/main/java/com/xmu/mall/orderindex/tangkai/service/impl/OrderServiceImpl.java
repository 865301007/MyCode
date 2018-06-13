package com.xmu.mall.orderindex.tangkai.service.impl;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmu.mall.orderindex.tangkai.mapper.OrderMapper;
import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.SearchOrder;
import com.xmu.mall.orderindex.tangkai.service.OrderService;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:14:33
 */
@Service("tangkai_OrderService")
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService{
	
	@Autowired (required=false) 
	private OrderMapper orderMapper;
		
	public List<Order> getAllOrder()
	{
		if(orderMapper == null)
			System.out.println("I am so sad");
		return orderMapper.getAllOrder();
	}

	@Override
	public Order getOrderByOrderId(long order_id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderByOrderId(order_id);
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderMapper.updateOrder(order);
	}

	@Override
	public List<Order> getShoppingCart(long user_id) {
		// TODO Auto-generated method stub
		return orderMapper.getShoppingCart(user_id);
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderMapper.addOrder(order);
	}
	@Override
	public String createOrder_sn() {
		// TODO Auto-generated method stub
		Format format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(new Date());
        int x = (new Random(System.currentTimeMillis())).nextInt(100000);
        
        String order_sn = time + String.format("%05d",x);
		return order_sn;
	}
	
	@Override
	public List<Order> getOrderByStatus(long user_id, int order_status) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderByStatus(user_id, order_status);
	}

	@Override
	public void receiveOrder(long order_id) {
		// TODO Auto-generated method stub
		try {
			orderMapper.receiveOrder(order_id, getUnxiTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void cancelOrder(long order_id) {
		// TODO Auto-generated method stub
		try {
			orderMapper.cancelOrder(order_id, getUnxiTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getUnxiTime() throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String t = df.format(d);
		int time_now = (int) (df.parse(t).getTime() / 1000);
		
		return time_now;
	}

	@Override
	public List<Order> getOrderByUser_id(long user_id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderByUser_id(user_id);
	}
	
	@Override
	public List<Order> searchOrder(SearchOrder searchOrder) {
		// TODO Auto-generated method stub
		return orderMapper.searchOrder(searchOrder);
	}

}
