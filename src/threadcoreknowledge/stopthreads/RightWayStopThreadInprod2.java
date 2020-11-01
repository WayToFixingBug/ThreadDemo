package threadcoreknowledge.stopthreads;

/**
 * @Description 最佳实践2：在catch子语句中调用Thread.currentThread().interrupt()来恢复设置中断状态，
 * 以便于在后续的执行中，依然能够检查到刚才发生了中断
 * <p>
 * 回到RightWayStopThreadInprod补上中断，让它跳出
 * @Date 2020/11/1 8:00 下午
 * @Created by WayToFixingBug
 */
public class RightWayStopThreadInprod2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted 程序运行结束");
                break;
            }
            reInterruptMethod();
        }
    }

    public void reInterruptMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInprod2());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
