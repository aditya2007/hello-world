package com.java8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import com.java8.model.Person;
import com.java8.model.Transaction;

/**
 * http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
 * http://www.oracle.com/technetwork/articles/java/architect-streams-pt2-2227132.html
 *
 * Created by Yogananda Gowda - 212590467 on 6/26/17.
 */
public class StreamsApp {

    public static void main(String[] args) throws Exception{
        StreamsApp lm = new StreamsApp();
        //lm.covertListToMap();
        //lm.streams();
        //lm.twoEvenSqures();
        //lm.findingAndMatching();
        //lm.findFirstAndAny();
        //lm.reduce();
        //lm.range();
        //lm.biuldingStreams();
        lm.infiniteStreams();
    }

    private void twoEvenSqures() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 6,7, 8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                        .filter(n -> {
                            System.out.println("filtering " + n);
                            return n % 2 == 0;
                        })
                        .map(n -> {
                            System.out.println("mapping " + n);
                            return n * n;
                        })
                        .limit(2)
                        //.skip(2)
                        //.distinct()
                        .collect(Collectors.toList());
        System.out.println(twoEvenSquares);
    }

    private void streams() {
        List<Transaction> transactions = getTransactions();
        List<Integer> transactionIds8 =
                transactions.parallelStream()
                        .filter(t -> t.getType() == Transaction.Type.GROCERY)
                        .sorted(Comparator.comparing(Transaction::getValue).reversed())
                        .map(Transaction::getId)
                        .collect(Collectors.toList());
        System.out.println("Java 8 ******** " + transactionIds8);
    }

    private void findingAndMatching() {
        List<Transaction> transactions = getTransactions();
        boolean expensive = transactions.stream().anyMatch(t -> t.getValue() > 500);
        System.out.println("Do we have an expensive transaction >> " + expensive);
    }

    private void findFirstAndAny() {
        List<Transaction> transactions = getTransactions();
        //Optional<Transaction> optional =
                transactions.stream()
                        .filter(t -> t.getType() == Transaction.Type.GROCERY)
                        .findAny().ifPresent(t -> System.out.println(t.getValue()));
    }

    private void reduce() {
        List<Integer> numbers = Arrays.asList(4,7,9,2,5);
        //int sum = numbers.stream().reduce(0, (x,y) -> x + y);
        int sum = numbers.stream().mapToInt(n -> n).sum();
        System.out.println("Total sum >>>> " + sum);
        int product = numbers.stream().reduce(1, (x, y) -> x + y);
        System.out.println("Total product >>>> " + product);
        int max = numbers.stream().reduce(1, Integer::max);
        System.out.println("Max of Integer >>> " + max);

        //Use mapToInt or mapToDouble or mapToLong, instead of map to avoid auto boxing and gain performance
        List<Transaction> transactions = getTransactions();
        double mapDoubleSum = transactions.stream().mapToDouble(Transaction::getValue).sum();
        System.out.println("Map to Double >>>>> " + mapDoubleSum);
    }

    private void range() {
        IntStream.rangeClosed(10, 31).filter(n -> n % 2 == 1).forEach(System.out::println);
    }

    private void biuldingStreams() throws IOException {
        Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);
        IntStream numbersFromArray = Arrays.stream(new int[]{1,2,3,4});

        //You can also convert a file in a stream of lines using the Files.lines static method.
        long numOfLines = Files.lines(Paths.get("/Users/212590467/work/core-java/pom.xml"), Charset.defaultCharset()).count();
        System.out.println("Number of lines in pom.xml >>>> " + numOfLines);
    }

    /**
     * There are two static methods—Stream.iterate and Stream.generate
     * —that let you create a stream from a function.
     * However, because elements are calculated on demand,
     * these two operations can produce elements “forever.”
     * This is what we call an infinite stream: a stream that doesn't’t have a fixed size,
     * as a stream does when we create it from a fixed collection.
     */
    private void infiniteStreams() {
        //an example that uses iterate to create a stream of all numbers that are multiples of 10
        Stream<Integer> numbers = Stream.iterate(0, n -> n + 10);
        numbers.limit(10).forEach(System.out::println);
    }


    private List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setId(101);
        transaction.setType(Transaction.Type.GROCERY);
        transaction.setValue(444.44);
        transactions.add(transaction);

        Transaction transaction1 = new Transaction();
        transaction1.setId(102);
        transaction1.setType(Transaction.Type.MERCHANDISE);
        transaction1.setValue(5555.44);
        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setId(103);
        transaction2.setType(Transaction.Type.GROCERY);
        transaction2.setValue(345.00);
        transactions.add(transaction2);

        Transaction transaction3 = new Transaction();
        transaction3.setId(104);
        transaction3.setType(Transaction.Type.GIFTS);
        transaction3.setValue(1450.00);
        transactions.add(transaction3);

        Transaction transaction4 = new Transaction();
        transaction4.setId(105);
        transaction4.setType(Transaction.Type.GROCERY);
        transaction4.setValue(600.00);
        transactions.add(transaction4);

        return transactions;
    }

    private void covertListToMap() {
        //List to Map Example
        /*List<String> list = new ArrayList<>();
        list.add("Mohan");
        list.add("Sohan");
        list.add("Mahesh");
        Map<String, Object> map = list.stream().collect(Collectors.toMap(Function.identity(), (x, y) -> x+", "+ y)));
        map.forEach((x, y) -> System.out.println("Key: " + x + ", value: " + y));*/

        List<Person> list = new ArrayList<>();
        list.add(new Person(100, "Mohan"));
        list.add(new Person(200, "Sohan"));
        list.add(new Person(300, "Mahesh"));
        Map<Integer, Person> map = list.stream()
                .collect(Collectors.toMap(Person::getId, Function.identity()));
        map.forEach((x, y) -> System.out.println("Key: " + x + ", value: "+ y));
    }

}
