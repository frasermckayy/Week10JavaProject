import controllers.LoginController;
import models.users.LoyaltyCard;
import models.users.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoyaltyCardTest {

    LoyaltyCard loyaltyCard;
    User user;

    @Before
    public void before(){
        loyaltyCard = new LoyaltyCard(user, "26/05/2018");
    }

    @Test
    public void canGetUser(){
        assertEquals(user, loyaltyCard.getUser());
    }

    @Test
    public void canGetSignUpDate(){
        assertEquals("26/05/2018", loyaltyCard.getSignUpDate());
    }

}
