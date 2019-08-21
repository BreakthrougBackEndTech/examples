package feature18.lambda;

import java.util.*;

/**
 * @author: zhegong
 **/
public class SortTest {

/**    Lambda 表达式是
    􀅖 一个匿名方法，将行为像数据一样进行传递。
    􀅖 Lambda表达式的常见结构：BinaryOperator<Integer> add = (x, y) → x + y。
    􀅖 函数接口指仅具有 单个抽象方法 的接口，用来表示 Lambda表达式的类*/
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

        System.out.println(names);


        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        System.out.println(names);

        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);


        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123


        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        Person p3 = new Person("Blice", "Wonderland");

        List<Person> listPerson = new ArrayList<>();
        listPerson.add(p1);
        listPerson.add(p2);
        listPerson.add(p3);

        Collections.sort(listPerson, comparator);

        System.out.println(listPerson);

        Predicate<Integer> atLest5 = x -> x > 5;

        System.out.println(atLest5.test(5));

    }

    interface Converter<F, T> {
        T convert(F from);
    }

    interface Predicate<T> {
        boolean test(T t);
    }

    static class Person {
        String firstName;
        String lastName;

        Person() {
        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }
}
