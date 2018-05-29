package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class TransactionController {


    public TransactionController() {
        setUpEndPoints();
    }

    private void setUpEndPoints() {

        get("/purchase", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();



            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/purchase/buy", (req, res) -> {

            // Data still to be added.

            res.redirect("/transaction");
            return null;
        });


    }
}
