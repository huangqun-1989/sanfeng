package lock;

import java.util.concurrent.TimeUnit;

/**
 * @author 无刀流
 */
public class ConcurrentPlainAtomicIncrease {

    // 共享变量
    public static int count = 0;

    // synchronized 关键字修饰的普通方法
    public synchronized void countAtomicIncrease() {
        // method before 获取this当前实例对象的锁，相当于执行 this.lock();
        count++;
        // method after 执行完释放锁，相当于执行 this.unlock();
    }

    public static void main(String[] args) {

        ConcurrentPlainAtomicIncrease lock = new ConcurrentPlainAtomicIncrease();

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

                        // 注意下面这种方式不能实现控制并发访问，每次都是不同的实例，获取的锁也是不同的锁
                        // new ConcurrentPlainAtomicIncrease().countAtomicIncrease();

                        lock.countAtomicIncrease();
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
