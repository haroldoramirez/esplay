package controllers;

import com.feth.play.module.mail.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.convite;

import javax.persistence.Column;
import java.util.Formatter;

public class EmailController extends Controller {

    static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    static LogController logController = new LogController();

    public static class MailMe {

        @Column(nullable = false)
        private String email;

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

    //private static final Form<MailMe> FORM = form(MailMe.class);

    private static final Form<MailMe> emailForm = Form.form(MailMe.class);

    public static Result novoConvite() {
        //String username = session().get("email");
        return ok(views.html.convite.render(emailForm));
    }

    public static Result enviarEmail() {

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        String username = session().get("email");

        final Form<MailMe> filledForm = emailForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(convite.render(filledForm));
        } else {
            final String email = filledForm.get().email;
            final Mailer.Mail.Body body = new Mailer.Mail.Body(
                    views.txt.email.body.render().toString(),
                    views.html.email.body.render().toString()
            );

            final Mailer defaultMailer = Mailer.getDefaultMailer();

            {
                 //simple usage
                defaultMailer.sendMail("Convite Esplay", body, email);

            }

//            {
//                // advanced usage
//                final Mailer.Mail customMail = new Mailer.Mail("play-easymail | advanced", body, new String[]{ email });
//                //customMail.addHeader("Reply-To", email);
//                customMail.addCustomHeader("Reply-To", email);
//                //customMail.addAttachment("attachment.pdf", Play.application().getFile("conf/sample.pdf"));
//                byte[] data = "data".getBytes();
//                //customMail.addAttachment("data.txt", data, "text/plain", "A simple file", EmailAttachment.INLINE);
//                defaultMailer.sendMail(customMail);
//            }

            logger.info("Novo convite enviado: {}", email);
            formatter.format("Conta: '%1s' enviou um convite para: '%2s'", username, email);
            logController.inserir(sb.toString());
            flash("sucesso", "O convite para '" + email + "' foi enviado com sucesso!");
            return redirect(routes.EmailController.novoConvite());
        }
    }
}
