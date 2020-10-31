package threadcoreknowledge.createthreads.wrongways;

/**
 * @Description Lambda表达式创建线程
 * @Date 2020/10/31 3:47 下午
 * @Created by WayToFixingBug
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
