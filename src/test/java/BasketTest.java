import models.baskets.Basket;
import models.items.Food;
import models.users.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    User user;
    Basket testBasket;
    Food food1;
    Food food2;

    @Before
    public void before() {
        testBasket = new Basket(user);
        food1 = new Food("Bread");
        food2 = new Food("Apple");
    }

    @Test
    public void basketStartsEmpty(){
        assertEquals(0, testBasket.numberOfItemsInBasket());
    }

    @Test
    public void canAddItemToBasket(){
        testBasket.addItem(food1);
        assertEquals(1, testBasket.numberOfItemsInBasket());
    }

    @Test
    public void canRemoveItemFromBasket(){
        testBasket.addItem(food1);
        testBasket.addItem(food2);
        testBasket.removeitem(food1);
        assertEquals(1, testBasket.numberOfItemsInBasket());
    }

    @Test
    public void canClearBasket(){
        testBasket.addItem(food1);
        testBasket.addItem(food2);
        testBasket.clearBasket();
        assertEquals(0, testBasket.numberOfItemsInBasket());
    }
}
