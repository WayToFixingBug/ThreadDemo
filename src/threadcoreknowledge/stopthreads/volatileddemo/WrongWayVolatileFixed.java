package threadcoreknowledge.stopthreads.volatileddemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description 用
 * @Date 2020/11/10 9:11 下午
 * @Created by chenzhibin
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(5);
        WrongWayVolatileCantStop.Producer producer = new WrongWayVolatileCantStop.Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        try {
            Thread.sleep(1000);
            WrongWayVolatileCantStop.Consumer consumer = new WrongWayVolatileCantStop.Consumer(storage);
            while (consumer.needMoreNums()) {
                System.out.println(consumer.storage.take() + "被消费了");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者不需要更多数据了");
        //一旦消费者不需要数据了，就让生产者停下来
        producerThread.interrupt();
        System.out.println(producer.canceled);
    }

    static class Consumer {
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }

    static class Producer implements Runnable {

        public volatile boolean canceled = false;
        private BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000 && !Thread.currentThread().isInterrupted() ) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + "是一百的倍数，被放到仓库了");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者停止运行");
            }
        }
    }
}
