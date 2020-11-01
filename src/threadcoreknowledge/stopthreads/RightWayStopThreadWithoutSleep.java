package threadcoreknowledge.stopthreads;

/**
 * @Description run方法内没用sleep或wait方法时，停止线程
 * @Date 2020/11/1 1:52 下午
 * @Created by WayToFixingBug
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2){
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
