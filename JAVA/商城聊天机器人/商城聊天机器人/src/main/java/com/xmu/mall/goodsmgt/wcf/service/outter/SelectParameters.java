package com.xmu.mall.goodsmgt.wcf.service.outter;

import com.xmu.mall.goodsmgt.wcf.model.Goods;


/**
*@author created by �����
*@date 2017��5��31��--����1:45:41
*/
public class SelectParameters extends Goods{

	private static final long serialVersionUID = 2680052380956279242L;
	private int no;
	private int size;

	public void initialPage(int no, int size) {
		setNo(no);
		setSize(size);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}



}
