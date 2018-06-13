package com.xmu.mall.orderindex.tangkai.model;

/**
 * @author tangkai
 *2018年4月2日下午8:27:46
 */
public class OrderReadyPay {
	private long user_id;  //用户ID
	private String order_sn;
	private int order_status;    //订单状态（0：未付款；1：已付款；2：定制中；3：已发货；4：已收货；5：取消）	
	private Double order_price; //订单总价	
	private Double total_weight; //订单总重
	private int add_time;       //下单时间（用int类型来保存时间戳）
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public Double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}
	public Double getTotal_weight() {
		return total_weight;
	}
	public void setTotal_weight(Double total_weight) {
		this.total_weight = total_weight;
	}
	public int getAdd_time() {
		return add_time;
	}
	public void setAdd_time(int add_time) {
		this.add_time = add_time;
	}
	
	
}
