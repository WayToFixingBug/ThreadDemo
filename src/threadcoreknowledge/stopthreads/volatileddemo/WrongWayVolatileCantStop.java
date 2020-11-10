package threadcoreknowledge.stopthreads.volatileddemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description 陷入阻塞时，volatile是无法停止线程的
 * 此例中，生产者的生产速度很快，消费者的消费速度很慢
 * @Date 2020/11/10 8:29 下午
 * @Created by WayToFixingBug
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(5);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        try {
            Thread.sleep(1000);
            Consumer consumer = new Consumer(storage);
            while (consumer.needMoreNums()) {
                System.out.println(consumer.storage.take() + "被消费了");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者不需要更多数据了");
        //一旦消费者不需要数据了，就让生产者停下来
        producer.canceled = true;
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
                while (num <= 100000 && !canceled) {
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
