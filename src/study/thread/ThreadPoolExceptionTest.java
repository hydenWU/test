package study.thread;

import java.util.concurrent.*;

/*
捕获异常并处理，如何捕获异常？
对于execute：重写afterExecute、自定义线程工厂并让线程setDefaultUncaughtExceptionHandler
对于submit：重写afterExecute、future.get
 */
public class ThreadPoolExceptionTest {
    public static void main(String[] args) {
         ExcuteTest();
        //SubmitTest();
    }

    public static void ExcuteTest() {
        //方法1：
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        //此处打印日志
                        System.out.println("方法1：捕获异常");
                        System.out.println(e);
                    }
                });
                return t;
            }
        });
        //方法2：
        pool = new ThreadPoolExecutor(2,2,0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10)
         ){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("方法2：捕获异常");
                System.out.println(t);
                //super.afterExecute(r, t);
            }
        };

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行task1...");
                int i = 1/0;
            }
        });

        pool.shutdown();
    }

    public static void SubmitTest() {
        //方法1：afterExecute里调用get
        ExecutorService pool = new ThreadPoolExecutor(2,2,0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10)){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("方法2：捕获异常");
                if(r instanceof FutureTask) {
                    try {
                        ((FutureTask)r).get();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        };

        Future f = pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行task2...");
                int i = 1/0;
            }
        });

        //方法2：调用get
//        try {
//            f.get();
//        } catch ( Exception e) {
//            System.out.println(e);
//        }

        pool.shutdown();
    }
}
