package com.xmu.mall.orderindex.tangkai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmu.mall.orderindex.tangkai.mapper.OrderGoodsMapper;
import com.xmu.mall.orderindex.tangkai.model.OrderGoods;
import com.xmu.mall.orderindex.tangkai.service.OrderGoodsService;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:14:27
 */
@Service("tangkai_OrderGoodsService")
@Transactional(rollbackFor = Exception.class)
public class OrderGoodsServiceImpl implements OrderGoodsService{

	
	@Autowired (required=false) 
	private OrderGoodsMapper orderGoodsMapper;


	@Override
	public List<OrderGoods> getAllOrdersByOrderId(long order_id) {
		// TODO Auto-generated method stub
		return this.orderGoodsMapper.getAllGoodsByOrderId(order_id);
	}


	@Override
	public void addOrderGoods(OrderGoods orderGoods) {
		// TODO Auto-generated method stub
		orderGoodsMapper.addOrderGoods(orderGoods);
	}
	


}
