package controllers;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import javax.persistence.PersistenceException;
import java.util.Formatter;
import java.util.List;

public class CompromissoController extends Controller {

    static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Compromisso compromisso = Json.fromJson(request().body().asJson(), Compromisso.class);

        Compromisso compromissoBusca = Ebean.find(Compromisso.class).where().eq("titulo", compromisso.getTitulo()).findUnique();

        if (compromissoBusca != null) {
            return badRequest("Compromisso já Cadastrado");
        }

        Tipo tipo = Ebean.find(Tipo.class, compromisso.getTipo().getId());
        Categoria categoria = Ebean.find(Categoria.class, compromisso.getCategoria().getId());
        Contato responsavel = null;
        if(compromisso.getResponsavel() != null)
            responsavel = Ebean.find(Contato.class, compromisso.getResponsavel().getId());
        Usuario dono = Ebean.find(Usuario.class).where().eq("email", username).findUnique();

        //List<Contato> contatos;

        compromisso.setTipo(tipo);
        compromisso.setCategoria(categoria);
        compromisso.setResponsavel(responsavel);
        compromisso.setDono(dono);

        try {
            Ebean.save(compromisso);
            logger.info("Criado um novo compromisso: {}", compromisso.getTitulo());
            formatter.format("Conta: '%1s' criou o compromisso: '%2s'", username, compromisso.getTitulo());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(compromisso));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result atualizar(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Compromisso compromisso = Json.fromJson(request().body().asJson(), Compromisso.class);

        try {
            Ebean.update(compromisso);
            logger.info("Compromisso: '{}' atualizado", compromisso.getTitulo());
            formatter.format("Conta: '%1s' atualizou o compromisso: '%2s'", username, compromisso.getTitulo());
            logController.inserir(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(compromisso));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorId(Long id) {
        Compromisso compromisso = Ebean.find(Compromisso.class, id);

        if (compromisso == null) {
            return notFound("Compromisso não encontrado");
        }

        return ok(Json.toJson(compromisso));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result remover(Long id) {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Compromisso compromisso = Ebean.find(Compromisso.class, id);

        if (compromisso == null) {
            return notFound("Compromisso não encontrado");
        }

        try {
            Ebean.delete(compromisso);
            logger.info("Compromisso deletado");
            formatter.format("Conta: '%1s' deletou um compromisso", username);
            logController.inserir(sb.toString());
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste Compromisso, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(compromisso));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaTodos() {

        //faz listagem se for responsável do compromisso e se ele estiver compartilhado
        String username = session().get("email");

        Usuario usuarioAtual = Ebean.createQuery(Usuario.class, "find usuario fetch contato where email = :email")
                .setParameter("email", username)
                .findUnique();

        Query<Compromisso> query = Ebean.createQuery(Compromisso.class, "find compromisso fetch responsavel fetch contatos " +
                "where dono.id = :idUsuario " +
                "or responsavel.id = :idContato ");
                //"or contatos.id in select contato.id from contato join usuario as u where u.email = :email ");
        query.setParameter("idUsuario", usuarioAtual.getId());
        query.setParameter("idContato", usuarioAtual.getContato().getId());
        //query.setParameter("email", username);
        List<Compromisso> listaDeCompromissos = query.findList();
        return ok(Json.toJson(listaDeCompromissos));
    }
}
