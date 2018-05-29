package controllers;


import db.DBHelper;
import db.DBItem;
import db.DBUser;
import models.baskets.Basket;
import models.items.Clothe;
import models.items.Electronic;
import models.items.Food;
import models.items.Item;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class BasketController {

    public BasketController() {
        setUpEndPoints();
    }

    private void setUpEndPoints() {

        get("/basket", (req, res) ->{
         HashMap<String, Object> model = new HashMap<>();
         model.put("user", DBUser.getUser(req, res));
         model.put("template", "templates/basket/index.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/basket/food/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Food food = DBHelper.find(id, Food.class);
            model.put("item", food);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/basket/select.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/basket/clothe/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Clothe clothe = DBHelper.find(id, Clothe.class);
            model.put("item", clothe);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/basket/select.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/basket/electronic/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            Electronic electronic = DBHelper.find(id, Electronic.class);
            model.put("item", electronic);
            model.put("user", DBUser.getUser(req, res));
            model.put("template", "templates/basket/select.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/basket/purchase", (req, res) -> {
            // Data to be added.
            res.redirect("/basket");
            return null;
        });

        post("/basket/edit", (req, res) -> {
            //Needs data to be added.
            res.redirect("/basket");
            return null;
        });

        post("/basket/delete/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            DBItem.deleteItem(id);
            Basket userBasket = DBUser.getUser(req, res).getBasket();
            userBasket.calculateTotal();
            DBHelper.save(userBasket);
            res.redirect("/basket");
            return null;
        });
    }
}
