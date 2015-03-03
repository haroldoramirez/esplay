package controllers;

import models.Evento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class EventoController extends Controller {
    
    private static final Form<Evento> eventoForm = Form.form(Evento.class);

    public static Result novo() {
        return ok(views.html.eventos.create.render(eventoForm));
    }
    
    public static Result lista() {
        List<Evento> eventos = Evento.find.findList();
        return ok(views.html.eventos.list.render(eventos));
    }

    public static Result detalhar(Long id) {
        return TODO;
    }
    
    public static Result salvar() {
        return TODO;
    }

    public static Result alterar() {
        return TODO;
    }

    public static Result remover() {
        return TODO;
    }
}
