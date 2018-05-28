package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {

    public LoginController() {
        setUpEndPoints();
    }

    private void setUpEndPoints() {

        get("/login", (req, res) ->{
            HashMap<String, Object> model = new HashMap<>();

            //Needs data to be added.

            return new ModelAndView(model, "template/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/login", (req, res) -> {

            //Needs data to be added.

            res.redirect("/login");
            return null;
        });



    }
}
