package cn.itcast.bankqueue;

public class Constants {
	public static int MAX_SERVICE_TIME = 10000; //10秒！
	public static int MIN_SERVICE_TIME = 1000; //1秒！
	
	/*每个普通窗口服务一个客户的平均时间为5秒，一共有4个这样的窗口，也就是说银行的所有普通窗口合起来
	 * 平均1.25秒内可以服务完一个普通客户，再加上快速窗口和VIP窗口也可以服务普通客户，所以，
	 * 1秒钟产生一个普通客户比较合理，*/	
	public static int COMMON_CUSTOMER_INTERVAL_TIME = 1; 	
}
