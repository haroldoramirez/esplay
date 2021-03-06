package controllers;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.Categoria;
import models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.persistence.PersistenceException;
import java.util.Formatter;
import java.util.List;

public class CategoriaController extends Controller {

    static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Categoria categoria = Json.fromJson(request().body().asJson(), Categoria.class);

        Query<Categoria> query = Ebean.createQuery(Categoria.class, "find categoria where nome = :nome and dono.email = :email");
        query.setParameter("email", username);
        query.setParameter("nome", categoria.getNome());
        Categoria categoriaBusca = query.findUnique();

        if (categoriaBusca != null) {
            return badRequest("Categoria de Compromisso já Cadastrado");
        }

        Usuario dono = Ebean.find(Usuario.class).where().eq("email", username).findUnique();

        categoria.setDono(dono);

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

    @Security.Authenticated(PlayAuthenticatedSecured.class)
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

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorId(Long id) {

        Categoria categoria = Ebean.find(Categoria.class, id);

        if (categoria == null) {
            return notFound("Categoria não encontrada");
        }

        return ok(Json.toJson(categoria));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaTodos() {
        String username = session().get("email");
        Query<Categoria> query = Ebean.createQuery(Categoria.class, "find categoria where dono.email = :email");
        query.setParameter("email", username);
        List<Categoria> listaDeCategorias = query.findList();
        return ok(Json.toJson(listaDeCategorias));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
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
        }  catch (PersistenceException e) {
            return badRequest("Existem dados que dependem desta categoria, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(categoria));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorNome(String nome) {
        //busca categoria atraves do nome que recebe por parametro e onde o dono é o usuario logado no sistema
        String username = session().get("email");
        Query<Categoria> query = Ebean.createQuery(Categoria.class, "find categoria where nome like = :nome and dono.email = :email");
        query.setParameter("email", username);
        query.setParameter("nome", "%" + nome + "%");
        List<Categoria> filtroDeCategorias = query.findList();
        return ok(Json.toJson(filtroDeCategorias));
    }
}
