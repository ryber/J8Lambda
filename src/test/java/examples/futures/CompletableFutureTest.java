package examples.futures;


import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CompletableFutureTest {

    @Test
    public void CompletableFutureCanFoo(){
        CompletableFuture<Integer> foo = CompletableFuture.supplyAsync(() -> 42);
        //Integer result = foo.

    }
}
