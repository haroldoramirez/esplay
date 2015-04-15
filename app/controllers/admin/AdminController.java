package controllers.admin;

import actions.PlayAuthenticatedSecured;
import play.mvc.Controller;
import play.mvc.Security;

@Security.Authenticated(PlayAuthenticatedSecured.class)
public class AdminController extends Controller {

}
