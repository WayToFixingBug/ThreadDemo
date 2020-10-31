package threadcoreknowledge.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description 定时器创建线程
 * @Date 2020/10/31 3:41 下午
 * @Created by WayToFixingBug
 */
public class TimmerTaskDemo {
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },1000,1000);
    }
}
