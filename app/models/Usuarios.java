package models;

import com.avaje.ebean.Ebean;
import play.libs.F;

public class Usuarios {

    public static F.Option<Usuario> existe(String email, String senha) {
        Usuario usuario = Ebean.find(Usuario.class).where().eq("email", email).eq("senha", senha).findUnique();

        if (usuario == null) {
            return F.Option.<Usuario>None();
        }

        return F.Option.Some(usuario);
    }
}
