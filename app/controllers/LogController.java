package controllers;

import com.avaje.ebean.Ebean;
import models.Log;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class LogController extends Controller {

    public static Result logs() {
        return ok(Json.toJson(Ebean.find(Log.class).findList()));
    }

    public Result salvar(Log log) {
        Ebean.save(log);
        return ok();
    }

}
