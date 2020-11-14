package threadcoreknowledge.sixstates;

/**
 * @Description 展示线程的NEW、RUNNABLE、TERMINATED状态，即使是正在运行，也是Runnable状态，而不是Running
 * @Date 2020/11/14 4:07 下午
 * @Created by chenzhibin
 */
public class NewRunnableterminated implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableterminated());
        //打印出NEW状态
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出RUNNABLE状态，即使是正在运行，也是RUNNABLE，而不是RUNNING
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出TERMINATED
        System.out.println(thread.getState());

    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
