package examples.functions;

import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.UnaryOperator;

import static junit.framework.Assert.assertEquals;

public class OperatorTests {

    @Test
    public void binaryOperatorsCombineTheSameType(){
        BinaryOperator<String> bi = (s1,s2) -> s1 + s2;

        assertEquals("FooBar", bi.apply("examples.Foo", "Bar"));
    }

    @Test
    public void unaryOperatorsModifyAndReturnTheSameType() throws Exception {
        UnaryOperator<String> u = s -> s.substring(0,1);
        assertEquals("F", u.apply("examples.Foo"));
    }

    @Test
    public void doubleBindaryOperatorIsASpecialCaseForDoubles(){
        DoubleBinaryOperator bi = (d1,d2) -> d1 + d2;

        double a = 1.1;
        double b = 2.2;

        assertEquals(3.3, bi.applyAsDouble(a,b), 1);
        assertEquals(new Double(3.3), bi.applyAsDouble(a,b), 1);
    }



}
