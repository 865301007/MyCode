package com.wcf;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printable p=new ConcretePrinter();
		p.add(new FullBorder(new Printer()));
		p.add(new FullBorder(new FullBorder(new Printer())));
		p.add(new SideBorder(new FullBorder(new Printer()),'*'));
		p.setContext("Hello world!");
		System.out.println("��̨��ӡ��ͬʱ����!");
		p.print();
		System.out.println("���ѡ��һ̨");
		Printable newp=p.getPrint();
		newp.setContext("test");
		newp.print();
	}

}
