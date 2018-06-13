package com.xmu.mall.goodsmgt.wcf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.service.outter.SelectParameters;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:03
*/
public interface GoodsMapper {
	public List<Goods> getGoodsList();
	public boolean addGoods(Goods goods);
	public boolean deleteGoodsById(long id);
	public Goods getGoodsById(long id);
	public boolean updateGoods(Goods goods);
	public Goods getGoodsByGoodsCode(String serial_code);
	public List<Goods> getGoodsByGoodsName(String goods_name);
	public List<Goods> getGoodsBy(SelectParameters selectParameters);
	public Boolean deleteGoodsNumById(@Param("goodsId") String goodsId, @Param("num") Integer num);
}
