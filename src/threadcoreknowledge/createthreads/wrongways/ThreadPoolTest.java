package threadcoreknowledge.createthreads.wrongways;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 线程池创建线程的方法
 * @Date 2020/10/31 3:28 下午
 * @Created by WayToFixingBug
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task());
        }
    }
    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

}
