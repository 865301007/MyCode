package com.xmu.mall.goodsmgt.wcf.service;

import java.util.List;

import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.model.Stand;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:55
*/
public interface GoodsService {
	public List<Goods> getGoodsList();
	public boolean addGoods(Goods goods);
	public boolean deleteGoodsById(long id);
	public Goods getGoodsById(long id);
	public boolean updateGoods(Goods goods);
	public Goods getGoodsByGoodsCode(String serial_code);
	public List<Goods> getGoodsByGoodsName(String goods_name);
	
	public Stand getGoodsPrice(Long goods_id);
}
