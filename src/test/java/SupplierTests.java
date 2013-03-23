import org.junit.Test;

import java.util.function.Supplier;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class SupplierTests {

    @Test
    public void suppliersSupplyThings(){
        Supplier<Foo> fooSupplier = () -> new Foo();

        assertNotNull(fooSupplier.get());
        assertTrue(fooSupplier.get() instanceof Foo);
    }
}
