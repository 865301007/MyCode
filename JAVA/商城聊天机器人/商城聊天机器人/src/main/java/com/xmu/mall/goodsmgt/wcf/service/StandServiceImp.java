package com.xmu.mall.goodsmgt.wcf.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmu.mall.goodsmgt.wcf.mapper.GoodsMapper;
import com.xmu.mall.goodsmgt.wcf.mapper.StandMapper;
import com.xmu.mall.goodsmgt.wcf.model.Goods;
import com.xmu.mall.goodsmgt.wcf.model.Stand;


/**
*@author created by �����
*@date 2017��5��31��--����1:45:19
*/
@Transactional
@Service("wcf_StandService")
public class StandServiceImp implements StandService{

	@Autowired
	private StandMapper standMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	public List<Stand> getStandList() {
		// TODO Auto-generated method stub
		return this.standMapper.getStandList();
	}

	public boolean addStand(Stand stand) {
		// TODO Auto-generated method stub
		Goods goods=this.goodsMapper.getGoodsById(stand.getGoods_id());
		stand.setMarket_price(goods.getMarket_price());
		stand.setMid_user_price(goods.getMid_user_price());
		stand.setReal_price(goods.getReal_price());
		return this.standMapper.addStand(stand);
	}

	public boolean deleteStandById(long id) {
		// TODO Auto-generated method stub
		return this.standMapper.deleteStandById(id);
	}

	public Stand getStandById(long id) {
		// TODO Auto-generated method stub
		return this.standMapper.getStandById(id);
	}

	public boolean updateStand(Stand stand) {
		// TODO Auto-generated method stub
		Goods goods=this.goodsMapper.getGoodsById(stand.getGoods_id());
		stand.setMarket_price(goods.getMarket_price());
		stand.setMid_user_price(goods.getMid_user_price());
		stand.setReal_price(goods.getReal_price());
		return this.standMapper.updateStand(stand);
	}

}
