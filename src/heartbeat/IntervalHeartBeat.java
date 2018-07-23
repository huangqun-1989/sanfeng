package heartbeat;

import java.util.Date;

/**
 * 死循环 + 检测固定时间间隔来实现心跳线程
 */
public class IntervalHeartBeat {

    private static long initTime = System.currentTimeMillis();

    // 单位毫秒
    private static long interval = 1000;

    // now - beforeTime = interval

    /**
     * 死循环 + 检测固定时间间隔
     * @param args
     */
    public static void main(String[] args) {


        while (true) {

            long now = System.currentTimeMillis();
            if(now - initTime == interval) {
                Date date = new Date();
                System.out.println("========= 心跳起来 ================= " + date);
                initTime = System.currentTimeMillis();

            }
        }
    }

}
