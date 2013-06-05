package examples.streams;

import examples.Foo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.empty;
import static java.util.stream.Stream.of;
import static junit.framework.Assert.*;

public class StreamTests {
    @Test
    public void canFilterCollectionToAnother(){
        Stream<Integer> foos = of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> lessThan5 = foos.filter(x -> x < 5).collect(Collectors.<Integer>toList());

        assertTrue(lessThan5.contains(4));
        assertFalse(lessThan5.contains(5));
        assertEquals(4, lessThan5.size());
    }

    @Test
    public void canCheckThatAllItemsMatch(){
        List<Integer> foos = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertFalse(foos.stream().allMatch(x -> x < 5));
        assertTrue(foos.stream().allMatch(x -> x < 10));
    }

    @Test
    public void canCheckThatAnyMatch(){
        List<Integer> foos = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertFalse(foos.stream().anyMatch(x -> x > 25));
        assertTrue(foos.stream().anyMatch(x -> x < 5));
    }

    @Test
    public void canCheckThatNoneMatch(){
        List<Integer> foos = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertFalse(foos.stream().noneMatch(x -> x < 25));
        assertTrue(foos.stream().noneMatch(x -> x > 25));
    }

    @Test
    public void canTakeASlice(){
        Stream<Integer> foos = of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> aSlice = foos.substream(3, 7).collect(toList());

        assertEquals((Integer)4, aSlice.get(0));
        assertEquals((Integer)7, aSlice.get(3));
        assertEquals(4, aSlice.size());
    }

    @Test
    public void canLimitStream(){
        Stream<Integer> foos = of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> aSlice = foos.limit(3).collect(toList());

        assertEquals((Integer)1, aSlice.get(0));
        assertEquals((Integer)3, aSlice.get(2));
        assertEquals(3, aSlice.size());
    }

    @Test
    public void canSkipElements(){
        Stream<Integer> foos = of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> aSlice = foos.substream(3).collect(toList());

        assertEquals((Integer)4, aSlice.get(0));
        assertEquals((Integer)9, aSlice.get(5));
        assertEquals(6, aSlice.size());
    }

    @Test
    public void canGetTheFirstElement(){
        Stream<Integer> foos = of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> noFoos = empty();

        Optional<Integer> first = foos.findFirst();
        Optional<Integer> empty = noFoos.findFirst();

        assertEquals((Integer)1, first.get());
        assertFalse(empty.isPresent());
    }

    @Test
    public void canGetAnyItem(){
        Stream<Integer> foos = of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> noFoos = empty();

        Optional<Integer> any = foos.findAny();
        Optional<Integer> empty = noFoos.findAny();

        assertTrue(any.isPresent());
        assertFalse(empty.isPresent());
    }

    @Test
    public void canGetUniqueElements(){
        Stream<Integer> foos = of(1, 2, 3, 1, 2, 3, 1, 2, 3);

        List<Integer> aSlice = foos.distinct().collect(toList());

        assertTrue(aSlice.contains(1));
        assertTrue(aSlice.contains(2));
        assertTrue(aSlice.contains(3));
        assertEquals(3, aSlice.size());
    }

    @Test
    public void canPerformAnActionOnEachItem(){
        List<Foo> foos = asList(new Foo(), new Foo());

        foos.forEach(f-> {f.value = "bar";});

        assertEquals("bar", foos.get(0).value);
        assertEquals("bar", foos.get(1).value);
    }

    @Test
    public void canSortNatty() throws Exception {
        Stream<Integer> ints = of(182, 666, 2, 42);
        List<Integer> sorted = ints.sorted().collect(toList());

        assertEquals((Integer)2, sorted.get(0));
        assertEquals((Integer)42, sorted.get(1));
        assertEquals((Integer)182, sorted.get(2));
        assertEquals((Integer)666, sorted.get(3));
    }

    @Test
    public void canSortWithAComparator(){
        Stream<String> foos = of("Indigo", "Blue", "Green", "Violet", "Yellow", "Orange", "Red");

        List<String> sorted = foos.sorted((s1, s2)->s1.length() - s2.length()).collect(toList());

        assertEquals("Red", sorted.get(0));
    }

    @Test
    public void canGetMax(){
        Stream<Integer> foos = of(1, 2, 3);

        Optional<Integer> max = foos.max((left, right) -> left - right);
        assertEquals((Integer)3, max.get());
    }

    @Test
    public void canGetMin(){
        Stream<Integer> foos = of(1, 2, 3);

        Optional<Integer> max = foos.min((left, right) -> left - right);
        assertEquals((Integer)1, max.get());
    }

    private List<Integer> LOG;

    @Test
    public void canPeekAtItems() throws Exception {
        LOG = new ArrayList<>();
        List<Integer> foos = asList(1, 2, 3);
        foos.stream().peek(x -> LOG.add(x)).collect(toList());

        assertEquals(foos, LOG);
    }

    @Test
    public void onceAStreamIsConsumedItCannotBeAgain() throws Exception {
        Stream<Integer> ints = of(1,2,3);
        ints.allMatch(x -> x > 0);

        try{
            ints.allMatch(x -> x > 0);
            fail("Should have thrown IllegalStateException");
        }catch (IllegalStateException e){

        }
    }

}
