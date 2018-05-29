package db;

import models.baskets.Basket;
import models.items.Item;
import models.transactions.Transaction;
import models.users.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import spark.Request;
import spark.Response;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public class DBTransaction {

    private static Session session;

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

    public static List<Transaction> findUsersTransactions(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> usersTransactions = null;
        try {
            Criteria cr = session.createCriteria(Transaction.class);
            cr.add(Restrictions.eq("id", user.getId()));
            usersTransactions = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usersTransactions;
    }

    public static List<Item> findItemsInTransaction(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Item> itemsInTransaction = null;
        try {
            Criteria cr = session.createCriteria(Item.class);
            cr.createAlias("transactions", "transaction");
            cr.add(Restrictions.eq("transaction.id", id));
            itemsInTransaction = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return itemsInTransaction;
    }
}