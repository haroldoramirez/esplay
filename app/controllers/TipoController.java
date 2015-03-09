package controllers;

import com.avaje.ebean.Ebean;
import models.Tipo;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

public class TipoController extends Controller {

    public static Result inserir() {
        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

        try {
            Ebean.save(tipo);
        } catch (PersistenceException e) {
            return badRequest("Tipo já Cadastrado");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }
        return created(Json.toJson(tipo));
    }

    public static Result atualizar(Long id) {
        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

        try {
            Ebean.update(tipo);
        } catch (PersistenceException e) {
            return badRequest("Tipo já Cadastrado");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }
        return ok(Json.toJson(tipo));
    }

    public static Result buscaPorId(Long id) {
        Tipo tipo = Ebean.find(Tipo.class, id);

        if (tipo == null) {
            return notFound("Tipo não encontrado");
        }

        return ok(Json.toJson(tipo));
    }

    public static Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Tipo.class).findList()));
    }

    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }

    public static Result remover(Long id) {
        Tipo tipo = Ebean.find(Tipo.class, id);

        if (tipo == null) {
            return notFound("Contato não encontrado");
        }

        try {
            Ebean.delete(tipo);
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste tipo de Compromisso, remova-os primeiro");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }

        return ok(Json.toJson(tipo));
    }
}
