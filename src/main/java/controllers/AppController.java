package controllers;


import db.Seeds;
import models.transactions.Transaction;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;

import spark.ModelAndView;

import java.util.HashMap;

public class AppController {

    public static void main(String[] args) {

        Seeds.seedData();

        staticFileLocation("/public");

        BasketController basketController = new BasketController();
        LoginController loginController = new LoginController();
        ShopController shopController = new ShopController();
        StockController stockController = new StockController();
        TransactionController transactionController = new TransactionController();
        UserController userController = new UserController();

        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Condensing the code to get the users name into one line.
            model.put("user", LoginController.getLoggedInUsername(req, res));

            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
