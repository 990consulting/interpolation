/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/10/15.
 */
public class Sandbox {

    public static void main(String[] args) {

//        List<String> strList = IntStream.range(0, 3)
//                .mapToObj(x -> Stream.of("a", "b", "c").map(c -> {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append(c);
//                    sb.append(x);
//                    return sb.toString();
//                }).collect(Collectors.toList()))
//                .flatMap(list -> list.stream())
//                .collect(Collectors.toList());

        List<String> strList = IntStream.range(0, 3)
                .mapToObj(x -> Stream.of("a", "b", "c").map(c -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    sb.append(x);
                    return sb.toString();
                }))
                .flatMap(subStream -> subStream.map(str -> str))
                .collect(Collectors.toList());

        for (String str : strList) {
            System.out.println(str);
        }
    }
}
