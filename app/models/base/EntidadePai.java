package models.base;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class EntidadePai extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected boolean padraoDoSistema;

    public boolean isPadraoDoSistema() {
        return padraoDoSistema;
    }

    public void setPadraoDoSistema(boolean padraoDoSistema) {
        this.padraoDoSistema = padraoDoSistema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
