
# JdbcTemplate
- Spring에서 제공하는 클래스로 JDBC API의 불편함을 해소시켜주는 역할을 한다.
- (템플릿 메서드 패턴/ 전략 패턴이 사용됌)

## JDBC API vs JdbcTemplate
`JDBC API` 는 자바로 데이터베이스에 접근할 수 있게 해주는 표준 인터페이스다.  
JAVA - `JdbcTemplate` - `JDBC API` - DB 의 단계로 생각할 수 있다. 

### JdbcTemplate 로 해결할 수 있는 것 
#### 구조적인 반복의 해결
`JDBC API` 를 사용하면 try-catch 문을 만들고 `Connection` - `Statement`, 결과를 가져오기 위해 `ResultSet` 까지 생성 후 예외처리 과정을 매번 반복해야한다.
- 실제 작업과 무관한 코드가 많다.
- 그러므로 어떤 일을 하는 코드인지 단번에 알아차리기 어렵다.

다음은 테이블을 생성하기 위한 코드다.
```java
public void create() throws SQLException {
    Connection connection = null;
    PreparedStatement stmt = null;
    try {
      connection = dataSource.getConnection();
      stmt = connection.prepareStatement("create table mytable (id int, name varchar(255))");
      stmt.execute();
    } catch (SQLException e) {
      // 예외처리
    } finally {
      // 자원반납
      if (stmt != null) stmt.close();
      if (connection != null) connection.close();
    }
}
```
- 실제 쿼리와 관련된 부분은 `stmt = connection.prepareStatement("create table mytable (id int, name varchar(255))");` 한 줄 뿐인데, 
  **구조적인 반복이 심하다.**

<br>

`JdbcTemplate` 를 사용하면 설정코드의 반복없이 간단하다. 
- 무슨 일을 하는 코드인지 알아차리기 쉽다.
- 작업 코드에 집중할 수 있다.
- 실제 작업과 무관한 코드는 `JdbcTemplate` 내부에서 처리해준다.

```java
  public void create() {
    jdbcTemplate.execute("create table mytable (id int, name varchar(255))");
  }
```

<br>

#### 트랜잭션이 간단하다.
`JDBC API` 만 사용할 땐 트랜잭션을 위해서 auto commit 을 해제하는 코드와 commit 을 따로 작성해주어야 한다.
```java
...
connection.setAutoCommit(false);
connection.commit();
```
- 코드를 깜빡하는 등의 실수를 할 여지가 있다. (둘 중 하나를 까먹는 등)
- 그리고 auto commit 해제와 commit 도 결국 실제 작업과 무관한 코드이다.

<br>

`JdbcTemplate` 를 사용하면 `@Transactional` 애노테이션 하나로 트랜잭션 처리가 가능하다.
```java
@Transactional
public void get() {
    // TODO
}
```

<br>

#### 데이터를 자바 객체로 맵핑하기 쉬워진다.
`JDBC API` 만 사용할 땐 쿼리 결과를 객체로 만들기 위해 `ResultSet` 에서 컬럼을 일일이 꺼내고 직접 맵핑시켜야 한다.
- 결과가 여러개라면 반복문을 사용해야 한다.
- rs.next() 를 통해 데이터를 확인하는데, 결과가 여러개일 경우 while 대신 if 를 사용하거나 하는 등 실수를 발생시킬 여지가 있다.

```java
stmt = connection.prepareStatement("select * from mytable where id=1");
rs = stmt.executeQuery();
if (rs.next()) {
    Person person = new Person(
            rs.getInt("id"),
            rs.getString("name")
    );
}
```

<br>

`JdbcTemplate`를 사용하면 ResultSet 에서 값을 꺼내올 필요가 없이 `query()` 메소드에 `RowMapper` 을 구현해서 객체로 맵핑시킬 수 있다.
- 맵퍼를 이용해 모든 데이터를 매핑시켜주므로 객체를 생성하는 것에만 집중할 수 있다. 

```java
List<Person> persons = jdbcTemplate.query("select * from mytable where id=1", new RowMapper<Person>() {
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Person(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
        }
});
```

- 람다식을 이용해 코드를 더 간단하게 줄일 수 있다.
```java
List<Person> persons = jdbcTemplate.query("select * from mytable where id=1",
        (resultSet, i) -> new Person(
                resultSet.getInt("id"),
                resultSet.getString("name")
        ));
}
```

