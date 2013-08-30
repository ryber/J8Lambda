package examples.functions;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

import static junit.framework.Assert.assertEquals;

public class FunctionTests {

    @Test
    public void basicFunctionsTakeOneThingAndReturnAnother() throws Exception {
        Function<String, Long> tolong = s -> Long.parseLong(s);

        assertEquals((Long) 2L, tolong.apply("2"));
    }

    @Test
    public void biFunctionsTakeTwoThingsAndReturnAThird(){
        BiFunction<Integer, Boolean, String> c = (i,b) -> i.toString() + b.toString();

        assertEquals("42false", c.apply(42, false));
    }

    @Test
    public void thereAreFunctionsForDealingWithPrimitives() throws Exception {
        IntFunction<String> toString = i -> String.valueOf(i);
        int pInt = 42;
        assertEquals("42", toString.apply(pInt));
    }

    @Test
    public void whatsupWithChaining(){
        UnaryOperator<String> mash = (s -> {
            System.out.println("s = " + s);
            return s.toUpperCase();
        });

        String foo = mash.andThen(s -> s.replace("BAR","zip"))
                .andThen(s -> s.substring(0,s.length() - 1))
                .andThen(s -> "woot" + s)
                        //.compose((Object o) -> "wat" + o)
                .apply("foobarqux");

        System.out.println("mash = " + mash.apply(foo));
    }
}
