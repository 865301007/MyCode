package com.xmu.mall.orderindex.tangkai.model;

import java.util.List;

public class ShowOrder {
	long order_id;
	List<OrderGoods> goodslist;
	
	
	public ShowOrder(long order_id, List<OrderGoods> gooslist) {
		super();
		this.order_id = order_id;
		this.goodslist = gooslist;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public List<OrderGoods> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<OrderGoods> gooslist) {
		this.goodslist = gooslist;
	}
	
}
