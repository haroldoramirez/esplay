package controllers;

import models.Evento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class EventosController extends Controller {
    
    private static final Form<Evento> eventoForm = Form.form(Evento.class);

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
