package threadcoreknowledge.createthreads;

/**
 * @Description 同时使用Runnable和Thread两种实现线程
 * @Date 2020/10/31 2:37 下午
 * @Created by WayToFixingBug
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
