package controllers;

import com.avaje.ebean.Ebean;
import models.Log;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class LogController extends Controller {

    public static Result inserir(String mensagem) {

        Log log = new Log();
        log.setMensagem(mensagem);
        Ebean.save(log);
        return ok();
    }

    public static Result listarTodos() {
        return ok(Json.toJson(Ebean.find(Log.class).findList()));
    }
 }
