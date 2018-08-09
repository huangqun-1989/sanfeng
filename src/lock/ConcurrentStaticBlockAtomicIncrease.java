package lock;

import java.util.concurrent.TimeUnit;

/**
 * @author 无刀流
 */
public class ConcurrentStaticBlockAtomicIncrease {

    // 共享变量
    public static int count = 0;

    public static void main(String[] args) {

        Object lock = new Object();
        for (int i=0; i<1000; i++) {
            new Thread(new Runnable() {
                @Override
                    public void run() {

                        // 所有子线程休息 10s 后执行，增加并发执行的概率
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 获取实例对象锁来实现
                        synchronized (lock) {
                            // 获取lock对象锁， 相当于执行lock.lock
                            count++;
                            // 代码块执行完， 释放对象锁， 相当于执行lock.unlock
                        }
                    }
                }
            ).start();
        }

        // 主线程休息 30s 保证所有自线程执行完
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行1000次并发自增操作， count = " + count);
    }
}
