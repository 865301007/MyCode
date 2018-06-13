package com.xmu.mall.orderindex.tangkai.service;

import java.util.List;

import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.SearchOrder;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:03:50
 */
public interface OrderService {
	public List<Order> getAllOrder();
	public Order getOrderByOrderId(long order_id);
	public void updateOrder(Order order);
	public List<Order> getShoppingCart(long user_id);
	public void addOrder(Order order);

	public String createOrder_sn();
	
	public List<Order> searchOrder(SearchOrder searchOrder);
	
	public List<Order> getOrderByStatus(long user_id, int status);
	
	public void receiveOrder(long order_id);
	
	public void cancelOrder(long order_id);
	
	public List<Order> getOrderByUser_id(long user_id);
}
