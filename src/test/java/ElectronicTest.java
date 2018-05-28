import models.items.Category;
import models.items.Electronic;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElectronicTest {

    Electronic electronic;

    @Before
    public void before() {
        electronic = new Electronic(Category.ELECTRONIC, 2, 6.50,"Mouse");
    }

    @Test
    public void testGetParts() {
        assertEquals("Mouse", electronic.getParts());
    }

}
