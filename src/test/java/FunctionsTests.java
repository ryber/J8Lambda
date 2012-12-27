import org.junit.Test;

import java.util.function.Function;
import java.util.function.Functions;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.format;

public class FunctionsTests {

    @Test
    public void simpleFunction(){
        Function<String, Integer> f = (s)-> new Integer(s);
        assertEquals((Integer)1, f.apply("1"));
    }

    @Test
    public void canDeferAnotherFunctionToApplyAfterTheFirst(){
        Function<String, String> addsBar = (s)->s+"bar";

        String result = addsBar.compose((s)->s+"baz").apply("foo");

        assertEquals("foobarbaz", result);
    }

    @Test
    public void identifyFunctionForSameObject(){
        // "Useful when a Mapper is required and {@code <T>} and {@code <U>}
        // are the same type."
        Function<Foo, Foo> f = Functions.identity();
        Foo foo = new Foo();
        assertEquals(foo, f.apply(foo));
    }

    @Test
    public void toStringFunction(){
        Function<Integer, String> f = Functions.string();
        assertEquals("1", f.apply(1));
        assertEquals("null", f.apply(null));
    }

    @Test
    public void produceAChainOfFunctions(){
        Function<Integer, String> f = Functions.chain(
                (Function<Integer, Integer>) (x)->x*x,
                Functions.<Integer>string()
        );

        assertEquals("25", f.apply(5));
    }

    @Test
    public void providesAConstantForResult(){
        Function<Object, String> always42 = Functions.constant("42");

        assertEquals("42", always42.apply("The meaning of life"));
        assertEquals("42", always42.apply(new Foo()));
    }

    @Test
    public void subInOutFunction(){
        Function<String, String> f = Functions.substitute("foo", "bar");

        assertEquals("bar", f.apply("foo"));
        assertEquals("bar", f.apply("bar"));
        assertEquals("baz", f.apply("baz"));
        assertEquals(null, f.apply(null));
    }

    @Test
    public void constructorFunction(){
        Function<String, Integer> f = Functions.instantiate(String.class, Integer.class);
        assertEquals((Integer)1, f.apply("1"));
    }
}
