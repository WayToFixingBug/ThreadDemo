package threadcoreknowledge.stopthreads;

/**
 * @Description 最佳实践：catch了InterruptedException之后的优先选择：在方法签名中抛出异常
 * 那么run()就会强制try/catch
 * @Date 2020/11/1 6:30 下午
 * @Created by WayToFixingBug
 */
public class RightWayStopThreadInprod implements Runnable {
    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("业务逻辑");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("响应中断，保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInprod());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
