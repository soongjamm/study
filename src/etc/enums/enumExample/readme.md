[예제의 출처](https://jojoldu.tistory.com/122?category=635881

#기본설정
중개료 계약서 관리 라는 시스템

### 계약서의 항목
- 회사명
- 수수료
- 수수료타입
    - 수수료를 %로 처리할지, '원'화로 처리할지 결정한다.
- 수수료절삭
    - 수수료 일정 자리수의 올림/내림/반올림을 결정한다.

# domain
## Contract
- 원래는 `company`도 테이블로 분리해서 도메인으로 관리해야할 대상이지만 이 예제에서는 중요하지 않으므로 String으로 둔다.
- Contract 클래스를 보면 getter와 달리 setter가 없다.
    - 그 이유는 도메인은 특별한 이벤트가 발생했을시 상태를 변경해줘야 하므로, setter로 무분별하게 상태를 변경해서는 안된다.
    - 만약 상태를 변경해야하는 경우 메세지를 보내서 상태를 처리하도록 한다. 
        - `setStatus()`가 아닌 `cancelOrder()`

### 문제점
- commissionType과 commissionCutting이 IDE로부터 지원을 받지 못한다.
    - 자동완성X, 오타검증X
- commissionType과 commissionCutting이 잘못된 값이 할당되도 검증하기 어렵다.
    - percent나 money가 아닌 아예 다른 값을 입력받지 못하도록 검증하는 메소드가 필요해진다.
- commissionType과 commissionCutting에 허용된 값 범위를 확인하기 어렵다.
- commissionType과 commissionCutting의 변경범위가 너무 크다.
    - money를 다른 것으로 변경하려면 프로젝트 전체에서 찾아야 한다. 그리고 다른 도메인의 money가 아닌지도 확인해야 한다.

<br>

### 문제해결1 - static 상수
- Commssion 인터페이스를 만들어 상수를 관리한다면 IDE의 지원을 받고, 변경 범위가 줄어든다.
- 그러나 이 시스템을 모르는 사람은 Commission 인터페이스를 사용해야 한다는 것을 모를 수도 있다.
    - 상수가 아닌, 여전히 'money'처럼 직접 값을 입력할 가능성이 있다.

<br> 

## 문제해결2 - enum (EnumContract.java)
### 내가 기억해야할 내용
- enum을 반드시 하나의 파일로 따로 생성할 필요가 없다. class안에 enum을 만들어도 된다.
    - 즉 보통 변수나 상수를 만들듯이 하면 된다.
- 컨벤션에 따라서 상수-인스턴스-생성자-메소드 순으로 배치를 해야한다. 
    - enum은 인스턴스와 생성자 사이에 둔다.


### Enum 관리 모듈
- enum 타입이 가진 모든 값을 출력하는 기능은 `Class`의 `getEnumConstants()`를 이용한다.
- enum은 인스턴스가 아닌 `type`이다. 그렇기 때문에 전달되고 나면 **name만 남게 된다.** <-- dto를 사용하는 이유

- 2개의 enum 타입이 모두 EnumModel을 구현하도록 만든다.
    - 인터페이스로 구현할 경우 자바의 다형성으로 CommissionType과 CommissionCutting을 EnumModel 타입으로 다룰수 있게 된다.

- 그리고 Dto를 사용한다. (EnumValue.java) 
    - EnumModel을 이용하여 실제 값을 가지고 view에 전달할 수 있는 Dto   
    - ❓DTO : DB레코드와 데이터를 매핑시키기 위해 각 폼 요소를 정의해놓은 클래스로, 로직을 가지고 있지 않다. getter와 setter만 정의한다.
  
### 또 다른 문제점
- EnumController를 호출할 때 마다 EnumValue로 전환하는 작업이 필요하다.
- 다른 Controller/Service/Repository에서 enum리스트를 사용하고 싶을 때 중복 코드 발생한다.
- enum 타입들을 관리하는 모듈을 개발한다.
```
아마 대부분 Spring의 Bean으로 등록해야겠다는 생각이 드실것 같습니다.
어플리케이션이 시작할때만 EnumValue로 전환하는 작업을 수행하고, 그 이후에는 이미 등록된 것들을 호출하여 원하는 곳에서 사용하면 될것 같습니다.
enum 타입들을 관리하는 모듈의 이름을 EnumMapper로 하여 개발을 진행하겠습니다.
```

- factory map을 생성하고 private로 막았다.
    - public으로 오픈한 get(), getAll(),) put()를 통해서만 접근이 가능하기 때문에 `toEnumValues()`를 강제할 수 있다.


❓ 이해가 어려운 코드 (공부)
````java
// 지정한 enum만 가져오는 기능
    public Map<String, List<EnumValue>> get(String keys) {
        return Arrays
                .stream(keys.split(","))
                .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
    }
```
- Collectors.to`