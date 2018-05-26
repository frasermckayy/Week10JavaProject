import models.baskets.Basket;
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

    @Before
    public void before(){
        loyaltyCard = new LoyaltyCard(user, "na/na/na");
        user = new User(loyaltyCard, false, basket, "Andrew", "Fraz23", "pass123");
        transaction = new Transaction(user, 50, "26/05/2018");
    }

    @Test
    public void canGetUser(){
        assertEquals("Andrew", transaction.getUser().getName());
    }

    @Test
    public void canGetAmount(){
        assertEquals(50, transaction.getAmount(), 0.01);
    }

    @Test
    public void canGetDate(){
        assertEquals("26/05/2018", transaction.getDate());
    }

}
