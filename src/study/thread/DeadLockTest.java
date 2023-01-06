package study.thread;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

//死锁解决：用相同的请求锁的顺序，不要构成循环。
//如此例子：都先请求string，再请求integer接口
public class DeadLockTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void task1() throws InterruptedException {
        synchronized (String.class) {
            System.out.println("task1请求到String");
            TimeUnit.SECONDS.sleep(1);
            synchronized (Integer.class) {
                System.out.println("task1请求到Integer");
            }
        }
    }

    public static void task2() throws InterruptedException {
            synchronized (Integer.class) {
                System.out.println("task2请求到Integer");
                TimeUnit.SECONDS.sleep(1);
                synchronized (String.class) {
                    System.out.println("task2请求到String");

            }
        }
    }
}
