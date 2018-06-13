package com.xmu.mall.orderindex.tangkai.service;

import java.util.List;

import javax.validation.spi.ValidationProvider;

import com.xmu.mall.orderindex.tangkai.model.OrderGoods;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:04:07
 */
public interface OrderGoodsService {
	public List<OrderGoods> getAllOrdersByOrderId(long order_id);
	public void addOrderGoods(OrderGoods orderGoods);
}
