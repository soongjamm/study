package com.soongjamm.startboot.stream.collectors;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.*;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.stream.Collectors.*;


public class Main {
    public static void main(String[] args) {
        List<Album> albums = List.of(
                new Album("G N' R Lies", "Guns N' Roses", 5_000_000),
                new Album("Use Your Illusion I", "Guns N' Roses", 7_000_000),  // - 1
                new Album("The Spaghetti Incident?", "Guns N' Roses", 1_000_000),
                new Album("Gold Cobra", "Limp Bizkit", 63_000),
                new Album("Results May Vary", "Limp Bizkit", 1_337_356),
                new Album("Chocolate Starfish and the Hot Dog Flavored Water", "Limp Bizkit", 8_000_000), // - 1
                new Album("Beauty Behind the Madness", "The Weekend", 1_230_000),  // - 1
                new Album("Starboy", "The Weekend", 484_000));

        /*------------------------toMap()---------------------------------*/
        // (Artist 이름 : Album 정보)
        Map<String, Album> topHitsAlbum = albums.stream().collect(
                toMap(
                        Album::getArtist,
                        album -> album,
                        maxBy(comparingInt(Album::getSales))
                ) // Album 끼리 판매량을 비교해서 가장 판매량이 많은 앨범을 선택
        );

        // (Artist 이름 : 최다 판매 앨범의 판매수)
        // key는 artist 이름으로 하고, value를 구하기 위해서 getSales 값을 3번째 인자(BinaryOperator)로 넘기면 해당 함수를 실행한 결과가 value가 된다.
        Map<String, Integer> topHitsAlbumsSell = albums.stream().collect(
                toMap(
                        Album::getArtist,
                        Album::getSales,
                        maxBy(comparingInt(value -> value))
                )
        );
        /*----------------------------------------------------------------*/

        /*------------------------groupingBy()----------------------------*/
        // toMap() 은 하나의 value 만 추출해냈고, gorupingBy() 는 여러개의 값을 가지고 무언가 하거나 Collection 을 만든다.
        Map<String, List<Album>> collectByArtist = albums.stream().collect(
                groupingBy(Album::getArtist)
        );

        Map<String, Set<Album>> collectByArtistToMap = albums.stream().collect(
                groupingBy(Album::getArtist, toSet())
        );

        Map<String, Long> countAlbumsByArtist = albums.stream().collect(
                groupingBy(Album::getArtist, counting())
        );
        // counting() 수집기를 collect(counting()) 형태로 사용할 일은 없다. stream.count() 메서드가 있으니까.
        /*----------------------------------------------------------------*/

    }
}
