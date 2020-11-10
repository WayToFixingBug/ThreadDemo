package threadcoreknowledge.stopthreads.volatileddemo;

/**
 * @Description volatile的局限
 * @Date 2020/11/10 8:19 下午
 * @Created by WayToFixingBug
 */
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num %100==0){
                    System.out.println(num+"是一百的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WrongWayVolatile wrongWayVolatile =new WrongWayVolatile();
        Thread thread =new Thread(wrongWayVolatile);
        thread.start();
        try {
            Thread.sleep(5000);
            wrongWayVolatile.canceled=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
