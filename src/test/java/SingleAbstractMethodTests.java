import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

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
