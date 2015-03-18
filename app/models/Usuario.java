package models;

import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

@Entity
public class Usuario extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String nome;

    //muitos usuarios para uma agenda
    @ManyToOne
    public Agenda agenda;

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
