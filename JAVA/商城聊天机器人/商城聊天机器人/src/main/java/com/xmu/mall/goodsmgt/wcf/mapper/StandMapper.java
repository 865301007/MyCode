package com.xmu.mall.goodsmgt.wcf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmu.mall.goodsmgt.wcf.model.Stand;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:07
*/
public interface StandMapper {
	public List<Stand> getStandList();
	public boolean addStand(Stand stand);
	public boolean deleteStandById(long id);
	public Stand getStandById(long id);
	public boolean updateStand(Stand stand);
	public Stand getGoodsPrice(Long goods_id);
	public Boolean deleteGoodsNumById(@Param("goodsId") String goodsId, @Param("num") Integer num);
}
