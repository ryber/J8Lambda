package examples;

import junit.framework.Assert;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.LambdaMetafactory;
import java.util.function.Supplier;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

public class ObjectLifeCycle {

    @Test
    public void lambdasAreAlwaysTheSameAnonsAreDifferent(){
        LambdaFactory factory = new LambdaFactory();

        assertSame(factory.getLambdaSupplier(), factory.getLambdaSupplier());

        assertNotSame(factory.getAnonInnerClassSupplier(), factory.getAnonInnerClassSupplier());
    }

    @Test
    public void lambdasAreStatic(){
        LambdaFactory factory = new LambdaFactory();
        LambdaFactory factory2 = new LambdaFactory();

        assertSame(factory.getLambdaSupplier(), factory2.getLambdaSupplier());
    }

    @Test
    public void theSameLambdaInADifferentLocationisNotTheSame(){
        LambdaFactory foo = new LambdaFactory();
        assertNotSame(foo.getLambdaSupplier(), foo.getAnotherLambda());
    }


    @Test
    public void lambdasStackTraceIsStillNice(){
        LambdaFactory factory = new LambdaFactory();
        Supplier foo = factory.getThrowingLambda();

        try {
            foo.get();
            fail("Should have thrown");
        }catch (Exception e){
            Assert.assertTrue(getStackTrace(e).contains("at examples.ObjectLifeCycle$LambdaFactory.lambda$1(ObjectLifeCycle.java:"));
        }
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

        public Supplier<Integer> getThrowingLambda(){
             return () -> {throw new RuntimeException();};
        }

        public Supplier<Integer> getAnotherLambda(){
            return () -> 42;
        }
    }

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String result = sw.toString();
        //System.out.println("result = " + result);
        return result;
    }

}
