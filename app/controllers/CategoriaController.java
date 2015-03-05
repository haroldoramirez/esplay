package controllers;

import com.avaje.ebean.Ebean;
import models.Categoria;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

public class CategoriaController extends Controller {

    public static Result inserir() {

        Categoria categoria = Json.fromJson(request().body().asJson(), Categoria.class);

        try {
            Ebean.save(categoria);
        } catch (PersistenceException e) {
            return badRequest("Categoria já Cadastrada");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }
        return created(Json.toJson(categoria));
    }

    public static Result atualizar(Long id) {

        Categoria categoria = Json.fromJson(request().body().asJson(), Categoria.class);

        try {
            Ebean.update(categoria);
        } catch (PersistenceException e) {
            return badRequest("Categoria já Cadastrada");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
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
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem a esta categoria, remova-os primeiro");
        } catch (Exception e) {
            return internalServerError("Erro interno de sistema");
        }

        return ok(Json.toJson(categoria));
    }
}
