package com.soongjamm.startboot.objects_practice.chapter5.responsibility;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        Movie movie = new NoneDiscountMovie("titanic", Duration.of(30L, ChronoUnit.MINUTES), Money.ZERO);
        // 바꾸려면 새로 인스턴스 생성하고 movie에 할당.
        // 해결 방법은 합성을 사용
        // 챕터 2와 같아진다.
    }
}
