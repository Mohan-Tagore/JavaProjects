package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  static List<String> bingoNumbers = Arrays.asList(
            "N40", "N36", "B12", "B06", "G53", "G49",
            "G60", "G50", "g64", "I26", "I17", "I29", "O71");
  static List<Department> departments = new ArrayList<>();

  static {
      Employee mohan =  new Employee("Mohan Tagore", 25);
      Employee mukundha =  new Employee("Mukundha Rajendran", 18);
      Employee dhana =  new Employee("Dhana Rajendran", 44);
      Employee rajendran =  new Employee("Rajendran K", 55);

      Department hr = new Department("Human Resources");
      hr.addEmployee(mohan);
      hr.addEmployee(mukundha);
      hr.addEmployee(rajendran);

      Department accounting = new Department("Accounting");
      hr.addEmployee(dhana);

      departments.add(hr);
      departments.add(accounting);

  }
    public static void main(String[] args) {
//        sortGnum();
//        streams();
//        flatMap();
//        collect();
        reduce();
    }

    private static void sortGnum(){
        List<String> gNumbers = new ArrayList<>();

        bingoNumbers.forEach(number -> {
            if(number.toUpperCase().startsWith("G")){
                gNumbers.add(number);
//                System.out.println(number);
            }
        });
        gNumbers.sort((s1, s2) -> s1.compareTo(s2));
        gNumbers.forEach(n -> System.out.println(n));


    }

    private static void streams(){
        bingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);
    }

    private static void flatMap(){

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);
    }

    private static void collect(){
        List<String> gNumbers = bingoNumbers.stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());

        List<String> gNumbers1 = bingoNumbers.stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        gNumbers.forEach(System.out::println);
        gNumbers1.forEach(System.out::println);

        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        System.out.println(groupedByAge);
    }

    private static void reduce(){
      departments.stream()
              .flatMap(department -> department.getEmployees().stream())
              .reduce((e1,e2) -> e1.getAge()<e2.getAge()?e1:e2)
              .ifPresent(System.out::println);
    }

}
