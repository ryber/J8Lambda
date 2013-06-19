package examples.streams;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Streams;

import static junit.framework.Assert.assertEquals;

public class PrimitiveStreams {
    int count = 0;

    @Test
    public void ranges() throws Exception {
        count = 0;
        IntStream.range(0, 50).forEach(x -> count++ );
        assertEquals(50, count);
    }

    @Test
    public void specializedConcatForPrims() throws Exception {
        IntStream ints = IntStream.of(1,2,3);
        IntStream ints2 = IntStream.of(4,5,6);

        IntStream result = Streams.concat(ints, ints2);

        assertEquals(6, result.count());
    }
}
