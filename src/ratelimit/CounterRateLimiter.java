package ratelimit;

/**
 * 计数器限流算法
 */
public class CounterRateLimiter {



    // 注意：生产环境变量要用atomic并发包里面的类，这样才可以真正防止并发问题
    // 单位毫秒
    private static long beforeTime = System.currentTimeMillis();

    private static long interval = 60 * 1000;

    private static long count = 0;

    private static long RATE_COUNT = 100;

    public static boolean isLimited() {

        long now = System.currentTimeMillis();

        if ((now - beforeTime) < interval) {

            count++;

            if (count > RATE_COUNT) {
                return true;
            } else {
                return  false;
            }

        } else {

            beforeTime = System.currentTimeMillis();
            count = 0;

        }

        return false;
    }


    public static void main(String[] args) throws InterruptedException {

        // 模拟计数器限流被攻击情况


        for (int i=1; i<2; i++) {

            if (isLimited()) {
                System.out.println("第 " + i + " 个请求被限制");
            } else {
                System.out.println("================第 " + i + " 个请求可以通过");
            }
        }


        Thread.sleep(1000*59);


        for (int i=1; i<100; i++) {

            if (isLimited()) {
                System.out.println("第 " + i + " 个请求被限制");
            } else {
                System.out.println("================第 " + i + " 个请求可以通过");
            }
        }


        Thread.sleep(1000*1);

        for (int i=1; i<100; i++) {

            if (isLimited()) {
                System.out.println("第 " + i + " 个请求被限制");
            } else {
                System.out.println("================第 " + i + " 个请求可以通过");
            }
        }

    }

}
