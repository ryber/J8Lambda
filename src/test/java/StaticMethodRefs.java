import org.junit.Test;

import static junit.framework.Assert.*;

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

    @Test
    public void canContsructWithMethodReference(){
        Runnable e = MyRunnable::new;

        assertNotNull(e);
    }

    private static class MyExecute  {
        private static boolean isOn;
        public static void go() {
            isOn = true;
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
        }
    }
}
