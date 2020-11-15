package threadcoreknowledge.threadobjectclasscomonmethods;

/**
 * @Description 用两个线程交替打印1～100，一个线程打印偶数，一个线程打印奇数（用wait/notify）
 * @Date 2020/11/15 4:26 下午
 * @Created by WayToFixingBug
 */
public class WaitNotifyPrintOddEvenWait {
    private static int count = 0;
    private static final Object lock = new Object();

    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if (count<=100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TurningRunner turningRunner=new TurningRunner();
        Thread thread1=new Thread(turningRunner,"偶数");
        Thread thread2=new Thread(turningRunner,"奇数");
        thread1.start();
        thread2.start();
    }

}
