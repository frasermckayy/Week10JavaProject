package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
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
            model.put("template", "templates/login/login.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/login", (req, res) -> {
            String inputUsername = req.queryParams("username");
            req.session().attribute("username", inputUsername);
            res.redirect("/shop");
            return null;
        });

        get("/logout", (req, res) -> {
            req.session().removeAttribute("username");
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

    }

    public static String getLoggedInUsername(Request req, Response res){
        String username = req.session().attribute("username");
        if (username == null || username.isEmpty()){
            res.redirect("/login");
        }
        return username;
    }
}
