package threadcoreknowledge.threadobjectclasscomonmethods;

/**
 * @Description 展示wait和notify的基本用法
 * 1.研究代码执行顺序
 * 2.验证wait释放锁
 * @Date 2020/11/15 10:48 上午
 * @Created by WayToFixingBug
 */
public class Wait {
    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1=new Thread1();
        Thread2 thread2=new Thread2();
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

    }
}
