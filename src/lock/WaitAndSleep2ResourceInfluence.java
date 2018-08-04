package lock;

/**
 * wait 和 sleep操作对cpu资源的影响
 */
public class WaitAndSleep2ResourceInfluence {

    public static void main(String[] args) {

        WaitAndSleep2ResourceInfluence ws2ri = new WaitAndSleep2ResourceInfluence();
        // 0. 没有程序运行时 cpu使用率 1%左右

        // 1. 纯计算cpu使用率达到  90%以上
        ws2ri.testCpuCostByMassCalc();

        // 2. 增加sleep的计算，cpu使用率 1%左右
        ws2ri.testCpuCostByMassCalcWithSleep();

        // 3. 增加wait的计算，cpu使用率 1%左右
        ws2ri.testCpuCostByMassCalcWithWait();

    }



    public void testCpuCostByMassCalc()  {
        // 1w亿次循环、检测cpu使用绿
        for (long i=1; i<1000000000000l; i++) {
            System.out.println("=========" + i + "========");
        }

    }

    public void testCpuCostByMassCalcWithSleep()  {
        // 1w亿次循环、检测cpu使用绿
        for (long i=1; i<1000000000000l; i++) {
            System.out.println("=========" + i + "========");
            sleep2minutes();
        }

    }

    public void testCpuCostByMassCalcWithWait() {
        // 1w亿次循环、检测cpu使用绿
        for (long i=1; i<1000000000000l; i++) {
            System.out.println("=========" + i + "========");
            synchronized (this) {
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sleep2minutes() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }


}
