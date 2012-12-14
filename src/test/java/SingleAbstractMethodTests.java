import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SingleAbstractMethodTests {
    interface Squarer<X, Y> {
        Y square(X x);
    }

    @Test
    public void implAInterfaceInLambda(){
        Squarer<Integer, Integer> s = (x) -> x * x;
        assertEquals((Integer)25, s.square(5));
    }
}
