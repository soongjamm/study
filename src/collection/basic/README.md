
## List
- 객체 자체를 저장하지 않고 객체의 번지를 참조한다.  
- null도 저장이 가능한데 이 경우 객체를 참조하지 않고 있는 상태다.

###ArrayList
- 객체가 인덱스로 관리된다. 기본적으로 배열과 유사하지만 런타임중 삽입/삭제가 자유롭다.
- 그러나 중간에 있는 인덱스를 삭제할 경우 그 뒤에 있는 요소들을 전부 다 1칸씩 당겨야 하기 때문에 객체 삽입/삭제가 빈번하다면 ArrayList는 적당하지 않다.
    - 이런 경우는 LinkedList가 더 좋은 성능을 보여준다.
    - ArrayList는 맨 마지막에 객체를 추가하는 경우 사용하는 것이 적당하다.
- 고정된 객체를 요소로 갖는 ArrayList 객체를 생성하려면 `Arrays.asList(T...a)` 메소드를 사용한다.
    - 예) `List<String> list = Arrays.asList("홍길동", "박명수", "유재석")`
  
### Vector
- 내부 구조는 ArrayList와 같다.
- 다른 점은 `Vector`는 동기화된 메소드로 구성되어 있어 멀티스레드가 동시에 이 메소드들을 실행할 수 없다.
    - 멀티 스레드 환경에서 안전하게 객체를 추가/삭제 가능하다. --> 스레드가 안전하다. (Thread Safe)

### LinkedList
- 인접 참조를 링크해서 체인처럼 관리한다. 
- 중간에 추가/삭제가 잦은 경우 LinkedList가 빠르고, 끝에서 부터 순차적으로 추가/삭제 하는 경우 ArrayList가 빠르다.

<br>

## Set
- 순서가 보장되지 않고 중복이 허용되지 않는다.  
- add() 메소드로 객체를 저장시 성공하면 true, 실패하면 false를 리턴한다. 
- 인덱스로 관리하지 않기 때문에 인덱스로 객체를 가져오는 메소드는 없고, 반복자(Iterator)를 제공한다. 또는 for-each 반복문을 사용한다.  
```
Set<String> set = ...;
Iterator<String> iterator = set.iterator();
// or
for (String str : set) { 
... 
}
```
> Iterator 메소드에서 remove를 수행하면 실제 Set 컬렉션에서 객체가 제거된다. 기억!

### HashSet
- Set 인터페이스의 구현 클래스다.
- HashSet은 객체를 저장하기 전에 hashCode() 메소드를 호출해서 해시코드를 얻어낸 뒤 이미 저장된 객체들의 해시코드와 비교한다. 만약 동일한 해시코드가 있다면 다시 equals() 메소드로 두 객체를 비교해서 true이면 동일한 객체로 판단하여 저장하지 않는다.
    - String 객체는 같은 문자열이면 같은 객체로 간주한다.

<br>

## Map
- key와 value는 모두 객체이다. 
- `put(key, value)` 메소드로 값을 저장시 새로운 키면 null, 동일한 키가 있을 경우 값을 대체한 후 이전 값을 리턴한다. 
- 전체 객체를 하나씩 얻는 방법은 `keySet()` 메소드를 사용하는 방법과 `entrySet()` 메소드를 사용하는 방법이 있다.
```
Map<K, V> map = ~;

Set<K> keySet = map.keySet();
Iterator<K< keyIterator = keySet.iterator();
while (keyIterator.hasNext()) { 
    K key = keyIterator.next();
    V value = map.get(key);
}

// or

Set<Map.Entry<K,V>> entrySet = map.entrySet();
Iterator<Map.Entry<K,V>> entryIterator = entrySet.iterator();
while (entryIterator.hasNext()) {
    Map.Entry<K, V> entry = entryIterator.next();
    K key = entry.getKey();
    V value = entry.getValue();
}
```

### HashMap
- Map 인터페이스를 구현한 Map 컬렉션.
- HashMap의 키로 사용할 객체는 hashCode()와 equals() 메소드를 재정의해서 동등 객체가 될 조건을 정해야 한다.
    - 동일한 키가 되려면 hashCode()의 리턴값이 같고, equals() 메소드가 true를 리턴.

### HashTable
- HashMap과 동일한 내부구조를 가지고 있다.
- 차이점은 동기화된 메소드로 구성되어 있어 멀티 스레드가 동시에 접근할 수 없다. -> 스레드가 안전하다.

### Properties
- HashTable의 하위클래스이기 때문에 HashTable의 특징을 그대로 가지고 있다.
- 차이점은 **Key-Value의 속성을 String으로 제한**하였다.
- Properties는 **애플리케이션의 옵션 정보, 데이터베이스 연결 정보, 국제화(다국어) 정보가 저장된 프로퍼티(.properties) 파일을 읽을 때 주로 사용한다.**
- 프로퍼티 파일은 키와 값이 `=` 기호로 연결되어 있는 텍스트 파일로 ISO 8859-1 문자셋으로 저장된다.
- 한글은 유니코드로 변환되어 저장된다.
- 프로퍼티 파일을 읽기 위해서는 `Properties` 객체를 생성 후 `load()` 메소드로 호출한다.
```
Properties properties = new Properties();
properties.load(new FileReader("경로~/database.properties");
``` 
- 파일의 경로는 절대경로 `getPath()` 나 상대경로 `getResource()` 메소드로 얻을 수 있다.
    - 프로퍼티 파일은 일반적으로 `.class` 파일과 함께 저장되는데, 상대경로는 이 클래스 파일을 기준으로 한다.
    - 경로에 한글이 있을 경우 한글을 복원하기 위해 `path = URLDecoder.decode(path, "utf-8")`

- Properties도 Map 컬렉션이므로 키의 값을 얻으려면 `get()`을 이용할 수 있다.
    - **그러나** `get()`은 값을 Object로 리턴하기 때문에 강제 타입 변환으로 String을 얻어야 하므로
    - `getProperty()`를 일반적으로 사용한다. 