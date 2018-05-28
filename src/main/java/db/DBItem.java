package db;

import models.items.Item;
import models.transactions.Transaction;

import java.util.Set;

public class DBItem {

    public static void addTransactionToItem(Transaction transaction, Set<Item> items){
        transaction.addItem(items);
        for(Item item : items){
            item.addTransaction(transaction);
        }
        DBHelper.update(transaction);
    }

}
