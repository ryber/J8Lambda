import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {


    public void lambdaBitches(){
        final ExecutorService executor = Executors.newCachedThreadPool();
        Runnable task = () -> System.out.println("I am Runnable"); //take 1
        executor.submit(task);
    }
}
