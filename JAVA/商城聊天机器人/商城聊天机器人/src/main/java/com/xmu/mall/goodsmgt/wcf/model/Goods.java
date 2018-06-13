package com.xmu.mall.goodsmgt.wcf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:23
*/

public class Goods {

	private Long id;

	private String name;

	private String serial_code;//��Ʒ����

	private Double weight;
	
	private Integer pre_sal_number;
	
	private Integer stock_count;

	private String store_id;	 //�̵�id

	private Double mid_user_price;	 //�н�۸�

	private Double market_price;	 //�г��۸�

	private Double real_price;	 //��ʵ�۸�

	private Integer redeem_point; 	 //��Ʒ����

	private Integer category_id;	 //Ʒ��id
	
	private Integer brand_id;		 //Ʒ��id

	private Integer Stand_id;            //���¼�id
	
	private String add_time; 	 //������Ʒʱ��
	
	private String last_modified_time;//����޸���Ʒ��Ϣʱ��
	
	private String description;	 //��Ʒ����

	private String image_path;	 //��ƷͼƬ
	
	private Boolean is_dividable;	 //�Ƿ�����ֵ�

	private String default_express;   //Ĭ�Ͽ��
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerial_code() {
		return serial_code;
	}
	public void setSerial_code(String serial_code) {
		this.serial_code = serial_code;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getPre_sal_number() {
		return pre_sal_number;
	}
	public void setPre_sal_number(Integer pre_sal_number) {
		this.pre_sal_number = pre_sal_number;
	}
	public Integer getStock_count() {
		return stock_count;
	}
	public void setStock_count(Integer stock_count) {
		this.stock_count = stock_count;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getMid_user_price() {
		return mid_user_price;
	}
	public void setMid_user_price(Double mid_user_price) {
		this.mid_user_price = mid_user_price;
	}
	public Double getReal_price() {
		return real_price;
	}
	public void setReal_price(Double real_price) {
		this.real_price = real_price;
	}
	public Integer getRedeem_point() {
		return redeem_point;
	}
	public void setRedeem_point(Integer redeem_point) {
		this.redeem_point = redeem_point;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public Integer getStand_id() {
		return Stand_id;
	}
	public void setStand_id(Integer stand_id) {
		Stand_id = stand_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLast_modified_time() {
		return last_modified_time;
	}
	public void setLast_modified_time(String last_modified_time) {
		this.last_modified_time = last_modified_time;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public Boolean getIs_dividable() {
		return is_dividable;
	}
	public void setIs_dividable(Boolean is_dividable) {
		this.is_dividable = is_dividable;
	}
	public String getDefault_express() {
		return default_express;
	}
	public void setDefault_express(String default_express) {
		this.default_express = default_express;
	}



}
