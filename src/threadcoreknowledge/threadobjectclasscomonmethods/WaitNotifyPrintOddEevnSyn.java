package threadcoreknowledge.threadobjectclasscomonmethods;

/**
 * @Description 用两个线程交替打印1～100，一个线程打印偶数，一个线程打印奇数（用Synchronized实现）
 * @Date 2020/11/15 3:05 下午
 * @Created by chenzhibin
 */
public class WaitNotifyPrintOddEevnSyn {

    private static int count;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "奇数").start();
    }
}
