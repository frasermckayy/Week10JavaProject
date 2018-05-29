package db;

import models.baskets.Basket;
import models.items.Item;
import models.transactions.Transaction;
import models.users.User;
import spark.Request;
import spark.Response;

import java.util.Set;

public class DBTransaction {

    public static void addItemToTransaction(Set<Item> items, Transaction transaction){
        for(Item item : items){
            item.addTransaction(transaction);
        }
        transaction.addItem(items);
        DBHelper.save(items);
        DBHelper.save(transaction);
    }

    public static void createTransaction(Request req, Response res){
        User currentUser = DBUser.getUser(req, res);
        Basket currentBasket = currentUser.getBasket();
        Transaction newTransaction = new Transaction(currentUser, currentBasket.getTotal(),"30/05/2018");
        addItemToTransaction(currentBasket.getItems(), newTransaction);
    }

}
