package com.xmu.mall.goodsmgt.wcf.service.outter;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmu.mall.goodsmgt.wcf.mapper.BrandMapper;
import com.xmu.mall.goodsmgt.wcf.mapper.CategoryMapper;
import com.xmu.mall.goodsmgt.wcf.mapper.GoodsMapper;
import com.xmu.mall.goodsmgt.wcf.mapper.StandMapper;
import com.xmu.mall.goodsmgt.wcf.model.Brand;
import com.xmu.mall.goodsmgt.wcf.model.Category;
import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.model.Stand;


/**
*@author created by �����
*@date 2017��5��31��--����1:45:36
*/
@Transactional
@Service("wcfIGoodsMapper")
public class IGoodsMapperImp implements IGoodsMapper{


	@Autowired
	
	private GoodsMapper goodsMapper;
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private StandMapper standMapper;
	public IGoodsMapperImp ()
	{
		System.out.println("IGoodsMapperImp");
	}
	public Goods getGoodsByGoodsCode(String serial_code) {
		// TODO Auto-generated method stub
		return this.goodsMapper.getGoodsByGoodsCode(serial_code);
	}

	public List<Goods> getGoodsByGoodsName(String goods_name) {
		// TODO Auto-generated method stub
		return this.goodsMapper.getGoodsByGoodsName(goods_name);
	}

	public List<Brand> getBrandList() {
		// TODO Auto-generated method stub
		return this.brandMapper.getBrandList();
	}

	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return this.categoryMapper.getCategoryList();
	}

	public Stand getGoodsPrice(Long goods_id) {
		// TODO Auto-generated method stub
		return this.standMapper.getGoodsPrice(goods_id);
	}

	public List<Goods> getGoodsBy(SelectParameters selectParameters) {
		// TODO Auto-generated method stub
	
		
		return this.goodsMapper.getGoodsBy(selectParameters);
	}

	public Boolean deleteGoodsNumById(String goodsId,  int num) {
		// TODO Auto-generated method stub
		this.standMapper.deleteGoodsNumById(goodsId, num);
		this.goodsMapper.deleteGoodsNumById(goodsId, num);
		return true;
	}

	@Override
	public Goods getGoodsByGoodsId(Long id) {
		// TODO Auto-generated method stub
		return this.goodsMapper.getGoodsById(id);
	}
	@Override
	public List<Goods> getGoodsList() {
		// TODO Auto-generated method stub
		return this.goodsMapper.getGoodsList();
	}

}
