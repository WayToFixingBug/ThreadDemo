package threadcoreknowledge.createthreads.wrongways;

/**
 * @Description 匿名内部类创建线程
 * @Date 2020/10/31 3:45 下午
 * @Created by WayToFixingBug
 */
public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
