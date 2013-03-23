import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Functions;
import java.util.function.Predicate;

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

    @Test
    public void functionsForMaps(){
        Map<String,String> m = new HashMap<>();
        m.put("foo","bar");
        m.put("baz","hobbit");

        Function<String, String> f = Functions.forMap(m);

        assertEquals("bar", f.apply("foo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void functionsForMapsAnnoyinglyThrowsExceptions(){
        Map<String,String> m = new HashMap<>();
        m.put("foo","bar");
        m.put("baz","hobbit");

        Function<String, String> f = Functions.forMap(m);

        f.apply("smurf");
    }

    @Test
    public void functionsForMapsAreNicerWithDefaults(){
        Map<String,String> m = new HashMap<>();
        m.put("foo","bar");
        m.put("baz","hobbit");

        Function<String, String> f = Functions.forMap(m, "gandalf");

        assertEquals("gandalf", f.apply("smurf"));
    }

    @Test
    public void functionBasedOnPredicate(){

        Function<String,String> barOrBaz = Functions.forPredicate(
                (Predicate<String>) (x)-> Objects.equals(x, "foo"),
                "bar",
                "baz");

        assertEquals("bar", barOrBaz.apply("foo"));
        assertEquals("baz", barOrBaz.apply("smurf"));
    }
}
