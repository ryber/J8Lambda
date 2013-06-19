package examples;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class Lists {
    @Test
    public void replaceAllItemsWithCalculations() throws Exception {
        List<String> foos = new ArrayList<>();
        foos.add("foo");
        foos.add("bar");

        foos.replaceAll(x -> x.toUpperCase());

        assertEquals(2, foos.size());
        assertThat(foos, hasItem("FOO"));
        assertThat(foos, hasItem("BAR"));
    }

    @Test
    public void inlineSort() throws Exception {
        List<String> foos = new ArrayList<>();
        foos.add("alabama");
        foos.add("ok");
        foos.add("zebra");

        foos.sort((x,y) -> x.length() - y.length());

        assertEquals("ok", foos.get(0));
        assertEquals("alabama", foos.get(2));
    }
}
