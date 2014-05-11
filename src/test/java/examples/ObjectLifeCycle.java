package examples;

import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Supplier;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertSame;

public class ObjectLifeCycle {

    @Test
    public void lambdasAreAlwaysTheSameAnonsAreDifferent(){
        LambdaFactory factory = new LambdaFactory();

        Supplier foo = factory.getLambdaSupplier();
        Supplier foo2 = factory.getLambdaSupplier();

        Supplier bar = factory.getAnonInnerClassSupplier();
        Supplier bar2 = factory.getAnonInnerClassSupplier();

        assertSame(foo, foo2);
        assertNotSame(bar, bar2);
    }

    @Test
    public void lambdasAreStatic(){
        LambdaFactory factory = new LambdaFactory();
        LambdaFactory factory2 = new LambdaFactory();

        Supplier foo = factory.getLambdaSupplier();
        Supplier foo2 = factory2.getLambdaSupplier();

        assertSame(foo, foo2);
    }

    public static class LambdaFactory {
        public Supplier<Integer> getLambdaSupplier(){
            return () -> 42;
        }

        public Supplier<Integer> getAnonInnerClassSupplier(){
            return new Supplier<Integer>() {
                @Override
                public Integer get() {
                    return 42;
                }
            };
        }
    }
}
