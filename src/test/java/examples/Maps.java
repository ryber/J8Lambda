package examples;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class Maps {

    @Test
    public void computeIfValueIsAbsent() throws Exception {
        Map<String, String> map = new HashMap<>();

        String foo = map.computeIfAbsent("foo", (k) -> loadFraomDataBase(k));

        assertEquals("Big ol object with a key of foo", map.get("foo"));
        assertEquals("Big ol object with a key of foo", foo);
    }

    @Test
    public void willNotComputeIfValueIsPresent() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "already loaded");

        map.computeIfAbsent("foo", (k) -> loadFraomDataBase(k));

        assertEquals("already loaded", map.get("foo"));
    }

    private String loadFraomDataBase(String key) {
        return "Big ol object with a key of " + key;
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

    @Test
    public void computeCalculatesIfItsThereOrNot() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("foo","bar");

        map.compute("foo", (k, v) -> v + v);
        map.compute("baz", (k, v) -> v + v);

        assertEquals("barbar", map.get("foo"));
        assertEquals("nullnull", map.get("baz"));
    }

    @Test
    public void merge() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("foo","bar");

        map.merge("foo", "rad", (old, nw) -> old + nw);
        map.merge("baz", "woot", (old,nw) -> old + nw);

        assertEquals("barrad", map.get("foo"));
        assertEquals("woot", map.get("baz"));
    }

    @Test
    public void recomputeAllItems() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "bar");
        map.put("baz", "qux");

        map.replaceAll((k,v) -> "--" + v);

        assertEquals("--bar", map.get("foo"));
        assertEquals("--qux", map.get("baz"));
    }
}
