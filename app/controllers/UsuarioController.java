package controllers;

import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UsuarioController extends Controller {

    private static final Form<Usuario> usuarioForm = Form.form(Usuario.class);
    
    public static Result novo() {
        return TODO;
    }

    public static Result lista() {
        return TODO;
    }

    public static Result detalhar() {
        return TODO;
    }

    public static Result alterar() {
        return TODO;
    }

    public static Result remover() {
        return TODO;
    }
}
