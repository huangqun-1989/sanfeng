package ratelimit;

/**
 * 漏桶限流算法
 */
public class LeakyBucketRateLimiter {

    // 水桶容量
    private static long bucketCapacity = 100;

    // 水流速率
    private static double rate = 0.0001;

    // 当前水位线
    private static long waterLevel = 0;

    private static long beforeTime = System.currentTimeMillis();


    public static boolean isLimited() {

        long nowTime = System.currentTimeMillis();

        // 1秒走一个
        waterLevel = max(0, (long)(waterLevel - ((nowTime - beforeTime) * rate)));

        beforeTime = System.currentTimeMillis();

        if ((waterLevel+1) <= bucketCapacity) {
            waterLevel++;
            return false;
        } else {
            return true;
        }


    }


    private static long max(long a, long b) {

        return a > b ? a : b;
    }



    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<200; i++) {
            if (isLimited()) {
                System.out.println("第 " + i + " 个请求被限制");
            } else {
                System.out.println("================第 " + i + " 个请求可以通过");
            }
        }

        Thread.sleep(1000);

        for (int i=0; i<100000000; i++) {
            if (isLimited()) {
                System.out.println("第 " + i + " 个请求被限制");
            } else {
                System.out.println("================第 " + i + " 个请求可以通过");
            }

        }

    }


}
