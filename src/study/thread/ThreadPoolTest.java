package study.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

//报表生成：sql并行处理，然后整合结果
//线程池+futureTask +返回值，
//ExecutorCompletionService 谁先完成就返回
//CompletableFuture+ ForkJoinPool ForkJoinTask；子类：RecusiveAction和RecusiveTask 任务并行合并
//子线程获取该值的时候为Null，可以用InheritableThreadLocal代替ThreadLocal
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<Future<String>> taskList = new ArrayList<>();

        Callable<String> c = new Callable(){
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return "task1Result";
            }
        };
        taskList.add(threadPool.submit(c));

        Callable<String> c2 = new Callable(){
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "task2Result";
            }
        };
        taskList.add(threadPool.submit(c2));

        for(Future<String> f: taskList){
            System.out.println(f.get());
        }


        threadPool.shutdown();

    }
}
