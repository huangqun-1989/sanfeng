package lock;

import java.util.concurrent.TimeUnit;

/**
 * @author 无刀流
 */
public class ConcurrentAtomicIncrease {

    // 共享变量
    public static int count = 0;

    /**
     * synchronized 用法一: 修饰静态方法
     * 本质是 synchronized(ConcurrentAtomicIncrease.class), 通过当前类对象锁来实现控制并发
     */
    public synchronized static void countAtomicIncrease() {

        // method before 进入方法前获取 ConcurrentAtomicIncrease.class的锁
        // 相当于执行 ConcurrentAtomicIncrease.class.lock()

        count++;

        // method after 执行完方法内容后，释放 ConcurrentAtomicIncrease.class的锁
        // 相当于执行 ConcurrentAtomicIncrease.class.unlock()

    }

    public static void main(String[] args) {

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

                        countAtomicIncrease();
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
