import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static junit.framework.Assert.assertEquals;

public class MapReduceTests {
    @Test
    public void mapAResult() {
        List<Foo> foos = Arrays.asList(new Foo("foo"), new Foo("Bar"));

        ///Aaaarg! Java, why you no infer function type?!
        List<String> result = foos.stream()
                .map((Function<Foo, String>) (x) -> x.value)
                .into(new ArrayList<String>());

        assertEquals("foo", result.get(0));
        assertEquals("Bar", result.get(1));
    }

    @Test
    public void reduceToSingleResult() {
        List<Foo> foos = Arrays.asList(new Foo("Foo"),
                new Foo("Bar"),
                new Foo("Baz"));

        //Why do I have to cast the function but not the binary operator?
        String result = foos.stream()
                .map((Function<Foo, String>) (x) -> x.value)
                .reduce("", (left, right) -> left + right);

        assertEquals("FooBarBaz", result);
    }
}
