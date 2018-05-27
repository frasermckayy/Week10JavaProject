package db;

import models.baskets.Basket;
import models.items.Category;
import models.items.Electronic;
import models.items.Food;
import models.items.Item;
import models.transactions.Transaction;
import models.users.User;

public class Seeds {

    public static void main(String[] args) {

        User user = new User();
        //Transaction transaction = new Transaction(user);
        Basket basket = new Basket();
        Food food = new Food(Category.FOOD, 3, 1.50, "Apples");
        Electronic electronic = new Electronic(Category.ELECTRONIC, 2, 6.50, "Mouse");
        basket.addItem(food);
        basket.addItem(electronic);
        DBHelper.save(basket);

        DBHelper.save(food);

    }

}
