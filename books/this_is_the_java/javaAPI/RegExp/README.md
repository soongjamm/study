## 정규 표현식과 Pattern 클래스
### 정규 표현식
- 자세한 작성법은 API 도큐먼트에서 `java.util.regex.Pattern` 클래스를 찾아 `Summary of regular-expression constructs` 참조. [link](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html)
- 요약  
    - `[]` : 한 개의 문자
        - `[abc]` : a,b,c 중 하나의 문자
        - `[^abc]` : a,b,c 이외의 하나의 문자
        - `[a-zA-Z]` : a~z, A~Z 중 하나의 문자 
    - `\d` : 한 개의 숫자 
        - `[0-9]`와 동일
    - `\s` : 공백
    - `\w` : 한 개의 알파벳 또는 한 개의 숫자
        - `[a-zA-Z_0-9]` 와 동일
    - `?` : 없음 또는 한 개
    - `*` : 없음 또는 한 개 이상
    - `+` : 한 개 이상
    - `{n}` : 정확히 n개 
    - `{n,}` : 최소한 n개
    - `{n, m}` : n개에서 m개 까지
    - `()` : 그룹핑
    
    - `a*b` : a가 없거나 1개 이상이고 b가 온다.
    - `\w+` : 1개 이상의 문자
    - `(\.\w+)?` : .과 1개 이상의 문자로 이루어진 (.kr 같은) 문자열이 없거나 1개 온다.  
     
### Pattern 클래스
- String 으로 정의된 정규 표현식은 우선 `Pattern` 클래스로 컴파일 하고 `Matcher` 객체를 생성하는데 쓰인다. 그리고 `Matcher` 객체를 이용해서 다른 문자열들과 match 한다.
    ```
    Pattern p = Pattern.compile("a*b");
    Matcher m = p.matcher("aaaaab");
    boolean b = m.matches();
    ```
  
- 또는 정규 표현식이 한 번만 쓰일 때는 다음 처럼 사용할 수 있다.
    - `java.util.regex.Pattern` 클래스의 정적 메소드 `matches()`로 정규 표현식을 검증할 수 있다.
    ```
    String regExp = "(02|010)-\\d{3,4}-\\d{4}";
    String data = "010-123-4567";
    boolean result = Pattern.matches(regExp, data);
    ```
