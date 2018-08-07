package lock;

public class SynchronizedTest {

    // synchronized 静态方法，锁的是类对象，   普通方法，   对象
    public synchronized  static void  test() {
        try {
            System.out.println("============");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        test();
        test();

    }
}
