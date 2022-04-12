package refactoring_to_patterns.replace_implicit_tree_with_composite;

import junit.framework.TestCase;

public class OrdersWriterTest extends TestCase {

    public void testName() {
        String expectedResult =
                "<orders>" +
                    "<order id='321'>" +
                        "<product id='123' color='red' size='medium>" +
                            "<price currency='USD'>" +
                                "8.95" +
                            "</price>" +
                            "Fire Truck" +
                        "</product>" +
                    "</order>" +
                "</orders>";
        // 묵시적 종단을 찾아보자. 8.95 vs <price> 태그
        // 값은 쉽게 표현 가능. 그래서 태그를 묵시적 종단으로 선택.
        // 모든 태그는 이름이 있고 속성, 자식, 값을 옵션으로 갖는다. (자식은 일단 무시)
        // TagNode를 만들자. by tdd
    }

}