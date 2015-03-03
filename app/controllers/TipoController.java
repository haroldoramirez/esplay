package controllers;

import models.Tipo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.util.List;

public class TipoController extends Controller {
    
    public static final Form<Tipo> tipoForm = Form.form(Tipo.class);

    public static Result novo() {
        return ok(views.html.tipos.create.render(tipoForm));
    }
    
    public static Result salvar() {
        Form<Tipo> form = tipoForm.bindFromRequest();
        Tipo tipo = form.get();
        tipo.save();
        flash("sucesso","Tipo do Evento gravado com sucesso");
        return redirect(routes.TipoController.lista());
    }
    
    public static Result detalhar(Long id) {
        return TODO;
    }

    public static Result lista() {
        List<Tipo> tipos = Tipo.find.findList();
        return ok(views.html.tipos.list.render(tipos));
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
