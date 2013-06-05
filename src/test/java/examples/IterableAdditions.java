package examples;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class IterableAdditions {
    @Test
    public void forEach(){
        List<Foo> foos = Arrays.asList(new Foo("foo"), new Foo("bar"));

        foos.forEach(x -> x.value = x.value + "123");
    }

}
