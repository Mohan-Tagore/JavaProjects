package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class LambdaTest {
    static List<Integer> numbers = new ArrayList<>();

    static {
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
    }

    public static void main(String[] args) {
//        consumer();
//        predicate();
//        suppliers();
        functions();
//        unary();
    }


    private static void consumer() {
        //accept method....takes in generics as input
        numbers.forEach(i -> System.out.println(i));
        numbers.forEach(System.out::println);

        Consumer<String> c1 = s -> System.out.println(s);
        Consumer<String> c2 = s-> System.out.println("Hello World!");

        c1.andThen(c2).accept("Tim");
    }

    private static void predicate() {
        //accepts generics as input.........test method needs condition to validate
        //test method
        IntPredicate nGT15 = i -> i > 15;
        IntPredicate nLS100 = i -> i < 100;
        System.out.println(nGT15.test(16));
        System.out.println(nLS100.test(101));
        System.out.println(nGT15.and(nLS100).test(90));

        Predicate<Employee> youngEmp = e -> e.getAge()<20;
        Random random = new Random();
        Supplier<Employee> employees = () -> {int temp = random.nextInt(5);
        return new Employee("Emp_" + temp, temp, random.nextInt(50));};
        for(int i=0; i<5;i++){
            Employee e = employees.get();
            if(youngEmp.test(e)){
                System.out.println("Young: " + e + "Age " + e.getAge());
            }else{
                System.out.println("Old " + e + "Age " + e.getAge());
            }
        }
    }

    private static void suppliers() {


        Random random = new Random();
        //Accepts type specific object for reference.....takes no arguments..Supplies same object
        Supplier<Employee> employees = () -> {
            int temp = random.nextInt(10);
            return new Employee("Emp" + temp, temp, random.nextInt(30));
        };

        for (int i = 0; i < 5; i++) {
            System.out.println(employees.get());
        }

    }

    private static void functions() {
        Function<Employee, Integer> getIds = e-> e.getId();
        Random random = new Random();
        Supplier<Employee> employees =  () ->{ int temp = random.nextInt(10);
            return new Employee("Emp_" + temp, temp, random.nextInt(30));};
        Function<Employee, String> getUpper = e -> e.getName().toUpperCase();
        Function<String, String> getFirstName = name -> name.substring(0, name.indexOf("_"));
        Function chainedFunction = getUpper.andThen(getFirstName);
        BiFunction<String, Employee, String> concatAge = (name, emp) -> name.concat(" " + emp.getAge());
        for (int i =0; i<5; i++){
            Employee e = employees.get();
            //System.out.print(e);
            System.out.print("ID: " + getIds.apply(e));
            System.out.print(", FirstName " + chainedFunction.apply(e));
            System.out.println(", Age: " + concatAge.apply((String)chainedFunction.apply(e), e));
        }

        Function<String, String> upperCase = String::toUpperCase;
        System.out.println(upperCase.apply("abcd"));

        Function<Employee, Integer> getAge = Employee::getAge;
        Employee e = new Employee("Mohan", 100, 25);
        System.out.println(getAge.apply(e));


    }

    private static void unary(){
        IntUnaryOperator incBy5 = i -> i+5;
        System.out.println(incBy5.applyAsInt(10));

    }

    private static class Employee {
        private String name;
        private int id;
        private int age;

        public Employee(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}