package lock;

import java.util.concurrent.*;

/**
 * @author 无刀流
 */
public class ConcurrentStaticBlock {

    public static int count = 0;

    public static void main(String[] args) {

        for (int i=0; i<1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    /**
                     * synchronized 用法二: 修饰静态方法
                     * 本质是 synchronized(ConcurrentAtomicIncrease.class), 通过当前类对象锁来实现控制并发
                     */
                    synchronized (ConcurrentStaticBlock.class) {
                        // 先获取 ConcurrentStaticBlock.class 类对象内置锁
                        // 相当于执行 ConcurrentStaticBlock.class.lock()
                        count++;
                        // 代码块执行完，释放锁，相当于执行 ConcurrentStaticBlock.class.unlock()
                    }
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count = " + count);
    }
}
