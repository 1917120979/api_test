package api.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * 
	 * @Title: d2t   
	 * @Description: 将Date类型的日期转换为Timstamp类型 
	 * @param: @param date
	 * @param: @return      
	 * @return: Timestamp      
	 * @throws
	 */
	public static Timestamp d2t(Date date) {
		if(null == date)
			return null;
		return Timestamp.valueOf(format.format(date));
	}
	
	/**
	 * 
	 * @Title: t2d   
	 * @Description: 将Timstamp类型的日期转换为Date类型 
	 * @param: @param time
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static String t2d(Timestamp time) {
		if(null == time)
			return null;
		return time.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(format.parse("2020-04-17 14:58:06"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
