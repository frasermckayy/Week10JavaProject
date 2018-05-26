package db;

import models.items.Food;

public class Seeds {

    public static void main(String[] args) {

        Food food = new Food("cheese");
////        Basket basket = new Basket();
////        User user = new User();
////        Transaction transaction = new Transaction();
////        LoyaltyCard loyaltyCard = new LoyaltyCard();
//
//        DBHelper.save();
        DBHelper.save(food);
////        DBHelper.save(basket);
////        DBHelper.save(user);
////        DBHelper.save(transaction);
////        DBHelper.save(loyaltyCard);

    }

}
