import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class StaticMethodRefs {

    @Test
    public void canRefByMethod(){

        MyExecute.isOn = false;

        Runnable task = MyExecute::go;

        assertFalse(MyExecute.isOn);

        task.run();

        assertTrue(MyExecute.isOn);
    }

    @Test
    public void canRefInline(){

        MyExecute.isOn = false;

        Runnable task = ()-> MyExecute.go();

        assertFalse(MyExecute.isOn);

        task.run();

        assertTrue(MyExecute.isOn);
    }

    @Test
    public void canRefAsMultiLineBlock(){

        MyExecute.isOn = false;

        Runnable task = ()-> {
            MyExecute.go();
        };

        assertFalse(MyExecute.isOn);

        task.run();

        assertTrue(MyExecute.isOn);
    }

    public static class MyExecute {

        private static boolean isOn;

        public static void go(){
            isOn = true;
        }
    }
}
