package threadcoreknowledge.startthread;

/**
 * @Description 对比start和run方法两种启动线程的方式
 * @Date 2020/10/31 4:53 下午
 * @Created by WayToFixingBug
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        runnable.run();
        new Thread(runnable).start();
    }
}
