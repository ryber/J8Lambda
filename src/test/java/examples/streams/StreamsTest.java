package examples.streams;

import examples.Foo;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Streams;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static junit.framework.Assert.assertEquals;

public class StreamsTest {

    @Test
    public void concatTwoStreams() throws Exception {
        Stream<Integer> ints = of(1, 2, 3);
        Stream<Integer> ints2 = of(4, 5, 6);

        Stream<Integer> result = Streams.concat(ints, ints2);

        assertEquals(6, result.count());
    }

    @Test
    public void specializedConcatForPrims() throws Exception {
        IntStream ints = IntStream.of(1,2,3);
        IntStream ints2 = IntStream.of(4,5,6);

        IntStream result = Streams.concat(ints, ints2);

        assertEquals(6, result.count());
    }

    @Test
    public void zippingTwoStreamsTogether() throws Exception {
        Stream<Foo> foos = of(new Foo("a"), new Foo("b"));
        Stream<String> newValues = of("foo", "bar", "baz", "qux");  //note that the extra elements are discarded

        List<Foo> newFoos = Streams.zip(foos, newValues, (f, n) -> {f.value = n; return f; })
                                    .collect(toList());

        assertEquals("foo", newFoos.get(0).getValue());
        assertEquals("bar", newFoos.get(1).getValue());

    }
}
