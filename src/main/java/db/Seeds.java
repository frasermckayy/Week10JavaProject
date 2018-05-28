package db;

import models.baskets.Basket;
import models.items.*;
import models.transactions.Transaction;
import models.users.LoyaltyCard;
import models.users.User;

public class Seeds {

    public static void seedData() {

        // Clearing DB before re-populating tables

        DBHelper.deleteAll(Food.class);
        DBHelper.deleteAll(Clothe.class);
        DBHelper.deleteAll(Electronic.class);
        DBHelper.deleteAll(User.class);

        // Users

        LoyaltyCard testLoyaltyCard = new LoyaltyCard("n/a");
        User testUser = new User(testLoyaltyCard, false, "Andrew", "Fraz123", "Pass123");

        DBHelper.save(testLoyaltyCard);DBHelper.save(testUser);

        // Food Items

        Food food1 = new Food(Category.FOOD, 10, 0.99, "Apples");
        Food food2 = new Food(Category.FOOD, 10, 1.20, "Bananas");
        Food food3 = new Food(Category.FOOD, 24, 0.10, "Cherries");

        DBHelper.save(food1);DBHelper.save(food2);DBHelper.save(food3);

        // Clothing Items

        Clothe clothe1 = new Clothe(Category.CLOTHE, 3, 5.99, "Red", 'S');
        Clothe clothe2 = new Clothe(Category.CLOTHE, 3, 3.99, "Blue", 'M');
        Clothe clothe3 = new Clothe(Category.CLOTHE, 3, 10.0, "Navy", 'L');

        DBHelper.save(clothe1);DBHelper.save(clothe2);DBHelper.save(clothe3);

        // Electronic Items

        Electronic electronic1 = new Electronic(Category.ELECTRONIC, 2, 80.99, "Tablet");
        Electronic electronic2 = new Electronic(Category.ELECTRONIC, 2, 125.99, "Laptop");
        Electronic electronic3 = new Electronic(Category.ELECTRONIC, 2, 180.99, "Desktop");

        DBHelper.save(electronic1);DBHelper.save(electronic2);DBHelper.save(electronic3);

    }

}
