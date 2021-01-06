package week6;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DollarTest {

    @Test
    void equalsTest() {
        assertTrue(new Dollar("soongjamm", 5).equals(new Dollar("soongjamm", 5)));
        assertFalse(new Dollar("soongjamm", 5).equals(new Dollar("soongjamm", 15)));
    }

    @Test
    void hashCodeTest() {
        HashMap<Dollar, String> hashMap = new HashMap<Dollar, String>();
        hashMap.put(new Dollar("soongjamm", 5), "숭잼");
        String value = hashMap.get(new Dollar("soongjamm", 5));
        assertEquals("숭잼", value);
    }

    @Test
    void toStringTest() {
        assertEquals("5달러입니다.", new Dollar("soongjamm", 5).toString());
    }

    @Test
    void cloneTest() {
        Dollar five = new Dollar("soongjamm", 5);
        // 얕은 복제
//        assertEquals(five.amount, five.copyDollar().amount);

        // 깊은 복제
        Dollar copiedFive = five.copyDollar();
        copiedFive.owner = "new owner";
        assertNotEquals(five.owner, copiedFive.owner);
    }
}