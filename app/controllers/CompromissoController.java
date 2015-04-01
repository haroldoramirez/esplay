package controllers;

import com.avaje.ebean.Ebean;
import models.Compromisso;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

public class CompromissoController extends Controller {

    public static Result inserir() {
        Compromisso compromisso = Json.fromJson(request().body().asJson(), Compromisso.class);

        Compromisso compromissoBusca = Ebean.find(Compromisso.class).where().eq("nome", compromisso.getDescricao()).findUnique();

        if (compromissoBusca != null) {
            return badRequest("Compromisso já Cadastrado");
        }

        try {
            Ebean.save(compromisso);
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(compromisso));
    }

    public static Result atualizar(Long id) {
        Compromisso compromisso = Json.fromJson(request().body().asJson(), Compromisso.class);

        try {
            Ebean.update(compromisso);
        } catch (PersistenceException e) {
            return badRequest("Categoria já Cadastrada");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }
        return ok(Json.toJson(compromisso));
    }

    public static Result remover(Long id) {
        Compromisso compromisso = Ebean.find(Compromisso.class, id);

        if (compromisso == null) {
            return notFound("Compromisso não encontrado");
        }

        try {
            Ebean.delete(compromisso);
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste Compromisso, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(compromisso));
    }
}
