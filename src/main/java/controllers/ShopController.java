package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class ShopController {

    public ShopController(){
        setUpEndPoints();
    }

    private void setUpEndPoints(){

        get("/shop", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/shop", (req, res) -> {

            // Data still to be added.

            res.redirect("/shop");
            return null;
        });

        get("/shop/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

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

    }
}