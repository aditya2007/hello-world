package com.java8.lamda;

import com.java8.model.Invoice;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by 212590467 on 11/27/16.
 */
public class InvoiceBuilder {

    public static void main(String[] args) {
        /*/Runnable r = () -> System.out.println("Executing Runnable.run() method");
        //new Thread(r).start();
        r.run();

        File dir = new File("/Users/212590467");
        File[] hiddenFilesLE = dir.listFiles(f -> f.isHidden());
        File[] hiddenFilesMR = dir.listFiles(File::isHidden);
        for ( File hf : hiddenFilesMR ) {
            System.out.println(hf.getName());
        }*/

        /*Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("In anther Thread..");
            }
        });*/

       // Thread th = new Thread(() -> System.out.println("In another thread.."));
        //th.start();

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        /*numbers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        }); */

        numbers.forEach(System.out::println);

        int result = 0;
        for( Integer e : numbers ) {
            if( e % 2 == 0 ) {
                result +=  e * 2;
            }
        }
        System.out.println("Imperative :: " + result);

        System.out.println("Declarative->reduce() :: " +
        numbers.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .reduce(0, Integer::sum));

        System.out.println("Declarative->sum() :: " +
        numbers.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e * 2)
                .sum());

    }

}
