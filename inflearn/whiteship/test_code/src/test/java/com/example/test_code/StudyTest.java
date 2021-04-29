package com.example.test_code;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @EnabledOnOs(OS.MAC)
    void 조건에_따라_assumingTrue() {
        assumingThat(1==2, () -> System.out.println("이건 실행 안함"));
        assumingThat(2==2, () -> System.out.println("이건 실행 함"));
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void 조건에_따라_assumeTrue() {
        assertEquals(1, 1);
        System.out.println("assumeTrue 테스트 실행"); // 여기까진 실행.

        String test_env = System.getenv("TEST_ENV");
        assumeTrue("ME".equalsIgnoreCase(test_env));
    }

    @Test
    @DisplayName("스터디 만들기👉🏻❤️")
    void create_new_study() {
        Study study = new Study(-10);

        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
        );

    }

//    @Test
//    @Disabled
//    void create_test_again() {
//        System.out.println("create1");
//    }
//
//    @BeforeAll
//    static void beforeAll() {
//        System.out.println("beforeAll");
//    }
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("afterAll");
//    }
//
//    @BeforeEach
//    void beforeEach() {
//        System.out.println("beforeEach");
//    }
//
//    @AfterEach
//    void afterEach() {
//        System.out.println("afterEach");
//    }
}