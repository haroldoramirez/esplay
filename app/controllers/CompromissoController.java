package controllers;

import com.avaje.ebean.Ebean;
import models.Categoria;
import models.Compromisso;
import models.Contato;
import models.Tipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.PersistenceException;

public class CompromissoController extends Controller {

    static Logger log = LoggerFactory.getLogger(CompromissoController.class);

    public static Result inserir() {
        Compromisso compromisso = Json.fromJson(request().body().asJson(), Compromisso.class);

        Compromisso compromissoBusca = Ebean.find(Compromisso.class).where().eq("titulo", compromisso.getTitulo()).findUnique();

        if (compromissoBusca != null) {
            return badRequest("Compromisso já Cadastrado");
        }

        Tipo tipo = Ebean.find(Tipo.class, compromisso.getTipo().getId());
        Categoria categoria = Ebean.find(Categoria.class, compromisso.getCategoria().getId());
        Contato contato = Ebean.find(Contato.class, compromisso.getContato().getId());

        compromisso.setTipo(tipo);
        compromisso.setCategoria(categoria);
        compromisso.setContato(contato);

        try {
            Ebean.save(compromisso);
            log.info("Criado um novo compromisso: {}", compromisso.getTitulo());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest("Erro interno de sistema");
        }

        return created(Json.toJson(compromisso));
    }

    public static Result atualizar(Long id) {
        Compromisso compromisso = Json.fromJson(request().body().asJson(), Compromisso.class);

        try {
            Ebean.update(compromisso);
            log.info("Compromisso: '{}' atualizado", compromisso.getTitulo());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(compromisso));
    }

    public static Result buscaPorId(Long id) {
        Compromisso compromisso = Ebean.find(Compromisso.class, id);

        if (compromisso == null) {
            return notFound("Compromisso não encontrado");
        }

        return ok(Json.toJson(compromisso));
    }

    public static Result remover(Long id) {
        Compromisso compromisso = Ebean.find(Compromisso.class, id);

        if (compromisso == null) {
            return notFound("Compromisso não encontrado");
        }

        try {
            Ebean.delete(compromisso);
            log.info("Compromisso deletado");
        } catch (PersistenceException e) {
            return badRequest("Existem dados que dependem deste Compromisso, remova-os primeiro");
        } catch (Exception e) {
            return badRequest("Erro interno de sistema");
        }

        return ok(Json.toJson(compromisso));
    }

    public static Result buscaTodos() {
        return ok(Json.toJson(Ebean.find(Compromisso.class).findList()));
    }
}
