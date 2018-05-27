import models.items.Category;
import models.items.Clothe;
import models.items.Food;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClotheTest {

    Clothe clothe;

    @Before
    public void before() {

        clothe = new Clothe(Category.CLOTHE, 1, 8.00, "red", 'm');

    }

    @Test
    public void testGetColor(){
        assertEquals("red", clothe.getColor());
    }

    @Test
    public void testGetSize(){
        assertEquals('m', clothe.getSize());
    }
}
