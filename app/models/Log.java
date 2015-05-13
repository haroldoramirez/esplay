package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Log extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataDoLog;

    private String mensagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDoLog() {
        return dataDoLog;
    }

    public void setDataDoLog(Date dataDoLog) {
        this.dataDoLog = dataDoLog;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
