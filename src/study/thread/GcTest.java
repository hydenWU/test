package study.thread;

//-Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
public class GcTest {
    public static void main(String[] args) {
        //实例化LeakRef类对象，使LeakRef类的静态属性bytes初始化
        LeakRef leakRef = new LeakRef();
        //然后将leakRef对象引用置为null，这样下次垃圾回收会把这个引用回收掉
        leakRef = null;


        //继续向堆申请9M内存这里是关键
        byte[] b1 = new byte[1024 * 1000 * 9];


        //这行不会打印，因为上面代码会把内存撑爆，直接OOM了
        System.out.println(b1.length);
    }



}

 class LeakRef {
    // 定义一个静态属性，初始化为10M的字节数组
    // 准确是1024*1024*10，这里采用1000而不是1024原因是预留出一点点防止启动就因内存刚刚好导致超出
    public static byte[] bytes = new byte[1024 * 1000 * 10];
}