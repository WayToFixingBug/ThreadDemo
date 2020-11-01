package threadcoreknowledge.stopthreads;

/**
 * @Description 带有sleep的中断线程的方法
 * @Date 2020/11/1 2:34 下午
 * @Created by WayToFixingBug
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                 Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(500);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
