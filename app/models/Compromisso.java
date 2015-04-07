package models;

import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Compromisso extends Model {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar horaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFim;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar horaFim;

    private String descricao;

    private String observacoes;

    private String local;

    @Enumerated(EnumType.STRING)
    private Status status;

    //muitos compromissos para uma agenda
    @ManyToOne
    private Agenda agenda;

   //muitos compromissos para muitos contatos
    @ManyToMany
    private List<Contato> contatos;

    //muitos compromissos para um usuario
    @ManyToOne
    private Usuario usuario;

    //muitos compromissos para um tipo
    @ManyToOne
    @Column(nullable = false)
    private Tipo tipo;

    //muitos compromissos para muitas categorias
//    @ManyToMany
//    private List<Categoria> categorias;

    //muitos compromissos para uma categoria
    @ManyToOne
    @Column(nullable = false)
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Json.toJson(this).toString();
    }
}
