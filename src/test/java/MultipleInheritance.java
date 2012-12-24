import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MultipleInheritance {
    interface A { default int foo(){ return 1; }}
    interface B extends A { default int foo() {return 2;}}
    interface C extends A { default int foo() {return 3;}}

    // D and E will not compile because its impl of foo is ambiguious
    // interface D extends B,C { }
    // class E implements B,C {}

    // But we can imp both B and C if we specify the foo we want
    interface F extends B,C {default int foo(){return B.super.foo(); }}
    class G implements B,C {public int foo(){ return C.super.foo(); }}
    class H implements B,C {public int foo(){return 42;}}

    @Test
    public void aGetsItsDefaults(){
        A a = new A(){};
        assertEquals(1, a.foo());
    }

    @Test
    public void inheritingInterfacesWillOverrideDefault(){
        B b = new B(){};
        assertEquals(2, b.foo());
    }

    @Test
    public void asLongAsWeSpecifyTheSuperWeCanFixConflicts(){
        F f = new F(){};
        assertEquals(2, f.foo());
    }

    @Test
    public void canDoTheSameThingWithClasses(){
        G g = new G(){};
        assertEquals(3, g.foo());
    }

    @Test
    public void orOverrideTheDefault(){
        H h = new H();
        assertEquals(42, h.foo());
    }

}
