# 유의사항 
Tomcat 설정 deployment에 설정할 때 WEB-INF 바로 하위 폴더를 설정해야함.

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
