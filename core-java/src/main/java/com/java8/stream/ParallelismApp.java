package com.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by Yogananda Gowda - 212590467 on 6/19/17.
 */
public class ParallelismApp {

    public static void main(String... args) {

        // Create sample data

        List<Person> roster = Person.createRoster();

        System.out.println("Contents of roster:");
        roster
                .stream()
                .forEach(p -> p.printPerson());
        System.out.println();

        // 1. Average age of male members in parallel

        double average = roster
                .parallelStream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        System.out.println("Average age of male members in parallel: " +
                average);

        // 2. Concurrent reduction example

        ConcurrentMap<Person.Sex, List<Person>>
                byGenderParallel =
                roster
                        .parallelStream()
                        .collect(Collectors.groupingByConcurrent(Person::getGender));

        List<Map.Entry<Person.Sex, List<Person>>>
                byGenderList =
                new ArrayList<>(byGenderParallel.entrySet());

        System.out.println("Group members by gender:");
        byGenderList
                .stream()
                .forEach(e -> {
                    System.out.println("Gender: " + e.getKey());
                    e.getValue()
                            .stream()
                            .map(Person::getName)
                            .forEach(f -> System.out.println(f)); });

        // 3. Examples of ordering and parallelism

        System.out.println("Examples of ordering and parallelism:");
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(intArray));

        System.out.println("listOfIntegers:");
        listOfIntegers
                .stream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("listOfIntegers sorted in reverse order:");
        Comparator<Integer> normal = Integer::compare;
        Comparator<Integer> reversed = normal.reversed();
        Collections.sort(listOfIntegers, reversed);
        listOfIntegers
                .stream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Parallel stream");
        listOfIntegers
                .parallelStream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Another parallel stream:");
        listOfIntegers
                .parallelStream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("With forEachOrdered:");
        listOfIntegers
                .parallelStream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        // 4. Example of interference

        try {
            List<String> listOfStrings =
                    new ArrayList<>(Arrays.asList("one", "two"));

            // This will fail as the peek operation will attempt to add the
            // string "three" to the source after the terminal operation has
            // commenced.

            String concatenatedString = listOfStrings
                    .stream()

                    // Don't do this! Interference occurs here.
                    .peek(s -> listOfStrings.add("three"))

                    .reduce((a, b) -> a + " " + b)
                    .get();

            System.out.println("Concatenated string: " + concatenatedString);

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.toString());
        }

        // 5. Stateful lambda expressions examples

        List<Integer> serialStorage = new ArrayList<>();

        System.out.println("Serial stream:");
        listOfIntegers
                .stream()

                // Don't do this! It uses a stateful lambda expression.
                .map(e -> { serialStorage.add(e); return e; })

                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        serialStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Parallel stream:");
        List<Integer> parallelStorage = Collections.synchronizedList(
                new ArrayList<>());
        listOfIntegers
                .parallelStream()

                // Don't do this! It uses a stateful lambda expression.
                .map(e -> { parallelStorage.add(e); return e; })

                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");
    }

    public static class Person {
        protected enum Sex {
            MALE,
            FEMALE;
        }

        private String name;
        private Integer age;
        private Sex gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public Sex getGender() {
            return gender;
        }

        public void setGender(Sex gender) {
            this.gender = gender;
        }

        public void setAge(Integer age) {
            this.age = age;
        }



        protected static List<Person> createRoster() {
            List<Person> persons = new ArrayList<>();
            Person person1 = new Person();
            person1.setName("Roster1");
            person1.setAge(34);
            person1.setGender(Sex.MALE);
            Person person2 = new Person();
            person2.setName("Roster2");
            person2.setAge(24);
            person2.setGender(Sex.MALE);
            Person person3 = new Person();
            person3.setName("Roster3");
            person3.setAge(30);
            person3.setGender(Sex.MALE);

            persons.add(person1);
            persons.add(person2);
            persons.add(person3);

            Person person4 = new Person();
            person4.setName("Hen1");
            person4.setAge(28);
            person4.setGender(Sex.FEMALE);
            Person person5 = new Person();
            person5.setName("Hen2");
            person5.setAge(20);
            person5.setGender(Sex.FEMALE);
            Person person6 = new Person();
            person6.setName("Hen3");
            person6.setAge(22);
            person6.setGender(Sex.FEMALE);

            persons.add(person4);
            persons.add(person5);
            persons.add(person6);

            return  persons;
        }

        protected void printPerson() {
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender +
                    '}';
        }
    }
}
