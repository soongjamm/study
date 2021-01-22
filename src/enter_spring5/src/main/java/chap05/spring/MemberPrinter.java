package chap05.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {

//    @Autowired(required = false)
//    private DateTimeFormatter dateTimeFormatter;

//    @Autowired
//    private Optional<DateTimeFormatter> dateTimeFormatterOptional;

    @Autowired
    @Nullable
    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초");
    }

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
        } else {
            System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

    /**
     * required 옵션을 사용해서 자동 주입을 받지 않을 수 있다.
     */
//    @Autowired(required = false)
//    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
//        this.dateTimeFormatter = dateTimeFormatter;

    /**
     * required 옵션 대신 파라미터 타입에 Optional 사용.
     * 파라미터에 Optional 쓰는건 안티패턴이므로 사용하지 않는다.
     * 이 책에선 왜 이걸 말해주지 않았는지 의문.... 맘에 들지 않는군.
     * 참고로 Optional은 리턴타입에만 사용하는게 좋으니 까먹지 말자.
     */
//    @Autowired
//    public void setDateTimeFormatter(Optional<DateTimeFormatter> dateTimeFormatter) {
//
//        this.dateTimeFormatter = dateTimeFormatter.orElseGet(null); // NullPointerExcepption 발생
//    }

    /**
     * @Nullable 어노테이션 사용
     * @Autowired의 required옵션과 다른점은 @Nullable은 자동 주입할 빈이 존재하지 않아도 메서드가 호출된다.
     * 반면 required=false 옵션은 자동 주입할 빈이 존재하지 않으면 세터 메서드를 호출하지 않는다.
     */
//    @Autowired
//    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//        this.dateTimeFormatter = dateTimeFormatter;
//    }

}
