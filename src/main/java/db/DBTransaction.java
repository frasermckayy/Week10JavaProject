package db;

import models.items.Item;
import models.transactions.Transaction;

public class DBTransaction {

    public static void addItemToTransaction(Item item, Transaction transaction){
        item.addTransaction(transaction);
        transaction.addItem(item);
        DBHelper.save(item);
    }

}
