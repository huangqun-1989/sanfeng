package lock;

import java.util.UUID;

/**
 * test wait and sleep diffrence
 */
public class WaitAndSleep {


    /**
     * 方法： 获取对象锁并睡眠sleep  <br>
     *
     * 结论： <br>
     *      1. sleep期间，线程并不会释放它所持有的对象锁   <br>
     *      2. sleeep自己等待时间结束即唤醒   <br>
     *      3. wait需要设置时间后自己唤醒，或者通过notify来唤醒   <br>
     *      4. wait和sleep都不占用cpu资源
     */
    public void acquireObjLockThenSleep() {

        synchronized (this) {
            try {
                String uuid = uuid();
                System.out.println(uuid + " | acquireObjLockThenSleep start >>> 获取了对象锁成功， 开始 sleep 3秒钟 time:" + getTimeStamp());
                Thread.sleep(3000);
                System.out.println(uuid + " | acquireObjLockThenSleep end >>> 获取了对象锁成功， 开始 sleep 3秒钟 time:" + getTimeStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void acquireObjLockThenWait() {
        synchronized (this) {
            try {
                String uuid = uuid();
                System.out.println(uuid + " | acquireObjLockThenWait start >>> 获取了对象锁成功， 开始 wait 3秒钟 time:" + getTimeStamp());
                this.wait(2000);
                System.out.println(uuid + " | acquireObjLockThenWait end >>> 获取了对象锁成功， 开始 wait 3秒钟 time:" + getTimeStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 方法：仅是获取对象锁 <br>
     */
    public void acquireObjectLock() {

        synchronized (this) {
            String uuid = uuid();
            System.out.println(uuid + " | acquireObjectLock >>> 只获取对象锁成功, time:" + getTimeStamp());
        }
    }


    private static String uuid() {
        return UUID.randomUUID().toString();
    }

    private static long getTimeStamp() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
        WaitAndSleep ws = new WaitAndSleep();

        // 不加同步代码块synchjronized wait不能被执行，会抛异常
        synchronized (ws) {
            try {
                ws.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // wait 和 notify 都需要在synchronized代码块中，不然都会抛出异常
        synchronized (ws) {
            ws.notify();
        }

        System.out.println("===================== wait 3s 钟 =========================");

        // 1. 启动一个线程去获取锁再sleep
        for (int i=0; i<3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ws.acquireObjLockThenSleep();
                }
            }).start();
        }

        // 2. 启动一个线程只是获取锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                ws.acquireObjectLock();
            }
        }).start();

        // 3. 启动一个线程去获取锁再wait
        for (int i=0; i<3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ws.acquireObjLockThenWait();
                }
            }).start();
        }


        Object obj = new Object();

        System.out.println("======================= main Thread end =========================");

    }
}
