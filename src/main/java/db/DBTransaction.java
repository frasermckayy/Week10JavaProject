package db;

import models.items.Item;
import models.transactions.Transaction;

import java.util.Set;

public class DBTransaction {

    public static void addItemToTransaction(Set<Item> items, Transaction transaction){
        for(Item item : items){
            item.addTransaction(transaction);
        }
        transaction.addItem(items);
        DBHelper.save(items);
    }

}
