package examples;

import org.junit.Test;

import java.util.function.UnaryOperator;

import static junit.framework.Assert.assertEquals;

public class UnaryOperatorTests {
    @Test
    public void unaryOperatorsAreFunctionsThatTakeAndReturnTheSameType(){
        UnaryOperator<String> addFoo = (x)-> x + "Foo";

        assertEquals("BarFoo", addFoo.apply("Bar"));
    }
}
