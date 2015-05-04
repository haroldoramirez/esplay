package controllers;

import com.avaje.ebean.Ebean;
import models.Tipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

public class TipoController extends Controller {

    static Logger log = LoggerFactory.getLogger(TipoController.class);

    public static Result inserir() {
        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

        Tipo tipoBusca = Ebean.find(Tipo.class).where().eq("nome", tipo.getNome()).findUnique();

        if (tipoBusca != null) {
            return badRequest("Tipo de Compromisso já Cadastrado");
        }

        tipo.setPadraoDoSistema(false);

        try {
            Ebean.save(tipo);
            log.info("Novo Tipo de compromisso criado: {}", tipo.getNome());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(tipo));
    }

    public static Result atualizar(Long id) {
        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

        try {
            Ebean.update(tipo);
            log.info("Tipo de compromisso: '{}' atualizado", tipo.getNome());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
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

        if (tipo.isPadraoDoSistema()) {
            return badRequest("Registro padrão do sistema");
        }

        try {
            Ebean.delete(tipo);
            log.info("Tipo de compromisso deletado");
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste tipo de Compromisso, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(tipo));
    }
}
