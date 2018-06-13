package com.xmu.mall.goodsmgt.wcf.service.outter;

import java.util.List;

import com.xmu.mall.goodsmgt.wcf.model.Brand;
import com.xmu.mall.goodsmgt.wcf.model.Category;
import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.model.Stand;


/**
*@author created by �����
*@date 2017��5��31��--����1:45:28
*/
public interface IGoodsMapper {
	
	public Goods getGoodsByGoodsCode(String serial_code);
	public List<Goods> getGoodsByGoodsName(String goods_name);
	public List<Goods> getGoodsBy(SelectParameters selectParameters);
	public List<Brand> getBrandList();  
	public List<Category> getCategoryList(); 
	public Stand getGoodsPrice(Long goods_id) ;
	public Boolean deleteGoodsNumById(String goodsId, int num);
	public Object getGoodsByGoodsId(Long id);
	public List<Goods> getGoodsList();
}
