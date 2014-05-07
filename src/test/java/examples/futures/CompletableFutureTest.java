package examples.futures;


import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;


public class CompletableFutureTest {

    private Integer consumedResult;

    @Before
    public void setUp(){
        consumedResult = null;
    }

    @Test
    public void canGetAValueFromAFuture() throws Exception {
        CompletableFuture<Integer> foo = CompletableFuture.supplyAsync(() -> 42);
        assertEquals((Integer)42, foo.get());
    }

    @Test
    public void canHandleExceptionsAsync() throws Exception {
        CompletableFuture<Integer> foo = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("boo!");
        });
        CompletableFuture<Integer> handled = foo.exceptionally(ex -> ex.getMessage().equals("java.lang.RuntimeException: boo!") ? 42 : -1);

        assertEquals((Integer) 42, handled.get());
    }

    @Test
    public void canConsumeAResult() throws Exception {

        CompletableFuture<Integer> foo = CompletableFuture.supplyAsync(() -> 42);
        foo.thenAccept(x -> consumedResult = x);

        assertEquals((Integer)42, consumedResult);
    }


}
