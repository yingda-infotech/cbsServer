package cn.com.git.cbs.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 使用jdk8的java time包进行处理
 * 
 * @author xia
 *
 */
public class DateUtils {

	/**
	 * 日期加天之后获取新的日期
	 * 
	 * @param date
	 *            操作的日期 "2017-02-08"
	 * @param days
	 *            增加的天数 300
	 * @return "2017-12-05"
	 */
	public static String addDays(String date, int days) {
		LocalDate date1 = LocalDate.parse(date);
		LocalDate date2 = date1.plusDays(days);
		return date2.toString();
	}

	/**
	 * 日期减去天之后获取新的日期
	 * 
	 * @param date
	 *            操作的日期 "2017-02-08"
	 * @param days
	 *            增加的天数 300
	 * @return "2016-04-14"
	 */
	public static String subDays(String date, int days) {
		LocalDate date1 = LocalDate.parse(date);
		LocalDate date2 = date1.minusDays(days);
		return date2.toString();
	}

	/**
	 * 日期加年之后获取新的日期
	 * 
	 * @param date
	 *            操作的日期 "2017-02-08"
	 * @param years
	 *            增加的年数 1
	 * @return "2018-02-08"
	 */
	public static String addYears(String date, int years) {
		LocalDate date1 = LocalDate.parse(date);
		LocalDate date2 = date1.plusYears(years);
		return date2.toString();
	}

	/**
	 * 计算两个日期之间间隔的天数 后面的参数减去前面的参数，如果前面的日期大，需要返回负数天数
	 * 
	 * @param str1
	 *            日期
	 * @param str2
	 *            日期
	 * @return 日期比较的结果：&gt;0，str1在str2之前；=0，str1与str2相等；&lt;0，str1在str2之后。
	 */
	public static int dateDiff(String str1, String str2) {
		LocalDate date1 = LocalDate.parse(str1);
		LocalDate date2 = LocalDate.parse(str2);
		return (int) ChronoUnit.DAYS.between(date1, date2);
	}

	public static String getGmtString() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}

}
