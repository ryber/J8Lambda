package examples;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class Maps {

    @Test
    public void computeIfValueIsAbsent() throws Exception {
        HashMap<String, String> map = new HashMap<>();

        map.computeIfAbsent("foo", (k) -> k.toUpperCase());

        assertEquals("FOO", map.get("foo"));
    }

    @Test
    public void willNotComputeIfValueIsPresent() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("foo", "bar");

        map.computeIfAbsent("foo", (k) -> k.toUpperCase());

        assertEquals("bar", map.get("foo"));
    }

    @Test
    public void computeIfValueIsPresent() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("foo", "bar");

        map.computeIfPresent("foo", (k,v) -> k + v);

        assertEquals("foobar", map.get("foo"));
    }

    @Test
    public void computeIfNotPresentWillNotInvokeFunction() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.computeIfPresent("foo", (k, v) -> k + v);

        assertNull(map.get("foo"));
    }


}
