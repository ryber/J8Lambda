import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Collections {
    @Test
    public void filter(){
        List<Integer> foos = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Iterable<Integer> lessThan5 = foos.filter(x->x<5);
    }
}
