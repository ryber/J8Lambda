import org.junit.Test;

import javax.xml.stream.StreamFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static junit.framework.Assert.assertEquals;

public class ExtensionMethodTests {

    public interface FancyInterface   {
        default String reverse(String s) {
            return new StringBuffer(s).reverse().toString();
        }
    }

    @Test
    public void applyExtensionMethod(){
        FancyInterface c = new FancyInterface() {};

        assertEquals("cba", c.reverse("abc"));
    }

}
