package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.inicio;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result inicio() {
        return ok(inicio.render());
    }

    public static Result sobre() {
        return ok(views.html.sobre.render("Versão do Play ", play.core.PlayVersion.current()));
    }

}
