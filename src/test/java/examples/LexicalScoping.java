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

    Supplier<String> r3 = new Supplier<String>() {
        @Override
        public String get() {
            return this.toString();
        }
    };

    Supplier<String> r4 = new Supplier<String>() {
        @Override
        public String get() {
            return toString();
        }
    };


    public String toString() { return HELLO_WORLD; }

    @Test
    public void lambdasDontHaveTheirOwnThis(){
        assertThat(HELLO_WORLD, is(r1.get()));
        assertThat(HELLO_WORLD, is(r2.get()));
        assertThat(HELLO_WORLD, not(r3.get()));

        System.out.println("r1.get() = " + r1.get());
        System.out.println("r2.get() = " + r2.get());
        System.out.println("r3.get() = " + r3.get());
        System.out.println("r4.get() = " + r4.get());
        // System.out.println("new NotALambda().get() = " + r3.get());
    }


}
