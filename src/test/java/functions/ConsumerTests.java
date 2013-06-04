package functions;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static junit.framework.Assert.assertEquals;

public class ConsumerTests {
    String passed;

    @Before
    public void setUp() throws Exception {
        passed = null;
    }

    @Test
    public void aConsumerTakesSomethingAndReturnsVoid() throws Exception {
        Consumer<String> c = s -> passed = s;
        c.accept("foo");
        assertEquals("foo", passed);
    }

    @Test
    public void chainingConsumers() throws Exception {
        PennyTray pt = new PennyTray(20);

        Consumer<PennyTray> c = p -> p.amount = p.amount - 2;
        c.chain(s -> s.amount++)
                .chain(s -> s.amount = s.amount * 2)
                .accept(pt);

        assertEquals(38, pt.amount);
    }


    private static class PennyTray {
        public int amount;

        public PennyTray(int starting) {
            amount = starting;
        }
    }
}
