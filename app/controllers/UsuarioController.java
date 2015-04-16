package controllers;

import actions.PlayAuthenticatedSecured;
import akka.util.Crypt;
import com.avaje.ebean.Ebean;
import models.Usuario;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.persistence.PersistenceException;

public class UsuarioController extends Controller {

    public static Result inserir() {
        Usuario usuario = Json.fromJson(request().body().asJson(), Usuario.class);

        Usuario usuarioBusca = Ebean.find(Usuario.class).where().eq("email", usuario.getEmail()).findUnique();

        if (usuarioBusca != null) {
            return badRequest("Usuário já Cadastrado");
        }

        String senha = Crypt.sha1(usuario.getSenha());

        usuario.setSenha(senha);

        try {
            Ebean.save(usuario);
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(usuario));
    }

    public static Result atualizar(Long id) {
        Usuario usuario = Json.fromJson(request().body().asJson(), Usuario.class);

        try {
            Ebean.update(usuario);
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
