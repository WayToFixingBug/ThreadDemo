package threadcoreknowledge.threadobjectclasscomonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * @Description 用wait/notify实现生产者消费者
 * @Date 2020/11/15 1:54 下午
 * @Created by WayToFixingBug
 */
public class ProduceronsumerModel {

    static class Producer implements Runnable {

        private EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.put();
            }
        }
    }

    static class Consumer implements Runnable {

        private EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.take();
            }
        }
    }

    static class EventStorage {
        private int maxSize;
        private LinkedList<Date> storage;

        public EventStorage() {
            this.maxSize = 10;
            this.storage = new LinkedList<>();
        }

        public synchronized void put() {
            while (storage.size() == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(new Date());
            System.out.println("仓库里有了" + storage.size() + "个产品");
            notify();
        }

        public synchronized void take() {
            while (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了" + storage.poll() + "，现在仓库还剩下" + storage.size());
            notify();
        }

    }

    public static void main(String[] args) {
        EventStorage eventStorage =new EventStorage();
        Producer producer=new Producer(eventStorage);
        Consumer consumer=new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
