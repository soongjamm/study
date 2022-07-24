package com.soongjamm.startboot.stream.collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Album {
    private final String title;
    private final String artist;
    private final int sales;
}
