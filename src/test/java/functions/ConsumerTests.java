package functions;

import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import static junit.framework.Assert.assertEquals;

public class ConsumerTests {

    // Consumers are expected to produce side effects!

    @Test
    public void aConsumerTakesSomethingAndReturnsVoid() throws Exception {
        PennyTray pt = new PennyTray(1);

        Consumer<PennyTray> c = s -> s.amount++;
        c.accept(pt);

        assertEquals(2, pt.amount);
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

    @Test
    public void aBiConsumerTakesTwoThings() throws Exception {
        PennyTray pt = new PennyTray(1);
        BiConsumer<PennyTray, Integer> addEm = (p,i) -> p.amount = p.amount + i;

        addEm.accept(pt, 41);

        assertEquals(42, pt.amount);
    }

    int consumed = 0;

    @Test
    public void thereAreSpecialtyConsumersForPrimatives() throws Exception {
        consumed = 0;
        IntConsumer c = i -> consumed = i;
        c.accept(42);

        assertEquals(42, consumed);
    }

    private static class PennyTray {
        public int amount;

        public PennyTray(int starting) {
            amount = starting;
        }
    }
}
