import models.baskets.Basket;
import models.items.Category;
import models.items.Electronic;
import models.items.Food;
import models.users.LoyaltyCard;
import models.users.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketTest {

    LoyaltyCard loyaltyCard;
    User user;
    Basket testBasket;
    Food food1;
    Food food2;

    @Before
    public void before() {
        testBasket = new Basket(user);
        food1 = new Food(Category.FOOD, 6, 1.50, "Bananas");
        food2 = new Food(Category.FOOD, 6, 1.50, "Apples");
        loyaltyCard = new LoyaltyCard("27/05/2018");
        user = new User(loyaltyCard, true, "Andrew", "Fraz123", "Pass123");
        loyaltyCard.setUser(user);user.setSignedUpForLoyaltyScheme(true);
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

    @Test
    public void canCalculateTotal(){
        testBasket.addItem(food1);
        testBasket.addItem(food2);
        assertEquals(9.0, testBasket.calculateTotal(), 0.01);
    }

    @Test
    public void canBuyOneGetOneFree(){
        testBasket.addItem(food1);
        testBasket.addItem(food2);
        assertEquals(9.0, testBasket.buyOneGetOneFree(), 0.01);
    }

    @Test
    public void canGetTenPercentOff(){
        Electronic laptop = new Electronic(Category.ELECTRONIC, 150, 1, "XF100");
        testBasket.addItem(laptop);
        assertEquals(135, testBasket.tenPercentOffPurchasesOver100(), 0.01);
    }

    @Test
    public void canGetLoyaltyDiscount(){
        Electronic laptop = new Electronic(Category.ELECTRONIC, 150, 1, "XF100");
        testBasket.addItem(laptop);
        assertEquals(135, testBasket.loyaltyDiscount(user), 0.01);
    }
}
