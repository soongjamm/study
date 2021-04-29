package chap05.config;

import chap05.spring.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


/**
 * ComponentScan의 속성 - basePackages와 excludeFilters에 대해
 * 그 중 excludeFilters 속성에 @Filter 어노테이션의 속성값들에 대해 공
 */
@Configuration
@ComponentScan(basePackages = {"chap05/spring", "chap05/conflict"},
//excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {NoProduct.class}))
//excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MemberDao.class}))
//excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "spring.*Dao")) // aspectJ 사용
//excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "..*Dao")) // 정규표현식 사용
        excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "..*Dao"),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = NoProduct.class
                )}) // 설정할 필터가 2개 이상일 때
public class AppCtx {
    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

    /**
     * MemberDao 클래스에 @Component 어노테이션이 붙어있다.
     * 그리고 이 설정 클래스에 수동으로 등록된 memberDao가 있다.
     * 이 경우 수동 등록된 빈이 우선한다. (@Component로 스캔된 빈은 무시된다)
     * 그래서 이 경우도 이름을 바꿔서 해결한다.
     * 빈이 2개가 생성되므로 빈을 주입받는 쪽에서 @Qualifier 어노테이션으로 알맞는 빈을 선택하도록 한다.
     */
    @Bean
    public MemberDao memberDao2() {
        return new MemberDao();
    }

    /**
     * @Component를 붙인 클래스들은 설정 코드 불필요
     */
//    @Bean
//    public MemberRegisterService memberRegSvc() {
//        return new MemberRegisterService();
//    }
//
//    @Bean
//    public ChangePasswordService changePwdSvc() {
//        ChangePasswordService pwdSvc = new ChangePasswordService();
//        return pwdSvc;
//    }
//
//    @Bean
//    public MemberListPrinter listPrinter() {
//        return new MemberListPrinter();
//    }
//
//    @Bean
//    public MemberInfoPrinter infoPrinter() {
//        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
//        infoPrinter.setPrinter(memberPrinter2());
//        return infoPrinter;
//    }
}
