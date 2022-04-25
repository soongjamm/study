package refactoring_to_patterns.encapsulate_composite_with_builder.replace_implicit_tree_with_composite;

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
        TagNode ordersTag = new TagNode("orders");
        for (int i = 0; i < orders.getOrderCount(); i++) {
            Order order = orders.getOrder(i);
            TagNode orderTag = new TagNode("order");
            orderTag.addAttribute("id", order.getOrderId());
            writeProductsTo(orderTag, order);
            ordersTag.add(orderTag);
        }
        xml.append(ordersTag);
    }

    private void writeProductsTo(TagNode orderTag, Order order) {
        for (int i = 0; i < order.getProductCount(); i++) {
            Product product = order.getProduct(i);
            TagNode productNode = new TagNode("product");
            productNode.addAttribute("id", product.getId());
            productNode.addAttribute("color", colorFor(product));
            if (product.getSize() != ProductSize.NOT_APPLICABLE) {
                productNode.addAttribute("size", sizeFor(product));
            }
            writePriceTo(productNode, product);
            orderTag.add(productNode);
        }
    }

    private void writePriceTo(TagNode productTag, Product product) {
        TagNode priceTag = new TagNode("price");
        priceTag.addAttribute("currency", currencyFor(product));
        priceTag.addValue(priceFor(product));
        productTag.add(priceTag);
    }

    private String sizeFor(Product product) {
        return "new char[0]";
    }

    private String colorFor(Product product) {
        return "new char[0]";
    }

    private String priceFor(Product product) {
        return String.valueOf(product.getPrice());
    }

    private String currencyFor(Product product) {
        return "USD";
    }
}
