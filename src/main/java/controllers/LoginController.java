package controllers;

import db.DBHelper;
import models.baskets.Basket;
import models.users.LoyaltyCard;
import models.users.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {

    public LoginController() {
        setUpEndPoints();
    }

    private void setUpEndPoints() {

        get("/login", (req, res) ->{
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/login/login.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/login", (req, res) -> {
            String inputUsername = req.queryParams("username");
            req.session().attribute("username", inputUsername);
            res.redirect("/shop");
            return null;
        });

        get("/logout", (req, res) -> {
            req.session().removeAttribute("username");
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

        get("/sign-up", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/login/signup.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/sign-up/new", (req, res) -> {
            LoyaltyCard newLoyaltyCard = new LoyaltyCard("N/A");
            String name = req.queryParams("name");
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            Boolean loyaltyScheme = false;
            if (req.queryParams("loyaltyscheme").equals("true")) { loyaltyScheme = true; }

            User newUser = new User(newLoyaltyCard, loyaltyScheme, name, username, password);
            Basket newBasket = new Basket(newUser);

            DBHelper.save(newLoyaltyCard);DBHelper.save(newUser);DBHelper.save(newBasket);

            res.redirect("/");
            return null;
        });

    }

    public static String getLoggedInUsername(Request req, Response res){
        String username = req.session().attribute("username");
        if (username == null || username.isEmpty()){
            res.redirect("/login");
        }
        return username;
    }
}
