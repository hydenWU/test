package study.thread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//因式分解，并发请求某数的因式分解，对算过的或正在算的，可以直接利用其结果。
public class FutureTest {
    private static ConcurrentHashMap<Integer, FutureTask<String>> resultMap = new ConcurrentHashMap();
    private static final CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<20;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+":await");
                        latch.await();
                        System.out.println(Thread.currentThread().getName()+":result:"+ getResult(100));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(2);
        latch.countDown();
    }

    public static String getResult(Integer num) throws ExecutionException, InterruptedException {
        FutureTask<String> task = resultMap.get(num);
        if(task==null) {
            System.out.println(Thread.currentThread().getName()+":first goin...");
            FutureTask<String> tasknew = new FutureTask<String>(new Callable(){
                @Override
                public Object call() throws Exception {
                    //模拟一个耗时任务
                    int t = new Random().nextInt(5);
                    System.out.println(Thread.currentThread().getName()+":runing with "+ t);
                    TimeUnit.SECONDS.sleep(t);
                    return num+"= X * Y";
                }
            });

            task =  resultMap.putIfAbsent(num,tasknew);//无则放入并返回null，有则返回旧值
            if(task == null){
                task= tasknew;
                task.run();
            }
        }

        return task.get();
    }
}
