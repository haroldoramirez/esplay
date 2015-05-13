package models;

import models.base.EntidadePai;
import play.libs.Json;

import javax.persistence.*;

@Entity
public class Tipo extends EntidadePai {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    //muitas categorias para um usuario
    //@ManyToOne
    //public Usuario dono;

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
