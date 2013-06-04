import org.junit.Test;

import java.util.Objects;
import java.util.function.Predicate;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class PredicateTests {

    @Test
    public void youCanMakeYourOwnPredicates(){
        Predicate<String> isFoo = (x)-> Objects.equals(x,"foo");

        assertTrue(isFoo.test("foo"));
        assertFalse(isFoo.test("bar"));
    }

    @Test
    public void canChainPredicates(){
        Predicate<Integer> gt6 = (x)-> x > 6;
        Predicate<Integer> lt9 = (x)-> x < 9;

        assertTrue(gt6.and(lt9).test(7));
        assertFalse(gt6.and(lt9).test(5));
        assertFalse(gt6.and(lt9).test(10));
    }

    @Test
    public void canProduceANegatingPredicate(){
        Predicate<String> isFoo = (x)-> Objects.equals(x,"foo");

        assertFalse(isFoo.negate().test("foo"));
        assertTrue(isFoo.negate().test("bar"));
    }

    @Test
    public void canProduceOrPredicates(){
        Predicate<Integer> is6 = (x)->x.equals(6);
        Predicate<Integer> is9 = (x)->x.equals(9);


        assertTrue(is6.or(is9).test(6));
        assertTrue(is6.or(is9).test(9));
        assertFalse(is6.or(is9).test(7));
    }

    @Test
    public void canDoAOr(){
        Predicate<String> containsF = (x) -> x.contains("F");
        Predicate<String> startsWithp = (x) -> x.startsWith("p");

        Predicate<String> or = containsF.or(startsWithp);

        assertTrue(or.test("patty"));
        assertTrue(or.test("Barney Fife"));
        assertFalse(or.test("Neither is true here"));
    }


}
