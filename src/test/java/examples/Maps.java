package examples;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class Maps {

    private String valueWas;

    @Before
    public void setUp() throws Exception {
        valueWas = null;
    }

    @Test
    public void computeIfValueIsPresent() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("foo", "bar");

        map.computeIfPresent("foo", (k,v) -> valueWas = k + v);

        assertEquals("foobar", valueWas);
    }

    @Test
    public void computeIfNotPresentWillNotInvokeFunction() throws Exception {
        valueWas = "nothing";

        HashMap<String, String> map = new HashMap<>();
        map.computeIfPresent("foo", (k,v) -> valueWas = k + v);

        assertEquals("nothing", valueWas);
    }

    @Test
    public void computeIfValueIsAbsent() throws Exception {
        HashMap<String, String> map = new HashMap<>();

        map.computeIfAbsent("foo", (k) -> valueWas = k);

        assertEquals("foo", valueWas);
    }

    @Test
    public void willNotComputeIfValueIsPresent() throws Exception {
        valueWas = "nothing";
        HashMap<String, String> map = new HashMap<>();
        map.put("foo", "bar");

        map.computeIfAbsent("foo", (k) -> valueWas = k);

        assertEquals("nothing", valueWas);
    }
}
