package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class StockController {

    public StockController(){
        setUpEndPoints();
    }

    private void setUpEndPoints(){

        get("/stock", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/stock/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/stock", (req, res) -> {

            // Data still to be added.

            res.redirect("/stock");
            return null;
        });

        post("/stock/edit", (req, res) -> {

            // Data still to be added.

            res.redirect("/stock");
            return null;
        });

        post("/stock/delete", (req, res) -> {

            // Data still to be added.

            res.redirect("/stock");
            return null;
        });

    }

}