package com.xmu.mall.orderindex.tangkai.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmu.mall.orderindex.tangkai.model.OrderGoods;

//@Repository用于标注数据访问组件，即DAO组件；
/**
 * @author tangkai
 * @data 2017年6月2日--下午4:17:34
 */
@Repository
public interface OrderGoodsMapper {
	public List<OrderGoods> getAllGoodsByOrderId(long order_id);
	public void addOrderGoods(OrderGoods orderGoods);
}
