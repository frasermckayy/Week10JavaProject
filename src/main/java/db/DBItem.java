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

    public static void deleteItem(int id){
        Item item = DBHelper.find(id, Item.class);
        DBHelper.delete(item);
        DBStock.returnStock(item);
    }

}
