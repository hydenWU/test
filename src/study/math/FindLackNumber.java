package study.math;

import java.util.ArrayList;
import java.util.List;

//二分搜索
/*
40亿个随机生产的32位整数，求出一个缺失的数：二进制01二分后，从少的一组里找
43亿个随机生产的32位整数，求出重复的一个数：二进制01二分后，从多的一组里找
//内存足够可用位图来解决
 */
public class FindLackNumber {
 
	private static int bit = 4;// 总共的位数，int 32位,这里为了方便举例，数字最大为15只占四位
 
	private static Integer[] temp;
	private static List<Integer> zero;// 存放0
	private static List<Integer> one;// 存放1
	private static int number;
 
	// 找到数组a中缺少的整数
	public static int find(Integer[] a) {
		temp = a;
		zero = new ArrayList<>();
		one = new ArrayList<>();
 
		while (bit-- > 0) {
			zero.clear();
			one.clear();
			for (int i = 0; i < temp.length; i++) {
				// 高位为1
				if ((temp[i] & (1 << bit)) != 0) {
					one.add(temp[i]);
				} else {
					zero.add(temp[i]);
				}
			}
 
			if (zero.size() < one.size()) {
				temp = (Integer[]) zero.toArray(new Integer[zero.size()]);
			} else {
				temp = (Integer[]) one.toArray(new Integer[one.size()]);
				number |= 1 << bit;
			}
		}
		return number;
 
	}
 
	public static void main(String[] args) {
		Integer[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15 };
		System.out.println(find(a));
 
	}
 
}