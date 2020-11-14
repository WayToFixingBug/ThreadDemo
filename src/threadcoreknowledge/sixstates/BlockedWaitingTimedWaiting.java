package threadcoreknowledge.sixstates;

/**
 * @Description 展示Blcoked、Waiting、TimedWaiting
 * @Date 2020/11/14 4:26 下午
 * @Created by chenzhibin
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        //打印出TIME_WAITING状态，因为正在执行Thread.sleep(1000)
        System.out.println(thread1.getState());
        //打印出BLOCKED状态，因为thread2想拿到syn()的锁却拿不到
        System.out.println(thread2.getState());

        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出WAITING状态
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
