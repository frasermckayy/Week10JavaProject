package controllers;

import db.DBHelper;
import db.Seeds;
import models.items.Category;
import models.items.Item;
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
            List<Item> items =  DBHelper.getAll(Item.class);
            List<User> user = DBHelper.getAll(User.class);
            HashMap<String, Object> model = new HashMap<>();
            model.put("stock", items);
            model.put("user", user);
            model.put("template", "templates/stock/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/stock/:id", (req, res) -> {
             HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/stock/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Item> items = DBHelper.getAll(Item.class);
            model.put("items", items);
            model.put("template", "templates/stock/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

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
            model.put("template", "templates/stock/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/stock", (req, res) -> {
            String item = req.queryParams("item");
            res.redirect("/stock");
            return null;
        }, new VelocityTemplateEngine());


        post("/stock/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Item itemToDelete = DBHelper.find(id, Item.class);
            DBHelper.delete(itemToDelete);
            res.redirect("/stock");
            return null;
        });

        post("/stock/new", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            res.redirect("/stock");
            return null;
        });
    }

}