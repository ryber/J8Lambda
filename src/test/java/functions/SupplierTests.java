package functions;

import org.junit.Test;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class SupplierTests {

    @Test
    public void suppliersSupplyThings(){
        Supplier<Foo> fooSupplier = () -> new Foo();

        assertNotNull(fooSupplier.get());
        assertTrue(fooSupplier.get() instanceof Foo);
    }

    @Test
    public void specialtySuppliersForPrims() throws Exception {
        IntSupplier i = () -> 42;
        assertEquals(42, i.getAsInt());
    }

    private class Foo {
    }
}
