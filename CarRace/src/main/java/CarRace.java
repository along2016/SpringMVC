import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by alion on 2017/1/20.
 */
public class CarRace {

    static final int FINISH_LINE = 75;
    private List<Car> cars = new ArrayList<Car>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public CarRace(int nCars, final int pause){
        barrier = new CyclicBarrier(nCars, new Runnable() {
            public void run() {
                StringBuilder s = new StringBuilder();
                for(int i = 0; i < FINISH_LINE; i++){
                    s.append("=");
                }
                System.out.println(s);

                for (Car car : cars) {
                    System.out.println(car.tracks());
                }

                for (Car car : cars) {
                   if(car.getStrides() >= FINISH_LINE){
                       System.out.println(car + " won!");
                       exec.shutdownNow();
                       return;
                   }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("barrier-action sleep interrputed");
                }
            }
        });

        for(int i = 0; i < nCars; i++){
            Car car = new Car(barrier);
            cars.add(car);
            exec.execute(car);
        }
    }

    public static void main(String[] args) {
        new CarRace(7, 100);
    }
}
