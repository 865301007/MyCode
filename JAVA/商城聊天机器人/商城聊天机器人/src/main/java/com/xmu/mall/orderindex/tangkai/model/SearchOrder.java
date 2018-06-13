package com.xmu.mall.orderindex.tangkai.model;

/**
 * @author tangkai
 * @data 2017年6月2日--下午1:19:18
 */
public class SearchOrder {
	private long order_sn; //订单ID
	private int order_status;    //订单状态（0：未付款；1：已付款；2：定制中；3：已发货；4：已收货；5：取消）
	private String consignee;    //收货人
	private String goods_name;	//商品名称
	private long before_add_time;       //下单时间时间的前范围
	private long after_add_time;			//下单时间的后范围
	private String isUrgency;   //是否加急  （可以用Y/N来表示）
	
	public SearchOrder(long order_sn, int order_status, String consignee, String goods_name, long long_before,
			long long_after, String isUrgency) {
		super();
		this.order_sn = order_sn;
		this.order_status = order_status;
		this.consignee = consignee;
		this.goods_name = goods_name;
		this.before_add_time = long_before;
		this.after_add_time = long_after;
		this.isUrgency = isUrgency;
	}
	public long getorder_sn() {
		return order_sn;
	}
	public void setorder_sn(long order_sn) {
		this.order_sn = order_sn;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public long getBefore_add_time() {
		return before_add_time;
	}
	public void setBefore_add_time(int before_add_time) {
		this.before_add_time = before_add_time;
	}
	public long getAfter_add_time() {
		return after_add_time;
	}
	public void setAfter_add_time(int after_add_time) {
		this.after_add_time = after_add_time;
	}
	public String getIsUrgency() {
		return isUrgency;
	}
	public void setIsUrgency(String isUrgency) {
		this.isUrgency = isUrgency;
	}
	
	
}
