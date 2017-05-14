package com.wcf.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Expression {
	//�����ֵ䣺��������һ����
	protected static Map<String,Integer> table=new LinkedHashMap<String,Integer>();
	
	public Expression()
	{
		Expression.table.put("һ", 1);
		Expression.table.put("��", 2);
		Expression.table.put("��", 3);
		Expression.table.put("��", 4);
		Expression.table.put("��", 5);
		Expression.table.put("��", 6);
		Expression.table.put("��", 7);
		Expression.table.put("��", 8);
		Expression.table.put("��", 9);
		
	}
	protected boolean stringEndsWith(String src,String tail)
	{
		if(src.length()<tail.length())
			return false;
		int end=src.lastIndexOf(tail);
		System.out.println(src+"    "+tail);

		return end==(src.length()-tail.length());
	}
	public  void interpret(Context context)
	{
		if(context.getStatement().length()==0)
			return;
		
		for(String k:table.keySet())
		{
			String statement=context.getStatement();
			String tail=k+getPostfix();
			
			if(this.stringEndsWith(statement, tail))
			{
				context.setData(context.getData()+table.get(k)*this.muliplier());
				context.setStatement(statement.substring(0, statement.length()-2));
			}
			if(this.stringEndsWith(statement, "��"))
			{
				context.setStatement(statement.substring(0,statement.length()-2));
			}
		}
		
	}

	
	public abstract String getPostfix();
	public abstract int muliplier();

}
