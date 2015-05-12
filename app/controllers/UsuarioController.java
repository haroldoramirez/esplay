package controllers;

import actions.PlayAuthenticatedSecured;
import akka.util.Crypt;
import com.avaje.ebean.Ebean;
import models.Log;
import models.Usuario;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.persistence.PersistenceException;
import java.util.Formatter;

public class UsuarioController extends Controller {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    public static Result inserir() {

        StringBuilder sb = new StringBuilder();

        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Usuario usuario = Json.fromJson(request().body().asJson(), Usuario.class);

        Usuario usuarioBusca = Ebean.find(Usuario.class).where().eq("email", usuario.getEmail()).findUnique();

        if (usuarioBusca != null) {
            return badRequest("Usuário já Cadastrado");
        }

        String senha = Crypt.sha1(usuario.getSenha());

        usuario.setSenha(senha);

        try {
            Ebean.save(usuario);
            logger.info("Conta: '{}' criou o usuário: {}", username, usuario.getEmail());
            formatter.format("Conta: '%1s' criou o usuário: '%2s'", username, usuario.getEmail());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(usuario));
    }

    public static Result atualizar(Long id) {
        Usuario usuario = Json.fromJson(request().body().asJson(), Usuario.class);

        try {
            Ebean.update(usuario);
            logger.info("Usuario atualizado");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }
        return ok(Json.toJson(usuario));
    }

    public static Result buscaPorId(Long id) {
        Usuario usuario = Ebean.find(Usuario.class, id);

        if (usuario == null) {
            return notFound("Usuário não encontrado");
        }

        return ok(Json.toJson(usuario));
    }

    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }

    public static Result remover(Long id) {
        Usuario usuario = Ebean.find(Usuario.class, id);

        if (usuario == null) {
            return notFound("Usuário não encontrado");
        }

        if (usuario.isPadraoDoSistema()) {
            return badRequest("Registro padrão do sistema");
        }

        try {
            Ebean.delete(usuario);
            logger.info("Usuario deletado");
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste usuário, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(usuario));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Usuario.class).findList()));
    }
}
