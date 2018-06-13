package com.xmu.mall.goodsindex.zjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmu.mall.goodsindex.zjh.model.ShoppingCart;

public interface ShoppingCartMapper
{
	/**
	 * 通过用户id查找购物车
	 * @param id
	 * @return
	 */
	List<ShoppingCart> getShoppingCartListByUserId(Long id);
	
	/**
	 * 通过商品id和用户id查找购物车
	 * 用于确定某个商品是否被添加到购物车
	 * @param shoppingCart
	 * @return
	 */
	ShoppingCart selectCartByGoodsIdAndUserId(ShoppingCart shoppingCart);
	
	/**
	 * 添加购物车
	 * @param shopppingCart
	 * @return
	 */
	int add(ShoppingCart shopppingCart);
	
	/**
	 * 增加购物车商品数量
	 * 当商品被加入购物车时调用此函数
	 * @param shoppingCart
	 * @return
	 */
	int addGoodsNumber(ShoppingCart shoppingCart);
	
	/**
	 * 更新购物车
	 * @param shoppingCart
	 * @return
	 */
	int update(ShoppingCart shoppingCart);
	
	/**
	 * 删除购物车
	 * @param shoppingCart
	 * @return
	 */
	int delete(ShoppingCart shoppingCart);
	
	/**
	 * 删除购物车中已付款商品
	 * @param shoppingCart
	 * @return
	 */
	int deleteByUserIdAndGoodsId(@Param("user_id")long user_id, @Param("goods_id") long goods_id);
}
