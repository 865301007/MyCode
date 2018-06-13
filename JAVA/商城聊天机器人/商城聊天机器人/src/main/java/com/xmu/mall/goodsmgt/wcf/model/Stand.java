package com.xmu.mall.goodsmgt.wcf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:27
*/
@Entity
@Table(name="142492_stand")
public class Stand {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;		 //���¼�id
	@Column
	private Long goods_id;		 //��Ʒid
	@Column
	private Double mid_user_price;	 //�н�۸�
	@Column
	private Double market_price;	 //�г��۸�
	@Column
	private Double real_price;	 //��ʵ�۸�
	@Column
	private String on_market_time;	 //�ϼ�ʱ��
	@Column
	private String off_market_time;	 //�¼�ʱ��
	@Column
	private Integer pre_sale_count;	 //Ԥ������
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	public Double getMid_user_price() {
		return mid_user_price;
	}
	public void setMid_user_price(Double mid_user_price) {
		this.mid_user_price = mid_user_price;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getReal_price() {
		return real_price;
	}
	public void setReal_price(Double real_price) {
		this.real_price = real_price;
	}
	public String getOn_market_time() {
		return on_market_time;
	}
	public void setOn_market_time(String on_market_time) {
		this.on_market_time = on_market_time;
	}
	public String getOff_market_time() {
		return off_market_time;
	}
	public void setOff_market_time(String off_market_time) {
		this.off_market_time = off_market_time;
	}
	public Integer getPre_sale_count() {
		return pre_sale_count;
	}
	public void setPre_sale_count(Integer pre_sale_count) {
		this.pre_sale_count = pre_sale_count;
	}
}
