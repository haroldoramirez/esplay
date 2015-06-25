package controllers;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import models.Log;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Date;

public class LogController extends Controller {

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result inserir(String mensagem) {
        Log log = new Log();
        log.setMensagem(mensagem);
        log.setDataDoLog(new Date());
        Ebean.save(log);
        return ok();
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result listarTodos() {
        return ok(Json.toJson(Ebean.find(Log.class).order().desc("dataDoLog").findList()));
    }
 }
