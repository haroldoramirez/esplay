package controllers;

import models.Categoria;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class CategoriaController extends Controller {
    
    private static final Form<Categoria> categoriaForm = Form.form(Categoria.class);

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
