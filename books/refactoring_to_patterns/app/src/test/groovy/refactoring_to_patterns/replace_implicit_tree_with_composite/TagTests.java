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
}
