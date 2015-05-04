package controllers;

import akka.util.Crypt;
import models.Usuario;
import models.Usuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;

public class LoginController extends Controller {

    static Logger log = LoggerFactory.getLogger(LoginController.class);

    private static DynamicForm form = Form.form();

    public static Result loginTela() {
        return ok(views.html.login.render(form));
    }

    public static Result telaAutenticado() {
        String username = session().get("email");
        return ok(views.html.autenticado.render(username));
    }

    public static Result telaLogout() {
        return ok(views.html.logout.render());
    }

    public static Result autenticar() {

        Form<DynamicForm.Dynamic> requestForm = form.bindFromRequest();

        String email = requestForm.data().get("email");
        String senha = requestForm.data().get("senha");

        F.Option<Usuario> talvesUmUsuario = Usuarios.existe(email, Crypt.sha1(senha));

        if (talvesUmUsuario.isDefined()) {
            session().put("email", talvesUmUsuario.get().getEmail());
            log.info("Usuário '{}' autenticou no sistema", talvesUmUsuario.get().getEmail());
            return redirect(routes.LoginController.telaAutenticado());
        }

        DynamicForm formDeErro = form.fill(requestForm.data());
        formDeErro.reject("O email ou senha não existem");
        return forbidden(views.html.login.render(formDeErro));
    }

    public static Result logout() {
        session().clear();
        log.info("Usuário saiu do sistema");
        return redirect(routes.LoginController.telaLogout());
    }
}
