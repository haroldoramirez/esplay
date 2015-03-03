package controllers;

import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class UsuarioController extends Controller {

    private static final Form<Usuario> usuarioForm = Form.form(Usuario.class);
    
    public static Result novo() {
        return ok(views.html.usuarios.create.render(usuarioForm));
    }

    public static Result lista() {
        List<Usuario> usuarios = Usuario.find.findList();
        return ok(views.html.usuarios.list.render(usuarios));
    }

    public static Result detalhar(Long id) {
        return TODO;
    }
    
    public static Result salvar() {
        Form<Usuario> form = usuarioForm.bindFromRequest();
        if (form.hasErrors()) {
            flash("erro","Erro ao adicionar o Usuário");
            return badRequest(views.html.usuarios.create.render(form));
        }
        Usuario usuario = form.get();
        usuario.save();
        flash("sucesso","Usuário gravado com sucesso");
        return redirect(routes.UsuarioController.lista());
    }

    public static Result alterar() {
        return TODO;
    }

    public static Result remover() {
        return TODO;
    }
}
