import controllers.LoginController;
import models.baskets.Basket;
import models.users.LoyaltyCard;
import models.users.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    LoyaltyCard loyaltyCard;
    User user;
    Basket basket;

    @Before
    public void before(){
        loyaltyCard = new LoyaltyCard(user, "na/na/na");
        user = new User(loyaltyCard, false, basket, "Andrew", "Fraz23", "pass123");
    }

    @Test
    public void canGetLoyaltyCard(){
        assertEquals(loyaltyCard, user.getLoyaltyCard());
    }

    @Test
    public void canCheckIfSignedUpToLoyaltyScheme(){
        assertEquals(false, user.isSignedUpForLoyaltyScheme());
    }

    @Test
    public void canGetBasket(){
        assertEquals(basket, user.getBasket());
    }

    @Test
    public void canGetName(){
        assertEquals("Andrew", user.getName());
    }

    @Test
    public void canGetUsername(){
        assertEquals("Fraz23", user.getUsername());
    }

    @Test
    public void canGetPassWord(){
        assertEquals("pass123", user.getPassword());
    }
}
