import org.junit.Test;

import java.util.*;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import static junit.framework.Assert.*;

public class StreamTests {
    @Test
    public void canFilterCollectionToAnother(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<Integer> lessThan5 = foos.stream().filter(x -> x < 5).into(new ArrayList<Integer>());

        assertTrue(lessThan5.contains(4));
        assertFalse(lessThan5.contains(5));
        assertEquals(4, lessThan5.size());
    }

    @Test
    public void canCheckThatAllItemsMatch(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);

        assertFalse(foos.stream().allMatch(x -> x < 5));
        assertTrue(foos.stream().allMatch(x -> x < 10));
    }

    @Test
    public void canCheckThatAnyMatch(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);

        assertFalse(foos.stream().anyMatch(x -> x > 25));
        assertTrue(foos.stream().anyMatch(x -> x < 5));
    }

    @Test
    public void canCheckThatNoneMatch(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);

        assertFalse(foos.stream().noneMatch(x -> x < 25));
        assertTrue(foos.stream().noneMatch(x -> x > 25));
    }

    @Test
    public void canTakeASlice(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<Integer> aSlice = foos.stream().slice(3, 4).into(new ArrayList<Integer>());

        assertEquals((Integer)4, aSlice.get(0));
        assertEquals((Integer)7, aSlice.get(3));
        assertEquals(4, aSlice.size());
    }

    @Test
    public void canLimitStream(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<Integer> aSlice = foos.stream().limit(3).into(new ArrayList<Integer>());

        assertEquals((Integer)1, aSlice.get(0));
        assertEquals((Integer)3, aSlice.get(2));
        assertEquals(3, aSlice.size());
    }

    @Test
    public void canSkipElements(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<Integer> aSlice = foos.stream().skip(3).into(new ArrayList<Integer>());

        assertEquals((Integer)4, aSlice.get(0));
        assertEquals((Integer)9, aSlice.get(5));
        assertEquals(6, aSlice.size());
    }

    @Test
    public void canGetTheFirstElement(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> noFoos = new ArrayList<>();

        Optional<Integer> first = foos.stream().findFirst();
        Optional<Integer> empty = noFoos.stream().findFirst();

        assertEquals((Integer)1, first.get());
        assertFalse(empty.isPresent());
    }

    @Test
    public void canGetAnyItem(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> noFoos = new ArrayList<>();

        Optional<Integer> any = foos.stream().findAny();
        Optional<Integer> empty = noFoos.stream().findAny();

        assertTrue(any.isPresent());
        assertFalse(empty.isPresent());
    }

    @Test
    public void canGetUniqueElements(){
        List<Integer> foos = Arrays.asList(1,2,3,1,2,3,1,2,3);

        List<Integer> aSlice = foos.stream().uniqueElements().into(new ArrayList<Integer>());

        assertTrue(aSlice.contains(1));
        assertTrue(aSlice.contains(2));
        assertTrue(aSlice.contains(3));
        assertEquals(3, aSlice.size());
    }

    @Test
    public void canPerformAnActionOnEachItem(){
        List<Foo> foos = Arrays.asList(new Foo(), new Foo());

        foos.forEach(f-> {f.value = "bar";});

        assertEquals("bar", foos.get(0).value);
        assertEquals("bar", foos.get(1).value);
    }

    @Test
    public void canPerformAnActionOnEachItemAndThenTeeThePipelineBack(){
        List<Foo> foos = Arrays.asList(new Foo(), new Foo());

        List<Foo> result = foos.stream().tee(f-> {f.value = "bar";}).into(new ArrayList<Foo>());

        assertEquals("bar", result.get(0).value);
        assertEquals("bar", result.get(1).value);
    }

    @Test
    public void canSortWithAComparator(){
        List<String> foos = Arrays.asList("Indigo", "Blue", "Green", "Violet", "Yellow", "Orange", "Red");

        List<String> sorted = foos.stream().sorted((s1, s2)->s1.length() - s2.length()).into(new ArrayList<String>());

        assertEquals("Red", sorted.get(0));
    }

    @Test
    public void canCulminateEachItemWithTheNext(){
        List<Integer> foos = Arrays.asList(1,2,3);

        List<Integer> merged = foos.stream().cumulate((i1, i2)-> i1 + i2).into(new ArrayList<Integer>());

        assertEquals((Integer)1, merged.get(0));
        assertEquals((Integer)3, merged.get(1));
        assertEquals((Integer)6, merged.get(2));
    }

    @Test
    public void canGetMax(){
        List<Integer> foos = Arrays.asList(1,2,3);

        Optional<Integer> max = foos.stream().max((left, right) -> left - right);
        assertEquals((Integer)3, max.get());
    }

    @Test
    public void canGetMin(){
        List<Integer> foos = Arrays.asList(1,2,3);

        Optional<Integer> max = foos.stream().min((left, right) -> left - right);
        assertEquals((Integer)1, max.get());
    }

}
