package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result versaoplay() {
        return ok(views.html.versaoPlay.render("Versão do Play ", play.core.PlayVersion.current()));
    }

    public static Result index() {
        String username = session().get("email");
        return ok(views.html.index.render(username));
    }

}
