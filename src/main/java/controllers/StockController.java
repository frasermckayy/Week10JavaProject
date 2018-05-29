package controllers;

import db.DBHelper;
import db.DBStock;
import db.DBUser;
import db.Seeds;
import models.items.*;
import models.users.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;

public class StockController {

    public StockController(){
        setUpEndPoints();
    }

    private void setUpEndPoints(){

        get("/stock", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Item> items = DBStock.getAllStock();
            List<User> user = DBHelper.getAll(User.class);
            model.put("stock", items);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/stock/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/stock/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Item> items = DBHelper.getAll(Item.class);
            model.put("items", items);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/stock/create.vtl");
            model.put("categories", Category.values());
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/stock/new/item", (req, res) -> {
            String category = req.queryParams("category");
            res.redirect("/stock/new/" + category.toLowerCase());
            return null;
        });

        get("/stock/:id", (req, res) -> {
             HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

   
        get("/stock/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Item item = DBHelper.find(id, Item.class);
            User user = DBHelper.find(id, User.class);
            HashMap<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("stock", item);
            model.put("user", DBUser.getUser(req, res));
            model.put("categories", Category.values());
            model.put("template", "templates/stock/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/stock/new/food", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("user", DBUser.getUser(req, res));
            model.put("category", "food");
            model.put("template", "templates/stock/create-item.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/stock/new/clothe", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("user", DBUser.getUser(req, res));
            model.put("category", "clothe");
            model.put("template", "templates/stock/create-item.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/stock/new/electronic", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("user", DBUser.getUser(req, res));
            model.put("category", "electronic");
            model.put("template", "templates/stock/create-item.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/stock", (req, res) -> {
            String item = req.queryParams("item");
            res.redirect("/stock");
            return null;
        }, new VelocityTemplateEngine());


        post ("/stock/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Item item = DBHelper.find(intId, Item.class);
            Category category = Category.valueOf(req.queryParams("category"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            int price = Integer.parseInt(req.queryParams("price"));
            item.setCategory(category);
            item.setQuantity(quantity);
            item.setPrice(price);
            DBHelper.update(item);
            res.redirect("/stock");
            return null;
        }, new VelocityTemplateEngine());


        post("/stock/new/food", (req, res) ->{
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            int price = Integer.parseInt(req.queryParams("price"));
            String name = req.queryParams("name");
            Food newFoodItem = new Food(Category.FOOD, quantity, price, name);
            DBHelper.save(newFoodItem);
            res.redirect("/stock");
            return null;
        });

        post("/stock/new/clothe", (req, res) -> {
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            int price = Integer.parseInt(req.queryParams("price"));
            String color = req.queryParams("color");
            Character size = req.queryParams("size").charAt(0);
            Clothe newClotheItem = new Clothe(Category.CLOTHE, quantity, price, color, size);
            DBHelper.save(newClotheItem);
            res.redirect("/stock");
            return null;
        });

        post("/stock/new/electronic", (req, res) -> {
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            int price = Integer.parseInt(req.queryParams("price"));
            String parts = req.queryParams("parts");
            Electronic newElectronicItem = new Electronic(Category.ELECTRONIC, quantity, price, parts);
            DBHelper.save(newElectronicItem);
            res.redirect("/stock");
            return null;
        });

        post("/stock/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Item itemToDelete = DBHelper.find(id, Item.class);
            DBHelper.delete(itemToDelete);
            res.redirect("/stock");
            return null;
        });


    }

}