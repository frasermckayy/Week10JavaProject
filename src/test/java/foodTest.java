import models.items.Food;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class foodTest {

    private Food food;


    @Before
    public void before() {
    }

    @Test
    public void testGetName() {
        assertEquals("apple",food.getName());
    }
}
