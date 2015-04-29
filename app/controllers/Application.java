package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    static Logger log = LoggerFactory.getLogger(Application.class);

    public static Result versaoplay() {
        return ok(views.html.versaoPlay.render("Versão do Play ", play.core.PlayVersion.current()));
    }

    public static Result index() {
        log.info("Index iniciou");
        String username = session().get("email");
        return ok(views.html.index.render(username));
    }

}
