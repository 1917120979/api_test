package api.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
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
		return new Timestamp(date.getTime());
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
	public static Date t2d(Timestamp time) {
		if(null == time)
			return null;
		return new Date(time.getTime());
	}
}
