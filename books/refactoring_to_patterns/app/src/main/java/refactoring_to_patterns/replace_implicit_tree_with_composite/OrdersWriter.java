package refactoring_to_patterns.replace_implicit_tree_with_composite;

public class OrdersWriter {
    private Orders orders;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents() { // compose method 기법을 사용해 리팩터링할 가능성을 찾기 쉬워진 상태.
        StringBuffer xml = new StringBuffer();
        writeOrderTo(xml);
        return xml.toString();
    }

    // XML 생성에 사용될 도메인 객체를 얻기 위해 Order, Product, Price 에 대해 루프를 돌고 있다.
    // 각각 도메인 객체에 XML 생성을 요청하지 않고, 외부에서 XML을 생성하고 있다.
    // 그렇다면 toXML() ? -> 그럴듯해 보이지만, 다양한 표현이 불가능하다.
    // Visitor 패턴은 당장 만들기가 쉽지 않다. (시간이 걸린다)
    // 그래서 그 Visitor 패턴으로 리팩터링 전에, XML 생성 로직을 단순하게 만든다. -> Composite 패턴 적용.
    private void writeOrderTo(StringBuffer xml) {
        xml.append("<orders>");
        for (int i = 0; i < orders.getOrderCount(); i++) {
            Order order = orders.getOrder(i);
            xml.append("<order");
            xml.append(" id='");
            xml.append(order.getOrderId());
            xml.append("'>");
            writeProductsOrderTo(xml, order);
            xml.append("</order>");
        }
        xml.append("</orders>");
    }

    private void writeProductsOrderTo(StringBuffer xml, Order order) {
        for (int i = 0; i < order.getProductCount(); i++) {
            Product product = order.getProduct(i);
            xml.append("<product");
            xml.append(" id='");
            xml.append(product.getId());
            xml.append("'");
            xml.append(" color='");
            xml.append(colorFor(product));
            xml.append("'");
            if (product.getSize() != ProductSize.NOT_APPLICABLE) {
                xml.append(" size='");
                xml.append(sizeFor(product));
                xml.append("'");
            }
            xml.append("/>");
            writePriceTo(xml, product);
            xml.append(product.getName());
            xml.append("</product>");
        }
    }

    private char[] sizeFor(Product product) {
        return new char[0];
    }

    private char[] colorFor(Product product) {
        return new char[0];
    }

    private void writePriceTo(StringBuffer xml, Product product) {
        xml.append("<price>");
        xml.append(" currency='");
        xml.append(currencyFor(product));
        xml.append("'>");
        xml.append(product.getPrice());
        xml.append("</price>");
    }

    private char[] currencyFor(Product product) {
        return new char[0];
    }
}
