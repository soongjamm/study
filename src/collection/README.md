## 컬렉션 프레임워크
- 배열만 사용했을 때 문제점은 생성할 때 배열의 크기가 결정되기 때문에 불특정한 길이 만큼의 객체를 저장하기 어렵다. 그리고 배열 안의 객체를 삭제할 시 비어있는 인덱스가 생긴다.  
그래서 `java.util`에 여러가지 자료구조를 클래스로 구현해 놓은 것을 `collection framework`라고 부른다.
> 주요 인터페이스로 List, Set, Map가 있다.

### 분류
- **Collection**
    - List
        - 순서 유지
        - 중복 저장 가능
        - `ArrayList`, `Vector`, `LinkedList`
    - Set
        - 순서 유지하지 않음
        - 중복 저장 불가능
        - `HashSet`, `TreeSet`
- **Map**

    - key-value pair
    - key는 중복 저장 불가능
    - `HashMap`, `Hashtable`, `TreeMap`, `Properties`

<br>

> [basic]() :
>   - List(ArrayList, Vector, LinkedList)
>   - Set(HashSet)
>   - Map(HashMap, HashTable, Properties) 