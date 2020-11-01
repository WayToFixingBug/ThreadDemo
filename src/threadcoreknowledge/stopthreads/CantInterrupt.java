package threadcoreknowledge.stopthreads;

/**
 * @Description 如果while里面放try/catch，会导致中断失效
 * @Date 2020/11/1 6:13 下午
 * @Created by WayToFixingBug
 */
public class CantInterrupt {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
