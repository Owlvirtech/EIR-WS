/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceClases;

import clases.cUsuario;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alumno
 */
@Path("/wbUsr")
public class wbUserManager {
    Gson gson = new Gson();
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String RegistrarUserPlain(@FormParam("usr") String User,@FormParam("eml") String Mail,@FormParam("psw") String Pass,@FormParam("lvl") String Niv){
        System.out.println(User+", "+Mail+", "+Pass+", "+Niv);
        cUsuario user = new cUsuario(User,Mail,Integer.parseInt(Niv));
        return gson.toJson(user.RegUsr(Pass));
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegistrarUserJson(@FormParam("usr") String User,@FormParam("eml") String Mail,@FormParam("psw") String Pass,@FormParam("lvl") String Niv){
        System.out.println(User+", "+Mail+", "+Pass+", "+Niv);
        cUsuario user = new cUsuario(User,Mail,Integer.parseInt(Niv));
        return gson.toJson(user.RegUsr(Pass));
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String RegistrarUserForm(@FormParam("usr") String User,@FormParam("eml") String Mail,@FormParam("psw") String Pass,@FormParam("lvl") String Niv){
        System.out.println(User+", "+Mail+", "+Pass+", "+Niv);
        cUsuario user = new cUsuario(User,Mail,Integer.parseInt(Niv));
        return gson.toJson(user.RegUsr(Pass));
    }
}
