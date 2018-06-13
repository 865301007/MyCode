package com.xmu.mall.orderindex.tangkai.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmu.mall.orderindex.tangkai.model.Order;
import com.xmu.mall.orderindex.tangkai.model.SearchOrder;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:17:41
 */
@Repository
public interface OrderMapper {
	//具体实现在mapper层的OrderMapper.xml文件中
	public List<Order> getAllOrder();
	public Order getOrderByOrderId(@Param("order_id") long order_id);
	public void updateOrder(Order order);
	public List<Order> getShoppingCart(@Param("user_id") long user_id);
	
	public List<Order> getOrderByStatus(@Param("user_id") long user_id,@Param("order_status") int status);
	
	public void receiveOrder(@Param("order_id") long order_id, @Param("receive_time")  int receive_time);
	
	public void cancelOrder(@Param("order_id") long order_id, @Param("cancel_time") int cancel_time);
	
	public void addOrder(Order order);
	//获取当前用户所有订单
	public List<Order> getOrderByUser_id(@Param("user_id") long user_id);
	//查询订单
	public List<Order> searchOrder(SearchOrder searchOrder);
		
}
