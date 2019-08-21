package feature18.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhegong
 **/
public class StreamCollection {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

// "aaa2", "aaa1"

        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

// "aaa1", "aaa2"

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);
        //ccc
        String shortStr = stringCollection.stream()
                .min(Comparator.comparing(a -> a.length()))
                .get();

        System.out.println("shortStr" +shortStr);


// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

        System.out.println(stringCollection);

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true


        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);    // 3


        Optional<String> reduced =
                new ArrayList<String>()
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

        System.out.println(reduced.isPresent()?reduced.get():"empty values");


        //count=6
        int count  = Stream.of(1, 2, 3)
                .reduce((s1, s2)-> s1+s2).get();

        System.out.println(count);

        //[1,2,3]
        System.out.println(Stream.of("1","2","3").
                collect(Collectors.joining(",", "[", "]")));


    }
}

//    public Set<String> findLongTracks(List<Album> albums) {
//        Set<String> trackNames = new HashSet<>();
//        for(Album album : albums) {
//            for (Track track : album.getTrackList()) {
//                if (track.getLength() > 60) {
//                    String name = track.getName();
//                    trackNames.add(name);
//                }
//            }
//        }
//        return trackNames;
//    }
/** 使用Lambda简化代码效果*/
//    public Set<String> findLongTracks(List<Album> albums) {
//        return albums.stream()
//                .flatMap(album -> album.getTracks())
//                .filter(track -> track.getLength() > 60)
//                .map(track -> track.getName())
//                .collect(toSet());
//    }


//summaryStatistics()  min max sum average


//Optional emptyOptional = Optional.empty();
//    Optional alsoEmpty = Optional.ofNullable(null);
//    assertFalse(emptyOptional.isPresent());

/**在optional为空时调用 orElse orElseGet*/
//assertEquals("b", emptyOptional.orElse("b"));
//        assertEquals("c", emptyOptional.orElseGet(() -> "c"));

//stream.collect(toCollection(TreeSet::new));