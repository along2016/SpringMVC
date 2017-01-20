import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by alion on 2017/1/20.
 */
public class Car implements Runnable {
    private static int count = 0;
    private final int id = count++;
    //步数
    private int strides = 0;
    //注意：Random 的一个特点是：相同种子数的Random对象，对应相同次数生成的随机数字是完全相同的
    private static Random rand = new Random(47);
    public static CyclicBarrier barrier;

    public Car(CyclicBarrier b){
        barrier = b;
    }
    public synchronized int getStrides(){
        return strides;
    }

    public void run() {
        try {
            while (!Thread.interrupted()){
                synchronized (this){
                    strides += rand.nextInt(3);//产生0、1或者2
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < strides; i++){
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }
}
