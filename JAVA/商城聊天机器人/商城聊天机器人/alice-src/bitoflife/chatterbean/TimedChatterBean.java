package bitoflife.chatterbean;

import java.util.Date;

/** 
* @ClassName: TimedChatterBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王崇菲
* @date 2018年3月15日 下午12:55:42 
* @version V1.0 
*/
public class TimedChatterBean {
	private Date date;
	private ChatterBean chatterBean;
	
	public TimedChatterBean() {
		// TODO Auto-generated constructor stub
	}
	
	public TimedChatterBean(Date d,ChatterBean c)
	{
		this.date=d;
		this.chatterBean=c;
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	public void setDate(Date date)
	{
		this.date=date;
	}
	
	public  ChatterBean getChatterBean() {
		
		return this.chatterBean;
		
	}
	

}
