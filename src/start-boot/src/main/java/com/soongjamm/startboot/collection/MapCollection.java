package com.soongjamm.startboot.collection;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapCollection {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);

        Set<Map.Entry<String, Integer>> s = map.entrySet();
        Set<String> al = map.keySet();

        // Properties (Hashtable 의 자식이다)
        // 경로 : target/...
        String path = MapCollection.class.getResource("db.properties").getPath();
        Properties dbProperties = new Properties();
        dbProperties.load(new FileReader(path));

        System.out.println(dbProperties.getProperty("username"));
    }
}
