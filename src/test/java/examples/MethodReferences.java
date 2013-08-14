package examples;

import org.junit.Test;

import java.util.Comparator;

import static junit.framework.Assert.*;

public class MethodReferences {

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
        Runnable e = MyComparer::new;

        assertNotNull(e);
    }

//    @Test
//    public void useAInstanceMethodRef(){
//        Comparator<String> c = new MyComparer();
//
//        List<String> letters =  Arrays.asList("j","c","m", "a");
//
//        Arrays.sort(letters, c::compare);
//    }

    private static class MyExecute  {
        private static boolean isOn;
        public static void go() {
            isOn = true;
        }
    }


    private static class MyComparer implements Comparator<String> {

        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }
}
