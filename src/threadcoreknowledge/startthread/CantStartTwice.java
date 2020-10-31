package threadcoreknowledge.startthread;

/**
 * @Description 不能调用两次start方法，否则会报错
 * @Date 2020/10/31 5:00 下午
 * @Created by WayToFixingBug
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
