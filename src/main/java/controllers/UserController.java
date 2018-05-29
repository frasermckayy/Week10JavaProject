package controllers;

import db.DBHelper;
import db.DBUser;
import models.items.Item;
import models.transactions.Transaction;
import models.users.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

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
            List<User> users = DBHelper.getAll(User.class);
            model.put("template", "templates/users/index.vtl");
            model.put("users", users);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/user/transaction-history", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Transaction> transactions = DBHelper.getAll(Transaction.class);
            model.put("template", "templates/users/");
            model.put("users", transactions);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/user/:/id/edit", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String stringId = req.params(":id");
            Integer intId = Integer.parseInt(stringId);
            User user = DBHelper.find(intId, User.class);
            List<Transaction> transactions = DBHelper.getAll(Transaction.class);
            model.put("transactions", transactions);
            model.put("template", "templates/users/edit.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //NOT WORKING
        post("/user", (req, res) -> {
            int transactionId = Integer.parseInt(req.queryParams("transaction"));
            List<Transaction> transactions = DBHelper.getAll(Transaction.class);
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
           // User user = new User(firstName, lastName);
            res.redirect("/user");
            return null;
        });

        post("/user/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User userToDelete = DBHelper.find(id, User.class);
            DBHelper.delete(userToDelete);
            res.redirect("/user");
            return null;
        });

    }

}
