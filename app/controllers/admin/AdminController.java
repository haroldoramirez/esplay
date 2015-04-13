package controllers.admin;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import models.Usuario;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(PlayAuthenticatedSecured.class)
public class AdminController extends Controller {

    public static Result buscaTodosOsUsuarios() {
        if (session().containsKey("email")) {
            return ok(Json.toJson(Ebean.find(Usuario.class).findList()));
        }
        return redirect(controllers.routes.LoginController.loginTela());
    }

}