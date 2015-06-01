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
import java.util.ArrayList;
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

        Query<Compromisso> query = Ebean.createQuery(Compromisso.class, "find compromisso where titulo = :titulo and dono.email = :email");
        query.setParameter("email", username);
        query.setParameter("titulo", compromisso.getTitulo());
        Compromisso compromissoBusca = query.findUnique();

        if (compromissoBusca != null) {
            return badRequest("Compromisso já Cadastrado");
        }

        Tipo tipo = Ebean.find(Tipo.class, compromisso.getTipo().getId());
        Categoria categoria = Ebean.find(Categoria.class, compromisso.getCategoria().getId());
        Contato contato = Ebean.find(Contato.class, compromisso.getContato().getId());

        List<Usuario> usuarios = new ArrayList<Usuario>();
        if(compromisso.getUsuarios() != null) {
            for (Usuario u:compromisso.getUsuarios()) {
                Usuario a = Ebean.find(Usuario.class, u.getId());
                if (a != null) {
                    usuarios.add(a);
                }
            }
        }

        Usuario dono = Ebean.find(Usuario.class).where().eq("email", username).findUnique();

        //List<Contato> contatos;

        compromisso.setTipo(tipo);
        compromisso.setCategoria(categoria);
        compromisso.setContato(contato);
        compromisso.setUsuarios(usuarios);
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

        if (compromisso.getDono() != null && !compromisso.getDono().getEmail().equals(username)) {
            return badRequest("Só o usuário criador do compromisso pode alterá-lo");
        }

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

        if (compromisso.getDono() != null && !compromisso.getDono().getEmail().equals(username)) {
            return badRequest("Apenas o criador do compromisso pode remover");
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

        Usuario usuarioAtual = Ebean.createQuery(Usuario.class, "find usuario where email = :email")
                .setParameter("email", username)
                .findUnique();

        Query<Compromisso> query = Ebean.createQuery(Compromisso.class, "find compromisso fetch usuarios " +
                "where dono.id = :idUsuario " +
                "or usuarios.email = :email ");
                //"or contatos.id in select contato.id from contato join usuario as u where u.email = :email ");
        query.setParameter("idUsuario", usuarioAtual.getId());
        query.setParameter("email", usuarioAtual.getEmail());
        //query.setParameter("email", username);
        List<Compromisso> listaDeCompromissos = query.findList();
        return ok(Json.toJson(listaDeCompromissos));
    }
}
