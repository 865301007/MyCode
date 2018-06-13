package com.xmu.mall.orderindex.tangkai.model;

/**
 * @author tangkai
 * @data 2017年6月2日--下午4:17:54
 */
public class Order {
 
	private long order_id; //订单ID
	private long user_id;  //用户ID
	private String order_sn;
	private int order_status;    //订单状态（0：未付款；1：已付款；2：定制中；3：已发货；4：已收货；5：取消）
	private String consignee;    //收货人
	private String address;      //地址
	private String zipcode;     //邮编
	private String telephone;   //电话
	private Double order_price; //订单总价
	private String remark;      //备注
	private Double total_weight; //订单总重
	private int add_time;       //下单时间（用int类型来保存时间戳）
	private int shipped_time;	//发货时间
	private String expressCode; //快递单号
	private String isUrgency;   //是否加急  （可以用Y/N来表示）
	private int make_time;		//订单变为定制时间
	private int pay_time;
	
	
	public int getPay_time() {
		return pay_time;
	}
	public void setPay_time(int pay_time) {
		this.pay_time = pay_time;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public int getMake_time() {
		return make_time;
	}
	public void setMake_time(int make_time) {
		this.make_time = make_time;
	}
	public int getShipped_time() {
		return shipped_time;
	}
	public void setShipped_time(int shipped_time) {
		this.shipped_time = shipped_time;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getIsUrgency() {
		return isUrgency;
	}
	public void setIsUrgency(String isUrgency) {
		this.isUrgency = isUrgency;
	}
	
	
	
}
