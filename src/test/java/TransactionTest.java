import models.baskets.Basket;
import models.items.Category;
import models.items.Food;
import models.transactions.Transaction;
import models.users.LoyaltyCard;
import models.users.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

    User user;
    Transaction transaction;
    LoyaltyCard loyaltyCard;
    Basket basket;
    Basket testBasket;
    Food food1;
    Food food2;

    @Before
    public void before(){
        loyaltyCard = new LoyaltyCard(user, "na/na/na");
        user = new User(loyaltyCard, false, basket, "Andrew", "Fraz23", "pass123");
        transaction = new Transaction(user, 0, "26/05/2018");
    }

    @Test
    public void canGetUser(){
        assertEquals("Andrew", transaction.getUser().getName());
    }

    @Test
    public void canGetAmount(){
        assertEquals(0, transaction.getAmount(), 0.01);
    }

    @Test
    public void canGetDate(){
        assertEquals("26/05/2018", transaction.getDate());
    }

    @Test
    public void canGetTotalFromBasket(){
        testBasket = new Basket(user);
        food1 = new Food(Category.FOOD, 3, 1.50, "Bananas");
        food2 = new Food(Category.FOOD, 3, 1.50, "Apples");
        testBasket.addItem(food1);testBasket.addItem(food2);
        transaction.updateAmount(testBasket);
        assertEquals(9, transaction.getAmount(), 0.01);
    }

}
