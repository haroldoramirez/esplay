package controllers;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.Tipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.persistence.PersistenceException;
import java.util.Formatter;
import java.util.List;

public class TipoController extends Controller {

    static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

        Query<Tipo> query = Ebean.createQuery(Tipo.class, "find tipo where nome = :nome");
        query.setParameter("nome", tipo.getNome());
        Tipo tipoBusca = query.findUnique();

        if (tipoBusca != null) {
            return badRequest("Tipo de Compromisso já Cadastrado");
        }

        tipo.setPadraoDoSistema(false);

        try {
            Ebean.save(tipo);
            logger.info("Novo Tipo de compromisso criado: {}", tipo.getNome());
            formatter.format("Conta: '%1s' criou o usuário: '%2s'", username, tipo.getNome());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(tipo));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result atualizar(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

        if (tipo.isPadraoDoSistema()) {
            return badRequest("Registro padrão do sistema não pode ser alterado");
        }

        try {
            Ebean.update(tipo);
            logger.info("Tipo de compromisso: '{}' atualizado", tipo.getNome());
            formatter.format("Conta: '%1s' atualizou o usuário: '%2s'", username, tipo.getNome());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }
        return ok(Json.toJson(tipo));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorId(Long id) {
        Tipo tipo = Ebean.find(Tipo.class, id);

        if (tipo == null) {
            return notFound("Tipo não encontrado");
        }

        return ok(Json.toJson(tipo));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Tipo.class).findList()));
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

        Tipo tipo = Ebean.find(Tipo.class, id);

        if (tipo == null) {
            return notFound("Contato não encontrado");
        }

        if (tipo.isPadraoDoSistema()) {
            return badRequest("Registro padrão do sistema");
        }

        try {
            Ebean.delete(tipo);
            logger.info("Tipo de compromisso deletado");
            formatter.format("Conta: '%1s' deletou um tipo de compromisso.", username);
            logController.inserir(sb.toString());
        }  catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste Tipo, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(tipo));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorNome(String nome) {
        Query<Tipo> query = Ebean.createQuery(Tipo.class, "find tipo where nome = :nome");
        query.setParameter("nome", nome);
        List<Tipo> filtroDeTipos = query.findList();
        return ok(Json.toJson(filtroDeTipos));
    }


}
