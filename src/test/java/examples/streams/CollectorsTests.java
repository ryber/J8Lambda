package examples.streams;

import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectorsTests {
    @Test
    public void canCollectToAList() throws Exception {
        assertEquals(3, Stream.of("a","b","c").collect(toList()).size());
    }

    @Test
    public void canCollectIntoAStringBuilder() throws Exception {
        String strings = Stream.of("foo", "bar", "baz").collect(joining());
        assertEquals("foobarbaz", strings);
    }

    @Test
    public void canCollectToAStringJoiner() throws Exception {
        String strings = Stream.of("foo", "bar", "baz").collect(joining(":"));
        assertEquals("foo:bar:baz", strings);
    }

    @Test
    public void mergStreamIntoMap() throws Exception {
        Stream<Person> people = Stream.of(new Person("Des Moines", "Nancy"),
                                        new Person("Des Moines", "Brian"),
                                        new Person("Denver", "Nancy"),
                                        new Person("Denver", "Carl"));

        Map<String, Set<String>> lastNamesByCity
                 = people.collect(groupingBy(Person::getCity,
                                  mapping(Person::getLastName, toSet())));

        assertTrue(lastNamesByCity.get("Des Moines").contains("Nancy"));
    }

    @Test
    public void mappingWIthMethodRefs() throws Exception {
        Stream<Person> foos = Stream.of(new Person("Ryan"), new Person("Dan"));

        String firstName = foos.map(Person::getLastName).findFirst().get();
        assertEquals("Ryan", firstName);
    }



    public class Person {
        private final String city;
        private final String lastName;

        public Person(String city, String lastName){
            this.city = city;
            this.lastName = lastName;
        }

        public Person(String lastName) {
           this("Keokuk", lastName);
        }

        public String getLastName() {
            return lastName;
        }

        public String getCity() {
            return city;
        }
    }
}