package db;

import models.items.Item;
import models.transactions.Transaction;

public class DBItem {

    public static void addTransactionToItem(Transaction transaction, Item item){
        transaction.addItem(item);
        item.addTransaction(transaction);
        DBHelper.save(transaction);
    }

}
