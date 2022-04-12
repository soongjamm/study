package refactoring_to_patterns.replace_implicit_tree_with_composite;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TagTests  {
    private static final String SAMPLE_PRICE = "8.95";

    @Test
    public void testSimpleTagWithOneAttributeAndValue() {
        TagNode priceTag = new TagNode("price");
        priceTag.addAttribute("currency", "USD");
        priceTag.addValue(SAMPLE_PRICE);
        String expected = "<price currency='USD'>" + SAMPLE_PRICE + "</price>";

        assertEquals("price XML", expected, priceTag.toString());
    }

    @Test
    void testCompositeTagOneChild() {
        TagNode productTag = new TagNode("product");
        productTag.add(new TagNode("price"));
        String expected =
                "<product>" +
                    "<price>" +
                    "</price>" +
                "</product>";
        assertEquals("product XML", expected, productTag.toString());
    }

    @Test
    void testAddingChildrenAndGrandchildren() {
        String expected =
                "<orders>" +
                    "<order>" +
                        "<product>" +
                        "</product>" +
                    "</order>" +
                "</orders>";

        TagNode ordersTag = new TagNode("orders");
        TagNode orderTag = new TagNode("order");
        TagNode productTag = new TagNode("product");
        ordersTag.add(orderTag);
        orderTag.add(productTag);
        assertEquals("price XML", expected, ordersTag.toString());
    }
}
