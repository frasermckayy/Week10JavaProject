package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {

    public UserController(){
        setUpEndPoints();
    }

    private void setUpEndPoints(){

        get("/user", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/user/transaction-history", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/user/edit", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Data still to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/user", (req, res) -> {

            // Data still to be added.

            res.redirect("/user");
            return null;
        });

        post("/user/delete", (req, res) -> {

            // Data still to be added.

            res.redirect("/");
            return null;
        });

    }

}
