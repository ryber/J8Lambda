import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

import static junit.framework.Assert.assertEquals;

public class CombinerTests {
    @Test
    public void combinersMerge2Things(){
        BiFunction<Integer, Boolean, String> c = (i,b) -> i.toString() + b.toString();

        assertEquals("42false", c.apply(42,false));
    }

    @Test
    public void binaryOperatorsCombineTheSameType(){
        BinaryOperator<String> bi = (s1,s2) -> s1 + s2;

        assertEquals("FooBar", bi.apply("Foo", "Bar"));
    }

    @Test
    public void doubleBindaryOperatorIsASpecialCaseForDoubles(){
        DoubleBinaryOperator bi = (d1,d2) -> d1 + d2;

        double a = 1.1;
        double b = 2.2;

        assertEquals(3.3, bi.operateAsDouble(a,b), 1);
        assertEquals(new Double(3.3), bi.operate(a,b), 1);
    }
}
