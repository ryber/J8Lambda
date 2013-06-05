package examples.streams;

import examples.Foo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class MapReduceTests {
    @Test
    public void canMapIntoAnotherObject() throws Exception {
        List<String> ids = Arrays.asList("a");

        List<Foo> foos = ids.stream().map(x -> new Foo(x)).collect(toList());

        assertEquals("a", foos.get(0).getValue());
    }

    @Test
    public void specialtyStreamMapsForPrims() throws Exception {
        Stream<String> ids = Arrays.asList("42").stream();

        IntStream ints = ids.mapToInt(x -> Integer.valueOf(x));

        assertEquals(42, ints.findFirst().getAsInt());
    }

    @Test
    public void voidFlatMapsFlattenSetsOfStreams(){
        Stream<Bar> bars = Arrays.asList(new Bar("a","b","c"), new Bar("z","y", "x")).stream();

        List<String> names = bars.flatMap(x -> x.names.stream()).collect(toList());

        assertEquals(6, names.size());
        assertThat(names, hasItem("b"));
        assertThat(names, hasItem("y"));
    }

    private class Bar {
        private Collection<String> names;

        public Bar(String... names){
            this.names = Arrays.asList(names);
        }
    }

    @Test
    public void reduceToSingleResult() {
        List<Foo> foos = Arrays.asList(new Foo("Foo"),
                new Foo("Bar"),
                new Foo("Baz"));


        String result = foos.stream()
                .map(x -> x.value)
                .reduce("Nutz", (left, right) -> left + right);

        assertEquals("NutzFooBarBaz", result);
    }

    @Test
    public void withoutABaseYouGetAnOptional() {
        List<Foo> foos = Arrays.asList(new Foo("Foo"),
                new Foo("Bar"),
                new Foo("Baz"));

        Optional<String> result = foos.stream()
                .map(x -> x.value)
                .reduce((left, right) -> left + right);

        assertEquals("FooBarBaz", result.get());
    }
}
