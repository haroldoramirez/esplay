package models.base;

import play.db.ebean.Model;

import javax.persistence.Entity;

@Entity
public class EntidadePai extends Model {

    private static final long serialVersionUID = 1L;

    protected Boolean padraoDoSistema;

    public Boolean isPadraoDoSistema() {
        return padraoDoSistema;
    }

    public void setPadraoDoSistema(Boolean padraoDoSistema) {
        this.padraoDoSistema = padraoDoSistema;
    }
}
