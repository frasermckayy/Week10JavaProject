package controllers;

import db.DBHelper;
import db.DBUser;
import models.baskets.Basket;
import models.items.Clothe;
import models.items.Electronic;
import models.items.Food;
import models.items.Item;
import models.users.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class ShopController {

    public ShopController(){
        setUpEndPoints();
    }

    private void setUpEndPoints(){

        // MAIN SHOP ROUTES
        get("/shop", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/shop/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        // SEPERATE ROUTES FOR DIFFERENT CATEGORIES
        // FOOD SECTION
        get("/shop/food", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Food> foods = DBHelper.getAll(Food.class);

            model.put("user", DBUser.getUser(req, res));

            model.put("items", foods);
            model.put("template", "templates/shop/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/shop/food/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Food food = DBHelper.find(id, Food.class);

            model.put("items", food);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/shop/select.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/shop/food/:id/add", (req, res) -> {
            User currentUser = DBUser.getUser(req, res);
            res.redirect("/shop/food");
            return null;
        }, new VelocityTemplateEngine());

        // CLOTHES SECTION
        get("/shop/clothes", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Clothe> clothes = DBHelper.getAll(Clothe.class);
            model.put("user", DBUser.getUser(req, res));
            model.put("items", clothes);
            model.put("template", "templates/shop/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        // NOT WORKING
        get("/shop/clothes/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Clothe clothe = DBHelper.find(id, Clothe.class);

            model.put("items", clothe);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/shop/select.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/shop/clothes/:id/add", (req, res) -> {
            User currentUser = DBUser.getUser(req, res);
            res.redirect("/shop/clothes");
            return null;
        }, new VelocityTemplateEngine());

        // ELECTRONIC SECTION
        get("/shop/electronic", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Electronic> electronics = DBHelper.getAll(Electronic.class);
            model.put("items", electronics);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/shop/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/shop/electronic/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Electronic electronic = DBHelper.find(id, Electronic.class);

            model.put("items", electronic);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/shop/select.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/shop/electronic/:id/add", (req, res) -> {
            User currentUser = DBUser.getUser(req, res);
            res.redirect("/shop/electronic");
            return null;
        }, new VelocityTemplateEngine());

        // SEARCH ROUTES - UNFINISHED
        get("/shop/search", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/shop/search", (req, res) -> {

            // Data still to be added.

            res.redirect("/shop/search");
            return null;
        });

        post("/shop", (req, res) -> {



            res.redirect("/shop");
            return null;
        });

    }

}