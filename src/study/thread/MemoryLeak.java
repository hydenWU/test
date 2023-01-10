package study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/*内存泄漏例子：
1.多线程：

 */
public class MemoryLeak {

    public static void main(String[] args)throws Exception  {
        System.out.println(System.getProperties());
        FutureExample();
    }

    public static void FutureExample() throws Exception {

        ArrayBlockingQueue queue = new ArrayBlockingQueue(1);
        ExecutorService pool = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,queue,new MyRejectedExecutionHandler());
        while(true) {//1.模拟定时任务，不断会触发
            TimeUnit.SECONDS.sleep(1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Future<String>> list = new ArrayList<>();
                    for(int i =0;i<10;i++) {//2.模拟业务，查询出数据对每个做处理
                        final int num = i;
                        try {
                            Future<String> f1 = pool.submit(new Callable<String>() {
                                @Override
                                public String call() throws Exception {
                                    System.out.println(this.toString() + ": finished");
                                    return this.toString();
                                }

                                @Override
                                public String toString() {
                                    return Thread.currentThread().getName() + ":task " + num;
                                }
                            });//这里如果是默认抛出异常的拒绝策略，某个task提交失败后则抛出异常无法加入list，后续就不会引起Future.get的阻塞，进而导致该线程无法结束被回收即内存泄漏。
                            list.add(f1);
                        }catch (Exception e) {
                            System.out.println(":has task be reject");
                        }
                    }

                    //取得结果
                    byte[] bigdata = new byte[1024*1024*50];//为了加大内存增加效果
                    for(Future<String> f:list){
                        try {
                            //FutureTask get阻塞后，最终走run方法，或者走异常或者正常结束都会唤醒阻塞的线程。
                            System.out.println(f.get()+":got it");//也可以用超时的get做个保护
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }

    //自定义拒绝策略，为了跟踪日志信息
    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(executor.getThreadFactory().toString() + ":has task be reject");
            //throw new RejectedExecutionException("task reject");//引发问题的关键
        }
    }
}
