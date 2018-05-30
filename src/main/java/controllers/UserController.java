package controllers;

import db.DBHelper;
import db.DBTransaction;
import db.DBUser;
import models.items.Item;
import models.transactions.Transaction;
import models.users.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {

    public UserController(){
        setUpEndPoints();
    }

    private void setUpEndPoints(){

        get("/user", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/user/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/user/transaction-history", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("transactions", DBTransaction.findUsersTransactions(DBUser.getUser(req, res)));
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/user/history.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/user/transaction-history/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            model.put("user", DBUser.getUser(req, res));
            model.put("items", DBTransaction.findItemsInTransaction(id));
            model.put("template", "templates/user/select.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
