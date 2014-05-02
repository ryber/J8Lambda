package examples.futures;

import org.junit.Test;

import java.util.stream.IntStream;

import static junit.framework.Assert.assertEquals;

public class ParallelProcessing {
    @Test
    public void parallelProcessAList() {
        IntStream.of(1, 2, 3, 4, 5, 6)
                .parallel()
                .max()
                .ifPresent(i -> assertEquals(6, i));
    }


}
