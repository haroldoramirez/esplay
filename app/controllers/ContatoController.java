package controllers;

import com.avaje.ebean.Ebean;
import models.Contato;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

public class ContatoController extends Controller {

    public static Result inserir() {
        Contato contato = Json.fromJson(request().body().asJson(), Contato.class);

        Contato contatoBusca = Ebean.find(Contato.class).where().eq("nome", contato.getNome()).findUnique();

        if (contatoBusca != null) {
            return badRequest("Contato já Cadastrado");
        }

        try {
            Ebean.save(contato);
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }
        return created(Json.toJson(contato));
    }

    public static Result atualizar(Long id) {
        Contato contato = Json.fromJson(request().body().asJson(), Contato.class);

        try {
            Ebean.update(contato);
        } catch (PersistenceException e) {
            return badRequest("Contato já Cadastrado");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }
        return ok(Json.toJson(contato));
    }

    public static Result buscaPorId(Long id) {
        Contato contato = Ebean.find(Contato.class, id);

        if (contato == null) {
            return notFound("Contato não encontrado");
        }

        return ok(Json.toJson(contato));
    }

    public static Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Contato.class).findList()));
    }

    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }

    public static Result remover(Long id) {
        Contato contato = Ebean.find(Contato.class, id);

        if (contato == null) {
            return notFound("Contato não encontrado");
        }

        try {
            Ebean.delete(contato);
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste Contato, remova-os primeiro");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }

        return ok(Json.toJson(contato));
    }
}
