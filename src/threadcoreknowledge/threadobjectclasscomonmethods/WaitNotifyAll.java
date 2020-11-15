package threadcoreknowledge.threadobjectclasscomonmethods;

/**
 * @Description 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们
 * @Date 2020/11/15 11:15 上午
 * @Created by WayToFixingBug
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " got resourceA lock");

            try {
                System.out.println(Thread.currentThread().getName() + " waits to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + " waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyAll waitNotifyAll = new WaitNotifyAll();
        Thread threadA = new Thread(waitNotifyAll);
        Thread threadB = new Thread(waitNotifyAll);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
                    System.out.println("ThreadC notified");
                }
            }
        });
        threadA.setName("threadA");
        threadB.setName("threadB");
        threadA.start();
        threadB.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadC.start();
    }
}
