package com.example.test_code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Mockito 예제
 */

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
    /**
     * @Mock 만 붙인다고 되진 않는다. null이 되기 때문에..
     * 이걸 처리해줄 익스텐션 필요(클래스 위에다가)
     */
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    // 목 객체를 한 메소드에서만 쓰고싶은데? -> 파라미터로 주면 된다.
    @Test
    // 파라미터에다가 목 객체를 줬다.
    void createStudyService(@Mock StudyRepository studyRepositoryByParam) {


        /**
         *  아래 객체는 만들 수가 없다. 인터페이스만 있어서. Mock을 사용하기 적절한 상황
         */
//        StudyService studyService = new StudyService();

        /**
         * 아래처럼 의존성 주입에 필요한 인터페이스 구현을 모키토가 대신 해주는 것이다.
         */
//        MemberService memberService = new MemberService() {
//            @Override
//            public Optional<Member> findById(Long memberId) {
//                return Optional.empty();
//            }
//        };
//        StudyService studyService= new StudyService(memberService)


        /**
         * 인터페이스만 있던 것들을 Mock으로 생성한 덕분에
         * StudyService에 의존성 주입이 가능해졌다.
         */
        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);
        StudyService studyService2 = new StudyService(memberService, studyRepositoryByParam);
        assertNotNull(studyService);
        assertNotNull(studyService2);

        /**
         * 모든 Mock 객체의 행동
         * - Null을 리턴한다. (Optional 타입은 Optional.empty 리턴)
         * - Primitive 타입은 기본 Primitive 값.
         * - 콜렉션은 비어있는 콜렉션.
         * - Void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않는다.
         */
        Optional<Member> optional = memberService.findById(1L);
        assertNotNull(optional); // Optional.empty

        memberService.validate(2L);


        /**
         * Mock 객체를 생성은 했는데, 이제 행동도 변경(정의)해줄 수 있다.
         * (Stubbing : 목 객체의 행동을 조작하는 것)
         */
        Member member = new Member();
        member.setId(1L);
        member.setEmail("kimst@naver.com");

        // 1이라는 id로 findById를 호출하면 이런 멤버를 리턴했으면 좋겠다.
        // when id = 1로 호출이 되면. thenReturn member 리턴해라.
        /** when(memberService.findById(1L)).thenReturn(Optional.of(member)); */

        // memberId가 중요하기 때문에, ArgumentMatchers를 이용해서 범용적으로 쓸 수도 있다. any()
        when(memberService.findById(any())).thenReturn(Optional.of(member));
        Optional<Member> findById = memberService.findById(1L);
        assertEquals("kimst@naver.com", findById.get().getEmail());

        // 리턴이 아닌, 예외를 던지게 할 수도 있다.
        // findById() id=2로 호출되면 RunTimeException 발생 처리
        when(memberService.findById(2L)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> memberService.findById(2L));

        // 체인형으로 이어서 쓸수도 있다. when을 순서대로 다르게 정의하고 싶을 때.
        when(memberService.findById(3L))
                .thenReturn(findById)
                .thenReturn(null)
                .thenThrow(new RuntimeException());
        Optional<Member> m1 = memberService.findById(3L);
        assertNotNull(m1);
        Optional<Member> m2 = memberService.findById(3L);
        assertNull(m2);
        assertThrows(RuntimeException.class, () -> memberService.findById(3L));

        // 리턴이 void인 경우 doThrow
        // 이 다음부터는 조건에 만족한 경우 예외를 던진다.
        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });
        memberService.validate(2L);

        Study study = new Study(10, "java");
    }

    @Test
    void mock_stubbing_practice(@Mock MemberService memberService,
                                @Mock StudyRepository studyRepository) {

        Member member = new Member();
        member.setId(1);
        member.setEmail("soong@email.com");

        Study study = new Study(10, "테스트");

        // TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        Optional<Member> findById = memberService.findById(1L);
        assertEquals("soong@email.com", findById.get().getEmail());

        // TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study)).thenReturn(study);
        Study save = studyRepository.save(study);
        assertEquals("테스트", save.getName());

        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createNewStudy(1L, study);

        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }
}