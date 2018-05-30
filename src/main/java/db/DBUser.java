package db;

import controllers.LoginController;
import models.users.LoyaltyCard;
import models.users.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import spark.Request;
import spark.Response;

public class DBUser {

    private static Session session;

    public static User getUser(Request req, Response res){
        String username = LoginController.getLoggedInUsername(req, res);
        User foundUser = findUser(username);
        if (foundUser == null) {
            res.redirect("/sign-up");
        }
        return foundUser;
    }

    public static User findUser(String username){
        session = HibernateUtil.getSessionFactory().openSession();
        User foundUser = null;
        try {
            Criteria cr = session.createCriteria(User.class);
            cr.add(Restrictions.eq("username", username));
            foundUser = (User) cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundUser;
    }

}
