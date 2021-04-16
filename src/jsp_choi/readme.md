# 유의사항 
~~Tomcat 설정 deployment에 설정할 때 WEB-INF 바로 하위 폴더를 설정해야함.~~
최상단폴더로 설정하고 그 아래에 chap14, 17.. , WEB-INF 를 놓으면 됌.

WEB-INF아래
- 외부 라이브러리들은 lib에
- 직접 작성하고 컴파일한 클래스들은 classes에

## chap14 (db)
### memo
- engine=InnoDB 라는 구문을 종종 보는데, mysql 에서 사용하는 구문이고 저장엔진이라고 한다.  
  트랜잭션을 사용하기 위해 쓴다고 함.
- Class.forName("com.mysql.jdbc.Driver") : JDBC드라이버 클래스들은 이 메소드를 통해 로딩될때 자동으로 JDBC 드라이버로 등록한다고 한다.
  

### chap14 에서 사용한 라이브러리
- 자카르타 프로젝트 dhcp2 api 
    - dbcp
    - logging  
    - pool
- mysql driver

## chap17
### memo
- 책에선 <servlet-mapping> 태그 없이 서블릿을 등록하는데, 옛날 버전에서만 가능하다. (아마 톰캣 7이하?)
- 서블릿은 최초 요청시에 서블릿 컨테이너가 생성하고 init을 호출한함(여기까지 서블릿 로딩) 뒤 나중엔 재사용    
  - <load-on-startup> 값 1 주면 톰캣 시작하자마자 서블릿 생성 가능