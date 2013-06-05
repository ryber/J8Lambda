package examples;

import org.junit.Test;

import java.util.function.Supplier;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class LexicalScoping {

    public static final String HELLO_WORLD = "Hello, world!";

    Supplier<String> r1 = () -> this.toString();
    Supplier<String> r2 = () -> toString();

    private class NotALambda implements Supplier<String> {
        @Override
        public String get() {
            return this.toString();
        }
    }


    public String toString() { return HELLO_WORLD; }

    @Test
    public void lambdasDontHaveTheirOwnThis(){
        assertThat(HELLO_WORLD, is(r1.get()));
        assertThat(HELLO_WORLD, is(r2.get()));
        assertThat(HELLO_WORLD, not(new NotALambda().get()));
        // System.out.println("new NotALambda().get() = " + new NotALambda().get());
    }


}
