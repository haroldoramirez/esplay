package models;

import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

@Entity
public class Compromisso extends Model {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInicio;

//    @Temporal(TemporalType.TIME)
//    private Time horaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar horaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFim;

//    @Temporal(TemporalType.TIME)
//    private Time horaFim;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar horaFim;

    private String descricao;

    private String observacoes;

    private String local;

    @Enumerated(EnumType.STRING)
    private Status status;

    //muitos compromissos para um tipo
    @ManyToOne
    @Column(nullable = false)
    private Tipo tipo;

    //muitos compromissos para uma categoria
    @ManyToOne
    @Column(nullable = false)
    private Categoria categoria;

    //muitos compromissos para um contato
    @ManyToOne
    @Column(nullable = false)
    private Contato contato;

    //muitos compromissos para um usuario
    @ManyToOne
    @Column(nullable = false)
    private Usuario dono;


    //muitos compromissos para muitos usuarios
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    //implementar depois nao apagar
    //muitos compromissos para muitas categorias
    //@ManyToMany
    //private List<Categoria> categorias;

    //implementar depois nao apagar
    //muitos compromissos para muitos contatos
    //@ManyToMany
    //private List<Contato> contatos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public Calendar getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Calendar horaFim) {
        this.horaFim = horaFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
