package controllers;

import com.avaje.ebean.Ebean;
import models.Tipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;
import java.util.Formatter;

public class TipoController extends Controller {

    static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

        Tipo tipoBusca = Ebean.find(Tipo.class).where().eq("nome", tipo.getNome()).findUnique();

        if (tipoBusca != null) {
            return badRequest("Tipo de Compromisso já Cadastrado");
        }

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

    public static Result atualizar(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Tipo tipo = Json.fromJson(request().body().asJson(), Tipo.class);

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
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(tipo));
    }
}
