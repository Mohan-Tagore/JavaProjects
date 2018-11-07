package challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaDemo {
    public static void main(String[] args) {
//        function();
//        supplier();
        stream();
    }

    private static void anClass(){
        Runnable runnable = () -> {
            String myString = "Let's split this into an array";
            String[] parts = myString.split(" ");

            for(String part: parts){
                System.out.println(part);
            }
        };
    }

    private static void function(){

        Function<String, String> everySecondChar = source -> {
          StringBuilder returnVal = new StringBuilder();
          for(int i =0; i<source.length();i++){
              if (i%2 == 1){
                  returnVal.append(source.charAt(i));
              }
          }
          return returnVal.toString();
        };

        System.out.println(everySecondCharacter(everySecondChar, "1234567890"));
    }

    private static String everySecondCharacter(Function<String, String> fn, String arg){
        return fn.apply(arg);
    }

    private static void supplier(){
        Supplier<String> iLovejava = () -> "I Love Java";

        String supplierResult = iLovejava.get();
        System.out.println(supplierResult);
    }

    private static void stream(){
        List<String> topNames2015 = Arrays.asList("Amelia", "Olivia", "emily",
                "Isla", "Ava", "oliver", "Jack", "Charlie", "harry", "Jacob");

        topNames2015.stream()
                .map((source) -> {
                    return source.substring(0, 1).toUpperCase() + source.substring(1);
                }).sorted()
                .forEach(System.out::println);

        List<String> sortedNames = new ArrayList<>();

        topNames2015.forEach(s-> {
                sortedNames.add(s.substring(0,1).toUpperCase() + s.substring(1));
        });

//        sortedNames.sort((s1, s2) -> s1.compareTo(s2));
//        sortedNames.forEach(s-> System.out.println(s));
        sortedNames.sort(String::compareTo);
        sortedNames.forEach(System.out::println);

        long count = topNames2015.stream()
                .map((source) -> {
                    return source.substring(0, 1).toUpperCase() + source.substring(1);
                }).filter(name->name.startsWith("A"))
                .count();
        System.out.println(count);

        topNames2015.stream()
                .map((source) -> {
                    return source.substring(0, 1).toUpperCase() + source.substring(1);
                }).peek(System.out::println).sorted().count();

    }

}
