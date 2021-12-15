package globalException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 한 컨트롤러 내에서 예외처리는 해당 클래스에서 @ExceptionHandler를 붙인 메소드를 작성하면 되었다.
 * 그런데 만약 여러 컨트롤러에서 해당 예외 처리가 필요하다면? 또 중복이 발생한다.
 * 그래서 글로벌 범위로 예외처리를 정의할 수 있다.
 *
 * @ControllerAdvice() 파라미터로는 패키지이름을 준다. 그럼 그 하위에 클래스들이 대상이 된다. (속성 : value,  속성명 적지 않으면 자동으로 value가 됌.)
 * 그 외에  annotations, assignableTypes 등이 있다.
 * 대상 클래스들은 반드시 빈으로 등록되어 있어야 한다.
 *
 * 우선순위는 컨트롤러 범위 > 글로벌 범위다.
 * 같은 컨트롤러에 위치한 @ExceptionHandler 메소드를 먼저 찾고, 없으면 @ControllerAdvice 클래스에서 @ExceptionHandler 메소드를 찾는다.
 */


@ControllerAdvice(value = "spring", annotations = Controller.class)
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRunetimeException() {
        return "error/commonException";
    }
}
