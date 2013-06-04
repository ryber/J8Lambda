package examples;

import examples.Foo;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;

public class MapReduceTests {
    @Test
    public void mapAResult() {
        List<Foo> foos = Arrays.asList(new Foo("foo"), new Foo("Bar"));

        List<String> result = foos.stream()
                .map((x) -> x.value)
                .collect(toList());

        assertEquals("foo", result.get(0));
        assertEquals("Bar", result.get(1));
    }

    @Test
    public void reduceToSingleResult() {
        List<Foo> foos = Arrays.asList(new Foo("examples.Foo"),
                new Foo("Bar"),
                new Foo("Baz"));


        String result = foos.stream()
                .map((x) -> x.value)
                .reduce("Nutz", (left, right) -> left + right);

        assertEquals("NutzFooBarBaz", result);
    }

    @Test
    public void withoutABaseYouGetAnOptional() {
        List<Foo> foos = Arrays.asList(new Foo("examples.Foo"),
                new Foo("Bar"),
                new Foo("Baz"));

        Optional<String> result = foos.stream()
                .map((Function<Foo, String>) (x) -> x.value)
                .reduce((left, right) -> left + right);

        assertEquals("FooBarBaz", result.get());
    }
}
