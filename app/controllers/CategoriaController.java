package controllers;

import com.avaje.ebean.Ebean;
import models.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

public class CategoriaController extends Controller {

    static Logger log = LoggerFactory.getLogger(CategoriaController.class);

    public static Result inserir() {

        Categoria categoria = Json.fromJson(request().body().asJson(), Categoria.class);

        Categoria categoriaBusca = Ebean.find(Categoria.class).where().eq("nome", categoria.getNome()).findUnique();

        if (categoriaBusca != null) {
            return badRequest("Categoria de Compromisso já Cadastrado");
        }

        try {
            Ebean.save(categoria);
            log.info("Nova categoria de compromisso criada: {}", categoria.getNome());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(categoria));
    }

    public static Result atualizar(Long id) {

        Categoria categoria = Json.fromJson(request().body().asJson(), Categoria.class);

        try {
            Ebean.update(categoria);
            log.info("Categoria de compromisso: '{}' atualizada", categoria.getNome());
        } catch (PersistenceException e) {
            return badRequest("Categoria já Cadastrada");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }
        return ok(Json.toJson(categoria));
    }

    public static Result buscaPorId(Long id) {

        Categoria categoria = Ebean.find(Categoria.class, id);

        if (categoria == null) {
            return notFound("Categoria não encontrada");
        }

        return ok(Json.toJson(categoria));
    }
    
    public static Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Categoria.class).findList()));
    }

    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }

    public static Result remover(Long id) {
        Categoria categoria = Ebean.find(Categoria.class, id);

        if (categoria == null) {
            return notFound("Categoria não encontrada");
        }

        try {
            Ebean.delete(categoria);
            log.info("Categoria de compromisso deletada");
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem desta categoria de Compromisso, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(categoria));
    }
}
