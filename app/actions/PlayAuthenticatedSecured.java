package actions;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class PlayAuthenticatedSecured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return forbidden(views.html.naoAutorizado.render());
    }
}
