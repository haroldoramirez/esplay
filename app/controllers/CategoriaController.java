package controllers;

import models.Categoria;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class CategoriaController extends Controller {
    
    private static final Form<Categoria> categoriaForm = Form.form(Categoria.class);

    public static Result novo() {
        return ok(views.html.categorias.create.render(categoriaForm));
    }

    public static Result lista() {
        List<Categoria> categorias = Categoria.find.findList();
        return ok(views.html.categorias.list.render(categorias));
    }

    public static Result detalhar(Long id) {
        return TODO;
    }
    
    public static Result salvar() {
        Form<Categoria> form = categoriaForm.bindFromRequest();
        if (form.hasErrors()) {
            flash("erro","Erro ao adicionar a Categoria");
            return badRequest(views.html.categorias.create.render(form));
        }
        Categoria categoria = form.get();
        categoria.save();
        flash("sucesso","Categoria gravada com sucesso");
        return redirect(routes.CategoriaController.lista());
    }

    public static Result alterar() {
        return TODO;
    }

    public static Result remover() {
        return TODO;
    }
}
