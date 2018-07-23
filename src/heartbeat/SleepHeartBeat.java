package heartbeat;

import java.util.Date;


/**
 * 死循了 + sleep固定时间来实现心跳线程
 */
public class SleepHeartBeat {

    // sleep interval

    // 死循环 + sleep固定时间
    public static void main(String[] args) {

        while (true) {

            try {
                Date now = new Date();
                System.out.println("============ 心跳起来 ============ " + now);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
