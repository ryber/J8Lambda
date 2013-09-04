package examples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

class StreamsExample {
    static int counter = 0;

    public static void main(String[] args) {
        counter = 0;
        Stream<Integer> numbers = Stream.generate(() -> counter++);


        numbers.parallel().forEach(StreamsExample::fizzBuzz);
    }

    public static void fizzBuzz(int i) {
        if (i % 15 == 0) {
            appendTo("c:\\logs\\fizz.txt", i);
            appendTo("c:\\logs\\buzz.txt", i);
        } else if (i % 3 == 0) {
            appendTo("c:\\logs\\fizz.txt", i);
        } else if (i % 5 == 0) {
            appendTo("c:\\logs\\buzz.txt", i);
        } else {
            System.out.println(String.valueOf(i));
        }
    }

    public static void appendFizz(int i) {

    }

    public static void appendTo(String file, int i){
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            out.println(i);
        }catch (IOException e) {
            System.out.println("Help! = " + e);
        }
    }
}