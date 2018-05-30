package db;

import models.baskets.Basket;
import models.items.Item;
import models.users.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DBBasket {

    private static Session session;

//    public static Basket findBasket(User user){
//        session = HibernateUtil.getSessionFactory().openSession();
//        Basket foundBasket = null;
//        try {
//            Criteria cr = session.createCriteria(Basket.class);
//            cr.add(Restrictions.eq("user_id", user.getId()));
//            foundBasket = (Basket) cr.uniqueResult();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return foundBasket;
//    }

    public static void applyDiscounts(Basket basket){
        basket.calculateTotal();
        basket.buyOneGetOneFree();
        basket.tenPercentOffPurchasesOver100();
        basket.loyaltyDiscount();
        DBHelper.save(basket);
    }

    public static void clearBasket(User user) {
        Basket adminBasket = getAdminBasket();
        Basket usersBasket = user.getBasket();
        for(Item item : usersBasket.getItems()){
            item.setBasket(adminBasket);
            DBHelper.save(item);
        }
        DBHelper.delete(usersBasket);
        // usersBasket.clearBasket();
        Basket newBasket = new Basket(user);
        user.setBasket(newBasket);
        newBasket.calculateTotal();
        DBHelper.save(newBasket);DBHelper.save(user);DBHelper.save(adminBasket);
    }

    private static Basket getAdminBasket(){
        return DBHelper.find(1, Basket.class);
    }


}
