import models.items.Category;
import models.items.Food;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    Food food;

    @Before
    public void before(){
        food = new Food(Category.FOOD, 3, 1.50, "Apples");
    }

    @Test
    public void canGetCategory(){
        assertEquals(Category.FOOD, food.getCategory());
    }

    @Test
    public void canGetQuantity(){
        assertEquals(3, food.getQuantity());
    }

    @Test
    public void canGetPrice(){
        assertEquals(1.50, food.getPrice(), 0.01);
    }

}
