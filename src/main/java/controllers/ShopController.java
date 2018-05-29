package controllers;

import db.*;
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
            List<Food> foods = DBStock.getFoodStock();
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
            Basket usersBasket = currentUser.getBasket();

            int id = Integer.parseInt(req.params(":id"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));

            Food food = DBHelper.find(id, Food.class);
            food.setId(id);food.setQuantity(food.getQuantity()-quantity);


            Food newFoodItem = new Food(food.getCategory(), quantity, food.getPrice(), food.getName());
            // newFoodItem.setQuantity(quantity);

            usersBasket.addItem(newFoodItem);newFoodItem.setBasket(usersBasket);

            DBHelper.update(usersBasket);DBHelper.save(food);DBHelper.save(newFoodItem);

            res.redirect("/shop/food");
            return null;
        }, new VelocityTemplateEngine());

        // CLOTHES SECTION
        get("/shop/clothes", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Clothe> clothes = DBStock.getClotheStock();
            model.put("user", DBUser.getUser(req, res));
            model.put("items", clothes);
            model.put("template", "templates/shop/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        // ROUTE NOT WORKING
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
            Basket usersBasket = currentUser.getBasket();

            int id = Integer.parseInt(req.params(":id"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));

            Clothe clothe = DBHelper.find(id, Clothe.class);
            clothe.setId(id);clothe.setQuantity(clothe.getQuantity()-quantity);


            Clothe newClotheItem = clothe;
            newClotheItem.setQuantity(quantity);

            usersBasket.addItem(newClotheItem);newClotheItem.setBasket(usersBasket);

            DBHelper.update(usersBasket);DBHelper.update(clothe);DBHelper.save(newClotheItem);

            res.redirect("/shop/clothes");
            return null;
        }, new VelocityTemplateEngine());

        // ELECTRONIC SECTION
        get("/shop/electronic", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Electronic> electronics = DBStock.getElectronicStock();
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
            Basket usersBasket = currentUser.getBasket();

            int id = Integer.parseInt(req.params(":id"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));

            Electronic electronic = DBHelper.find(id, Electronic.class);
            electronic.setId(id);electronic.setQuantity(electronic.getQuantity()-quantity);


            Electronic newElectronicItem = electronic;
            newElectronicItem.setQuantity(quantity);

            usersBasket.addItem(newElectronicItem);newElectronicItem.setBasket(usersBasket);

            DBHelper.update(usersBasket);DBHelper.update(electronic);DBHelper.save(newElectronicItem);

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