package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result sobre() {
        String username = session().get("email");
        return ok(views.html.sobre.render("Versão do Play ", play.core.PlayVersion.current(), username));
    }

    public static Result index() {
        String username = session().get("email");
        return ok(views.html.index.render(username));
    }

    public static Result manual() {
        String username = session().get("email");
        return ok(views.html.manual.render(username));
    }
}
