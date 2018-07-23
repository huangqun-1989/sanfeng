package heartbeat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 用传统的 Timer 和 TimerTask来实现心跳线程
 */
public class TimerAndTimerTaskHeartBeat {

    public static void main(String[] args) {

        heartBeatSchedule();
    }


    /**
     * 固定时间执行调度
     */
    public static void fixTimeSchedule() {

        Timer timer = new Timer();

        DateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse("2018-07-23 17:45:17");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        timer.schedule(new TimerTask() {


            @Override
            public void run() {
                Date now = new Date();
                System.out.println("========= 心跳线程启动 ========== " + new Date());
            }
        }, date);

    }


    /**
     * 间隔一定时间来调度
     */
    public static void heartBeatSchedule() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {


            @Override
            public void run() {
                Date now = new Date();
                System.out.println("========= 心跳线程启动 ========== " + now);


                // sleep 这里有学问， 因为是单线程调度，如果任务没执行完，等待任务执行完再调度

                int rand = new Random().nextInt(10);
                System.out.println("=========rand======  " + rand);
                if(rand < 6) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, 0, 1000);

    }


}
