import models.items.Category;
import models.items.Food;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class foodTest {

    Food food;

    @Before
    public void before() {
        food = new Food(Category.FOOD, 3, 1.50, "Apples");
    }

    @Test
    public void testGetName() {
        assertEquals("Apples", food.getName());
    }

}
