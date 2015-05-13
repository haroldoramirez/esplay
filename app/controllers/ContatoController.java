package controllers;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import models.Contato;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Formatter;

public class ContatoController extends Controller {

    static Logger log = LoggerFactory.getLogger(ContatoController.class);

    static LogController logController = new LogController();

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Contato contato = Json.fromJson(request().body().asJson(), Contato.class);

        Contato contatoBusca = Ebean.find(Contato.class).where().eq("nome", contato.getNome()).findUnique();

        if (contatoBusca != null) {
            return badRequest("Contato já Cadastrado");
        }

        try {
            Ebean.save(contato);
            log.info("Novo contato criado: {}", contato.getNome());
            formatter.format("Conta: '%1s' criou o contato: '%2s'", username, contato.getNome());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }
        return created(Json.toJson(contato));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result atualizar(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Contato contato = Json.fromJson(request().body().asJson(), Contato.class);

        try {
            Ebean.update(contato);
            log.info("Contato: '{}' atualizado", contato.getNome());
            formatter.format("Conta: '%1s' criou o usuário: '%2s'", username, contato.getNome());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }
        return ok(Json.toJson(contato));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorId(Long id) {
        Contato contato = Ebean.find(Contato.class, id);

        if (contato == null) {
            return notFound("Contato não encontrado");
        }

        return ok(Json.toJson(contato));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Contato.class).findList()));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result remover(Long id) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Contato contato = Ebean.find(Contato.class, id);

        if (contato == null) {
            return notFound("Contato não encontrado");
        }

        try {
            Ebean.delete(contato);
            log.info("Contato deletado");
            formatter.format("Conta: '%1s' excluiu um contato.", username);
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(contato));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }
}
