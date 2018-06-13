package com.xmu.mall.goodsmgt.wcf.model;


/**
*@author created by �����
*@date 2017��5��31��--����1:44:14
*/

public class Brand {


	private Long id;		 //Ʒ��id

	private String name;		 //Ʒ������

	private String website;		 //Ʒ����ַ
	
	private String description;	 //Ʒ������
	
	private Integer type;		 //Ʒ��״̬��Ŀǰ��ʾ�Ƿ���ǰ̨��ʾ��
	
	public Brand()
	{
		
	}
	
	public Brand(String name,String websitr,String description,Integer type)
	{
		this.name=name;
		this.website=websitr;
		this.description=description;
		this.type=type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
