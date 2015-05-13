package controllers;

import com.avaje.ebean.Ebean;
import models.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;
import java.util.Formatter;

public class CategoriaController extends Controller {

    static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Categoria categoria = Json.fromJson(request().body().asJson(), Categoria.class);

        Categoria categoriaBusca = Ebean.find(Categoria.class).where().eq("nome", categoria.getNome()).findUnique();

        if (categoriaBusca != null) {
            return badRequest("Categoria de Compromisso já Cadastrado");
        }

        try {
            Ebean.save(categoria);
            logger.info("Nova categoria de compromisso criada: {}", categoria.getNome());
            formatter.format("Conta: '%1s' criou a categoria de compromisso: '%2s'", username, categoria.getNome());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(categoria));
    }

    public static Result atualizar(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Categoria categoria = Json.fromJson(request().body().asJson(), Categoria.class);

        try {
            Ebean.update(categoria);
            logger.info("Categoria de compromisso: '{}' atualizada", categoria.getNome());
            formatter.format("Conta: '%1s' atualizou a categoria de compromisso: '%2s'", username, categoria.getNome());
            logController.inserir(sb.toString());
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

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Categoria categoria = Ebean.find(Categoria.class, id);

        if (categoria == null) {
            return notFound("Categoria não encontrada");
        }

        try {
            Ebean.delete(categoria);
            logger.info("Categoria de compromisso deletada");
            formatter.format("Conta: '%1s' deletou uma categoria de compromisso: ", username);
            logController.inserir(sb.toString());
        }catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(categoria));
    }
}
