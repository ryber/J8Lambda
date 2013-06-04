package functions;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

import static junit.framework.Assert.assertEquals;

public class FunctionTests {

    @Test
    public void basicFunctionsTakeOneThingAndReturnAnother() throws Exception {
        Function<String, Long> tolong = s -> Long.parseLong(s);

        assertEquals((Long)2L, tolong.apply("2"));
    }

    @Test
    public void biFunctionsTakeTwoThingsAndReturnAThird(){
        BiFunction<Integer, Boolean, String> c = (i,b) -> i.toString() + b.toString();

        assertEquals("42false", c.apply(42,false));
    }

    @Test
    public void thereAreFunctionsForDealingWithPrimitives() throws Exception {
        IntFunction<String> toString = i -> String.valueOf(i);
        int pInt = 42;
        assertEquals("42", toString.apply(pInt));
    }
}
