package db;

import models.baskets.Basket;
import models.items.Category;
import models.items.Electronic;
import models.items.Food;
import models.items.Item;
import models.transactions.Transaction;
import models.users.LoyaltyCard;
import models.users.User;

public class Seeds {

    public static void main(String[] args) {
      
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        User user = new User(loyaltyCard, false,"Andrew", "Fraz123", "pass123");

        Basket basket = new Basket(user);
        user.assignBasket(basket);

        Food food = new Food(Category.FOOD, 3, 1.50, "Apples");
        basket.addItem(food);

        Transaction transaction = new Transaction(user, 0, "26/05/2018");
        //transaction.getAmount(basket);

    }

}
