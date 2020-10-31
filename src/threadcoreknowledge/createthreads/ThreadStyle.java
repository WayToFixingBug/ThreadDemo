package threadcoreknowledge.createthreads;

/**
 * @Description 用Thread方式实现线程
 * @Date 2020/10/31 2:18 下午
 * @Created by WayToFixingBug
 */
public class ThreadStyle extends Thread {
    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }
}
