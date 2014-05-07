package examples;

import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Supplier;

public class ObjectLifeCycle {

    @Test
    public void lambdasAreAlwaysTheSameAnonsAreDifferent(){
        LambdaFactory factory = new LambdaFactory();

        Supplier foo = factory.getLambdaSupplier();
        Supplier foo2 = factory.getLambdaSupplier();

        Supplier bar = factory.getAnonInnerClassSupplier();
        Supplier bar2 = factory.getAnonInnerClassSupplier();

        Assert.assertSame(foo, foo2);
        Assert.assertNotSame(bar, bar2);
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
