package heartbeat;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 用jdk自带的调度框架来实现心跳线程 <br>
 *
 * 推荐此种方法，调度更精准，毫秒级调度, 稳定的定时器 <br>
 */
public class ScheduleThreadHeartBeat {


    public static void main(String[] args) {


        ExecutorService pool = Executors.newScheduledThreadPool(5);

        ((ScheduledExecutorService) pool).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("======== 心跳线程被调度 ======== " + new Date());
            }
        }, 0, 1, TimeUnit.SECONDS);


    }
}
