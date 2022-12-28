package study.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//模拟3个线程执行3个任务，时间不等；总共10轮，每轮都按照线程123顺序依次执行
//此例子也可以用多个condition做
public class ConditionTest {
    final static ReentrantLock lock = new ReentrantLock();
    final static Condition condition = lock.newCondition();
    static volatile int flag = 1;
    public static void main(String[] args) {
        new  Thread(new Runnable(){
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    try {
                        lock.tryLock();
                        while (flag != 3) {
                            condition.await();
                        }
                        System.out.println("thread 3 doing task");

                        flag = 1;
                        condition.signalAll();
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();

        new  Thread(new Runnable(){
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    try {
                        lock.tryLock();
                        while (flag != 1) {
                            condition.await();
                        }
                        System.out.println("thread 1 doing task");

                        flag = 2;
                        condition.signalAll();
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();

        new  Thread(new Runnable(){
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    try {
                        lock.tryLock();
                        while (flag != 2) {
                            condition.await();
                        }
                        System.out.println("thread 2 doing task");

                        flag = 3;
                        condition.signalAll();
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();



    }
}
