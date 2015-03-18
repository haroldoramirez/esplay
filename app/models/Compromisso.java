package models;

import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;

@Entity
public class Compromisso extends Model {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String nome;

    //muitos compromissos para uma agenda
    @ManyToOne
    public Agenda agenda;

   //muitos compromissos para muitos contatos
   @ManyToMany
    public List<Contato> contatos;

    //muitos compromissos para um usuario
    @ManyToOne
    public Usuario usuario;

    //muitos compromissos para um tipo
    @ManyToOne
    public Tipo tipo;

    //muitos compromissos para muitas categorias
    @ManyToMany
    public List<Categoria> categorias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return Json.toJson(this).toString();
    }
}
