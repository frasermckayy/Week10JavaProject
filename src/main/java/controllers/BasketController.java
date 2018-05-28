package controllers;


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

         //Needs data to be added.

        return new ModelAndView(model, "template/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/basket/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            //Needs data to be added.

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/basket", (req, res) -> {

             //Needs data to be added.

            res.redirect("/basket");
            return null;
        });

        post("/basket/edit", (req, res) -> {

            //Needs data to be added.

            res.redirect("/basket");
            return null;
        });

        post( "/basket/update", (req, res) ->{

            // data needs to be added

            res.redirect("/basket");
            return null;
        });

        post("/basket/delete", (req, res) -> {

            //Needs data to be added.

            res.redirect("/basket");
            return null;
        });

    }
    }
