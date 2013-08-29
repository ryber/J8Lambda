package examples.streams;

import org.junit.Test;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static junit.framework.Assert.assertEquals;

public class StreamsTest {

    @Test
    public void concatTwoStreams() throws Exception {
        Stream<Integer> ints = of(1, 2, 3);
        Stream<Integer> ints2 = of(4, 5, 6);

        Stream<Integer> result = Stream.concat(ints, ints2);

        assertEquals(6, result.count());
    }

}
