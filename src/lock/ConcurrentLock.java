package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLock {

    static ReentrantLock lock = new ReentrantLock();

    static Condition isFull = lock.newCondition();
    static Condition isFree = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("======= await lock ok=======");
                    isFull.await();
                    System.out.println("======== await end =========");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    lock.unlock();
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                System.out.println("========== signal lock ok ========");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isFull.signal();
                System.out.println("========= signal ok ==========");

                lock.unlock();
            }
        }).start();
    }

}
