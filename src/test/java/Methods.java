import com.sun.source.util.TaskEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class Methods {
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

}
