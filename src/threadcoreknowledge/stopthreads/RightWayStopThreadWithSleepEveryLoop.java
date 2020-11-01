package threadcoreknowledge.stopthreads;

/**
 * @Description 如果在执行过程中，每次循环都会调用sleep和wait方法，那么不需要每次迭代都检查是否已中断
 * @Date 2020/11/1 2:45 下午
 * @Created by WayToFixingBug
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
