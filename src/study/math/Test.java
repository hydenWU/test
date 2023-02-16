package study.math;

import java.util.BitSet;

/*
1、递归与分治:a.要求原始问题可以分解成相同问题的子问题,示例：阶乘、斐波纳契数列,F1=1,F2=1,Fn=F（n-1）+F（n-2）
b.待解决复杂的问题能够简化为几个若干个小规模相同的问题，然后逐步划分，达到易于解决的程度:求最值
2、动态规划
3、贪心算法
4、回溯法
5、 分支限界法
 */
public class Test {
    public static void main(String[] args) {
        BitSet bs;
        System.out.println( fbnqsl(8));
    }

    public static int fbnqsl(int n) {
        if(n==1)return 1;
        if(n==2)return 1;
        int k = fbnqsl(n-1) + fbnqsl(n-2);
        return k;
    }
}
