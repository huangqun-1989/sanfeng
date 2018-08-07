package lock;

public class TestSynchronize {


    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("=====A. 获取到锁======");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("========= test A. 是否需要再次获取锁 ============");

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("=====B. 获取到锁======");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("========= test B. 是否需要再次获取锁 ============");

                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("=====B1. 获取到锁======");

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("========= test B.1 是否需要再次获取锁 ============");

                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("=====B2. 获取到锁======");

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("========= test B2. 是否需要再次获取锁 ============");

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("=====B3. 获取到锁======");

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("========= test B3. 是否需要再次获取锁 ============");

                    try {
                        Thread.sleep(25000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            lock.notifyAll();
        }

    }


}
