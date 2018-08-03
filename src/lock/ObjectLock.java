package lock;


/**
 * object as lock <br>
 *
 *
 * object作为锁、wait/notify/notifyAll/synchronized 关键字一定要配合使用<br>
 *
 *
 *
 *
 * wait和sleep的区别<br>
 * 1. sleep不用配合synchronized一起使用
 * 2. sleep并不释放锁
 */
public class ObjectLock {


    public static void main(String[] args) {
        System.out.println("=====");
    }


    public static void test() {

        Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " wait 结束");
                }
            }
        }).start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.notify();
    }


}
