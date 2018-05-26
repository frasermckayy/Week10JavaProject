import controllers.LoginController;
import models.baskets.Basket;
import models.users.LoyaltyCard;
import models.users.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoyaltyCardTest {

    LoyaltyCard loyaltyCard;
    User user;
    Basket basket;

    @Before
    public void before(){
        loyaltyCard = new LoyaltyCard("26/05/2018");
        user = new User(loyaltyCard, false, "Andrew", "Fraz23", "pass123");
    }


    @Test
    public void canAssignToUser(){
        loyaltyCard.assignUser(user);
        assertEquals(user, loyaltyCard.getUser());
    }

    @Test
    public void canGetUsersName(){
        loyaltyCard.assignUser(user);
        assertEquals("Andrew", loyaltyCard.getUser().getName());
    }

    @Test
    public void canGetSignUpDate(){
        assertEquals("26/05/2018", loyaltyCard.getSignUpDate());
    }

}
