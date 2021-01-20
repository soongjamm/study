# Date와 Time
기존에 `SimpleDateFormat`, `Calandar`, `Date` 를 썼는데 불편함이 있었다.

#### Date는 Date지만 timestamp의 기능을 한다.
- 무슨말이냐면.. Date인데 date.getTime()을 한다. 즉 클래스명이 명확하지 않다.
    - Date의 getTime()은 Long이 나오고 Calandar의 getTime()은  Date가 나오고.. 
- 시간이 우리가 아는 그 시간이 아니라, 1970년 1월 1일 0시 0분 0초 (EPOCK)을 기준으로 현재까지의 시간을 ms로 리턴해준다. 

#### mutable하다. (객체의 상태를 바꿀 수 있다) 
- 멀티쓰레드 환경에서 안전하게 쓰기 어렵다는 뜻이다.
- `.setTime()`이 된다.
    - Synchronized를 쓰면 괜찮은데, 그냥 그대로 mutable하게 쓰면 문제가 된다.

#### 버그발생의 여지가 많다. 
- month가 0부터 시작해서 헷갈리게 되어있다.
    - 그래서 상수를 써야 한다.
- 타입 안정성이 없다.
> type safety가 없다는 말은?   
> 아무값이나 들어올수 있다는 뜻. (-100이 들어올수도 있고..)
> 타입안정성이 있는건 Month라는 타입을 받아서 그 타입만 받게 하는 것이다.

#### 그래서 보통 joda time 이라는 것을 썼다. 
- 표준화되어 java.time 패키지에 들어왔다.
- Immutable하다. 날짜수정 메소드를 쓰면 새로운 객체가 만들어진다.


## Date와 Time API
> #### 간략하게 요약 
> - 크게 API는 사람용 / 기계용 시간으로 나누어져있다.
>     - 기존 `Date`에서 꺼내오는 getTime()은 Long 타입으로 기계용 시간이다. (메소드 실행이 얼마나 걸리는지 등으 측정할 때 사용)
>     - 사람용은 `LocalDateTime` 이 있다.
> - 기간은 `Duration`(시간기반. 398초동안 나가서 놀자)/ `Period`(날짜기반. 몇 년동안~)
> - DateTimeFormatter를 사용해 일시를 특정한 문자열로 포매팅 가능하다.

# 자세한 내용은 필기자료를 보며 연습하는 것이 나을것 같다.


```java
package java8.date_time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {
    public static void main(String[] args) {

        // 현재 순간을 기계 시간으로 표현
        // Instant
        Instant instantNow = Instant.now(); // UTC(GMT)를 리턴
        System.out.println(instantNow);
        System.out.printf("Instant.now(): %s\n", instantNow.atZone(ZoneId.of("America/Los_Angeles"))); // ZoneId

        // ZonedDateTime
        ZonedDateTime zonedDateTime = instantNow.atZone(ZoneId.systemDefault()); // instantNow에 저장된 시간을 현재 로컬 시스템의 시간으로 가져온다
        System.out.printf("ZonedDateTime: %s\n", zonedDateTime);


        System.out.println();
        // 인류용 일시를 표현
        // LocalDateTime
        LocalDateTime localDateTimeNow = LocalDateTime.now(); // 현재 시스템 Zone에 해당하는 (로컬)턴 일시를 리턴. (`Local`DateTime 이니까.)
        LocalDateTime localDateTimeMyBirthday = LocalDateTime.of(2021, Month.MARCH, 03, 0, 0, 0);// 특정한 일시를 반환한다. year/month/day/hour/min/sec 을 입력해서 LocalDateTime 객체를 생성한다.
        ZonedDateTime bdayInHochimin = ZonedDateTime.of(localDateTimeMyBirthday, ZoneId.of("Asia/Ho_Chi_Minh")); // ZoneId 는 https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
        System.out.printf("local: %s\nhochimin: %s\n", localDateTimeMyBirthday, bdayInHochimin);

        System.out.println();

        // Period (사용자용에 가깝다.)
        LocalDate localDateToday = LocalDate.now();
        LocalDate localDateBirthday = LocalDate.of(2021, Month.MARCH, 03);
        Period between = Period.between(localDateToday, localDateBirthday);
        Period until = localDateToday.until(localDateBirthday);
        System.out.printf("between : %s\n", between); // Period는 30일이 넘어가면 Month로 저장한다. Year Month Day로 관리
        System.out.printf("until : %s\n", until); // LocalDate의 until() 메소드로 생성된 Period
        System.out.printf("ChrinoUnit으로 기간을 day로만 얻기 : %s", ChronoUnit.DAYS.between(localDateToday, localDateBirthday));

        System.out.println();

        // Duration (기계용에 가깝다.)
        Duration days3 = Duration.ofDays(3);  // 3일을 시간으로 변환해준다.
        System.out.println(days3);

        System.out.println();

        // Formatting
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // 왜 MM이 대문자인지는 https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html 에서 Date and Time Patterns 참고.
        String format = localDateTimeNow.format(MMddyyyy);
        System.out.printf("사용자정의 DateTimeFormatter : %s\n", localDateTimeNow.format(MMddyyyy)); // String 타입 리턴
        System.out.printf("BASIC_ISO_DATE : %s\n", localDateTimeNow.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.printf("ofLocalzedDate() : %s\n", localDateTimeNow.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))); // FormatStyle.FULL - 한글+요일까지 나옴

        System.out.println();

        // Parsing
        LocalDate parsed1 = LocalDate.parse("12/30/2020", MMddyyyy); // 포맷터와 형식이 일치해야한다. 미리 정의해둔 포맷 https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
        LocalDate parsed2 = LocalDate.parse("20201230", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate parsed3 = LocalDate.parse("2021년 1월 20일 수요일", DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.printf("parsed1 : %s\nparsed2 : %s\nparsed3 : %s\n ", parsed1, parsed2, parsed3);

        System.out.println();

        // 레거시 API 지원 (예전 API와 호환)
        // Date 타입이나 GregorianCalendar 타입의 인스턴스를 Instant나 ZonedDateTime으로 상호 변환 가능 
        // Date <-> Instant
        // GregorianCalendar <-> ZonedDateTime
        // java.util.TimeZone에서 java.time.ZoneId로 상호 변환 가능
        Date date= new Date();
        Instant instantFromDate = date.toInstant(); // Date -> Instant
        Date fromInstant = Date.from(instantFromDate); // Instant -> Date
//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime zonedDateTimefromGregorian = gregorianCalendar.toZonedDateTime(); // GregorianCalendar -> ZonedDateTime
        GregorianCalendar fromZoneDateTime = GregorianCalendar.from(zonedDateTimefromGregorian); // ZonedDateTime -> GregorianCalendar

        LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.printf("GregorianCalendar -> LocalDateTime : %s",dateTime);

        /////////

        ZonedDateTime toZonedDateTime = GregorianCalendar.from(zonedDateTimefromGregorian).toInstant().atZone(ZoneId.of("Asia/Seoul"));
        LocalDateTime toLocalDateTime = toZonedDateTime.toLocalDateTime();
        toLocalDateTime.atZone(ZoneId.of("America/Los_Angeles"));

        GregorianCalendar.from(ZonedDateTime.now());
        Date.from(Instant.now());

    }
}
```