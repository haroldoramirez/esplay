package models.base;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EntidadePai extends Model {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    protected boolean padraoDoSistema;

    public boolean isPadraoDoSistema() {
        return padraoDoSistema;
    }

    public void setPadraoDoSistema(boolean padraoDoSistema) {
        this.padraoDoSistema = padraoDoSistema;
    }
}
