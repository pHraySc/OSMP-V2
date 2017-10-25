package com.asiainfo.omp.test;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OmpTest {

	public  void main1(String[] args) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = new GregorianCalendar(2007, 11, 25,0,0,0);   
			Date date = calendar.getTime();   
			System.out.println("2007 Christmas is:"+sdf.format(date));  
			/** 设置当前日期 **/
			calendar.setTime(new Date());
			int end_day=calendar.get(Calendar.DAY_OF_MONTH);
			int end_hours=calendar.get(Calendar.HOUR_OF_DAY);
			String start_time = sdf.format(new Date());
			System.out.println("----------------"+date+"--"+end_hours+"------------------"+start_time);
	}
    public static void main(String[]  args){
    	NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(1);
    	long a=123;
    	long b=100;
    	double c;
    	nf.format((b-a)/a);
    	c=(b-a)/(double)a;
    	System.out.println(nf.format(b));
    	System.out.println(nf.format(a));
    	System.out.println(nf.format(b-a));
    	System.out.println(nf.format(-23.0000/123.0000));
    	System.out.println(nf.format((b-a)/a));
    	System.out.println((b-a)/a);
    	System.out.println(String.format("%.2f", c*100));
    	System.out.println(Math.round(c*100));
    }
}
