package actions;

import controllers.routes;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class AuthenticatedAction extends Action.Simple {

    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        String email = context.session().get("email");
        if (email != null) {
            return delegate.call(context);
        }
        context.flash().put("nao_logado", "Para continuar precisa estar logado");

        return F.Promise.pure(redirect(routes.LoginController.loginTela()));
    }
}
