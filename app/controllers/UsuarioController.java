package controllers;

import actions.PlayAuthenticatedSecured;
import akka.util.Crypt;
import com.avaje.ebean.Ebean;
import models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.persistence.PersistenceException;
import java.util.Formatter;

public class UsuarioController extends Controller {

    static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Usuario usuario = Json.fromJson(request().body().asJson(), Usuario.class);

        //faz uma busca no banco de dados para ver se esse usuário já esteja cadastrado na base de dados
        Usuario usuarioUnico = Ebean.find(Usuario.class).where().eq("email", usuario.getEmail()).findUnique();

        //verifica se o usuario foi encontrado
        if (usuarioUnico != null) {
            return badRequest("Usuário já Cadastrado");
        }

        //busca o usuário atual que esteja logado no sistema
        Usuario usuarioAtual = Ebean.createQuery(Usuario.class, "find usuario where email = :email")
                .setParameter("email", username)
                .findUnique();

        //verificar se o usuario atual encontrado é administrador
        if (usuarioAtual.getPrivilegio() == 1) {
            return badRequest("Você não tem privilégios de Administrador");
        }

        String senha = Crypt.sha1(usuario.getSenha());

        usuario.setSenha(senha);
        usuario.setPadraoDoSistema(false);

        //usuario.getContato().setId(null);

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

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result atualizar(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Usuario usuario = Json.fromJson(request().body().asJson(), Usuario.class);

        if (usuario.getPadraoDoSistema() == true) {
            return badRequest("Registro padrão do sistema");
        }

        //busca o usuário atual que esteja logado no sistema
        Usuario usuarioAtual = Ebean.createQuery(Usuario.class, "find usuario where email = :email")
                .setParameter("email", username)
                .findUnique();

        //verificar se o usuario atual encontrado é administrador
        if (usuarioAtual.getPrivilegio() == 1) {
            return badRequest("Você não tem privilégios de Administrador");
        }

        try {
            //atualiza as informações do usuário
            Ebean.update(usuario);
            logger.info("Usuario atualizado");
            formatter.format("Conta: '%1s' atualizou o usuário: '%2s'", username, usuario.getEmail());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }
        return ok(Json.toJson(usuario));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorId(Long id) {
        Usuario usuario = Ebean.find(Usuario.class, id);

        if (usuario == null) {
            return notFound("Usuário não encontrado");
        }

        return ok(Json.toJson(usuario));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result remover(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Usuario usuario = Ebean.find(Usuario.class, id);

        if (usuario == null) {
            return notFound("Usuário não encontrado");
        }

        if (usuario.getPadraoDoSistema() == true) {
            return badRequest("Registro padrão do sistema");
        }

        //busca o usuário atual que esteja logado no sistema
        Usuario usuarioAtual = Ebean.createQuery(Usuario.class, "find usuario where email = :email")
                .setParameter("email", username)
                .findUnique();

        //verificar se o usuario atual encontrado é administrador
        if (usuarioAtual.getPrivilegio() == 1) {
            return badRequest("Você não tem privilégios de Administrador");
        }

        if (usuarioAtual.getEmail().equals(usuario.getEmail())) {
            return badRequest("Não remover sua própria conta de usuário");
        }

        try {
            Ebean.delete(usuario);
            logger.info("Usuario deletado");
            formatter.format("Conta: '%1s' deletou um usuário", username);
            logController.inserir(sb.toString());
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

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }
}
