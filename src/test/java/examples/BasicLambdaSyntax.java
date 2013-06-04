package examples;

import org.junit.Before;
import org.junit.Test;

import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class BasicLambdaSyntax {
    int fooCalled = 0;
    int barCalled = 0;

    interface Action<T> {
        T execute();
    }

    String doFoo(){
        fooCalled++;
        return "foo";
    }

    String doBar(){
        barCalled++;
        return "bar";
    }



    @Before
    public void setUp(){
        fooCalled = 0;
        barCalled = 0;
    }

    @Test
    public void multiLineLambdas(){
        assertTrue(Boolean.TRUE);

        Runnable run = () -> {
            for(int x = 0; x < 10; x++)
                fooCalled++;
        };

        run.run();

        assertEquals(10, fooCalled);
    }

    @Test
    public void creatingClosuresDoesNotExecuteCode(){

        Map<String, Action<String>> commands = new HashMap<>();
        commands.put("f",()-> doFoo());
        commands.put("b",()-> doBar());

        assertEquals(0, fooCalled);
        assertEquals(0, barCalled);
    }

    @Test
    public void canExecuteClosuresLater(){
        Map<String, Action<String>> commands = new HashMap<>();
        commands.put("f",()-> doFoo());
        commands.put("b",()-> doBar());

        assertEquals("foo", commands.get("f").execute());
        assertEquals("bar", commands.get("b").execute());
        assertEquals(1, barCalled);
    }

    @Test
    public void singleParamMethodsDontNeedParams() throws Exception {
        FileFilter java = f -> f.getName().endsWith(".java");
        assertTrue(java instanceof FileFilter);
    }

    interface Squarer<X, Y> {
        Y square(X x);
    }

    @Test
    public void implAInterfaceInLambda(){
        Squarer<Integer, Integer> s = (x) -> x * x;
        assertEquals((Integer)25, s.square(5));
    }

}
