package models;

import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

@Entity
public class Contato extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String nome;

    //um contato tem um usuario
    @OneToOne
    public Usuario dono;

    //varios contatos tem um usuario
    @ManyToOne
    public Usuario usuario;

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
