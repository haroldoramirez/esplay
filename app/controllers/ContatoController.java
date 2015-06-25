package controllers;

import actions.PlayAuthenticatedSecured;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.Contato;
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

public class ContatoController extends Controller {

    static Logger log = LoggerFactory.getLogger(ContatoController.class);

    static LogController logController = new LogController();

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result inserir() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        Contato contato = Json.fromJson(request().body().asJson(), Contato.class);

        //Contato contatoBusca = Ebean.find(Contato.class).where().eq("nome", contato.getNome()).findUnique();

        Query<Contato> query = Ebean.createQuery(Contato.class, "find contato where nome = :nome and dono.email = :email");
        query.setParameter("email", username);
        query.setParameter("nome", contato.getNome());
        Contato contatoBusca = query.findUnique();

        if (contatoBusca != null) {
            return badRequest("Contato já Cadastrado");
        }

        Usuario dono = Ebean.find(Usuario.class).where().eq("email", username).findUnique();

        contato.setDono(dono);

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

        if (contato.getDono() != null && !contato.getDono().getEmail().equals(username)) {
            return badRequest("Você não é o dono deste usuário");
        }

        if (contato.getDono() == null) {
            return badRequest("Este contato só pode ser alterado através da lista de usuários");
        }

        try {
            Ebean.update(contato);
            log.info("Contato: '{}' atualizado", contato.getNome());
            formatter.format("Conta: '%1s' atualizou o usuário: '%2s'", username, contato.getNome());
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
        String username = session().get("email");
        //Query<Contato> query = Ebean.createQuery(Contato.class, "find contato where dono.email = :email or isnull(dono)");
        Query<Contato> query = Ebean.createQuery(Contato.class, "find contato where dono.email = :email");
        query.setParameter("email", username);
        List<Contato> listaDeContatos = query.findList();
        return ok(Json.toJson(listaDeContatos));
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

//        if (contato.getDono() != null && !contato.getDono().getEmail().equals(username)) {
//            return badRequest("Você não é o dono deste usuário");
//        }
//
//        if (contato.getDono() == null) {
//            return badRequest("Este contato é excluido juntamente com o usuário");
//        }

        try {
            Ebean.delete(contato);
            log.info("Contato deletado");
            formatter.format("Conta: '%1s' excluiu um contato.", username);
            logController.inserir(sb.toString());
        }  catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste contato, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(contato));
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorPaginas(Long pagina) {
        return TODO;
    }

    @Security.Authenticated(PlayAuthenticatedSecured.class)
    public static Result buscaPorNome(String nome) {
        //busca categoria atraves do nome que recebe por parametro e onde o dono é o usuario logado no sistema
        String username = session().get("email");
        Query<Contato> query = Ebean.createQuery(Contato.class, "find contato where nome = :nome and dono.email = :email");
        query.setParameter("email", username);
        query.setParameter("nome", nome);
        List<Contato> filtroDeContatos = query.findList();
        return ok(Json.toJson(filtroDeContatos));
    }
}
