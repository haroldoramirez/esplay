package controllers;

import models.Tipo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class TipoController extends Controller {
    
    public static final Form<Tipo> tipoForm = Form.form(Tipo.class);

    public static Result novo() {
        return ok(views.html.tipos.create.render(tipoForm));
    }
    
    public static Result salvar() {
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
