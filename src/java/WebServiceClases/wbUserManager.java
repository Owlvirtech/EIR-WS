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
import javax.ws.rs.PUT;
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Consumes(MediaType.TEXT_PLAIN)
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegistrarUserForm(@FormParam("usr") String User,@FormParam("eml") String Mail,@FormParam("psw") String Pass,@FormParam("lvl") String Niv){
        cUsuario user = new cUsuario(User,Mail,Integer.parseInt(Niv));
        return gson.toJson(user.RegUsr(Pass));
    }
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String ModificarUserForm(@FormParam("idUsr") String IdUser,@FormParam("usr")String User,@FormParam("eml") String Mail,@FormParam("psw") String Pass){
        cUsuario user = new cUsuario();
        user.setIdUsuario(Integer.parseInt(IdUser));
        user.setEmail(Mail);
        user.setUser(User);
        return gson.toJson(user.ModUsr(Pass));
    }
}
