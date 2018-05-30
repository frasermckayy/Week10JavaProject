package db;

import models.baskets.Basket;
import models.users.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DBBasket {

    private static Session session;

    public static Basket findBasket(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        Basket foundBasket = null;
        try {
            Criteria cr = session.createCriteria(Basket.class);
            cr.add(Restrictions.eq("user_id", user.getId()));
            foundBasket = (Basket) cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundBasket;
    }

    public static void applyDiscounts(Basket basket){
        basket.calculateTotal();
        basket.buyOneGetOneFree();
        basket.tenPercentOffPurchasesOver100();
        basket.loyaltyDiscount();
        DBHelper.save(basket);
    }

}
