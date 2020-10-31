package threadcoreknowledge.createthreads;

/**
 * @Description 用Runnable方式创建线程
 * @Date 2020/10/31 2:15 下午
 * @Created by WayToFixingBug
 */
public class RunnableStyle implements Runnable {
    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

}
