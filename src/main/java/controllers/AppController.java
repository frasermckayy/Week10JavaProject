package controllers;


import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.ModelAndView;

import java.util.HashMap;

public class AppController {

    public AppController() {
        setUpEndPoints();
    }

    private void setUpEndPoints() {

        get("/app", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Fill with data

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/app", (req, res) -> {

            // Fill with data

            res.redirect("/app");
            return null;
        });
    }
}
