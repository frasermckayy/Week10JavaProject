package controllers;

import db.DBHelper;
import db.Seeds;
import models.items.Category;
import models.items.Item;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

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
            HashMap<String, Object> model = new HashMap<>();
            model.put("stock", items);
            model.put("template", "/templates/stocks/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/stock/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Item item = DBHelper.find(id, Item.class);
            HashMap<String, Object> model = new HashMap<>();
            model.put("stock", item);
            model.put("template", "templates/stocks/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/stock", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/stock/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String category = req.queryParams("food");
            String category1 = req.queryParams("clothe");
            String category2 = req.queryParams("electronic");
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            double price = Double.parseDouble(req.queryParams("price"));

            if (category == "FOOD") {
                String name = req.queryParams("name");
                DBHelper.save("food");
            }
            else if (category1 == "CLOTHE") {
                    String size = req.queryParams("size");
                    DBHelper.save("size");
                    String color = req.queryParams("color");
                    DBHelper.save("color");
                }
            else if (category2 == "ELECTRONIC") {
                String parts = req.queryParams("parts");
                DBHelper.save("parts");
            }

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