/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceClases;

import clases.cServHandler;
import clases.cUsuario;
import com.google.gson.Gson;
import java.io.IOException;
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
    cServHandler serv = new cServHandler();
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String RegistrarUserHTML(@FormParam("usr") String User,@FormParam("eml") String Mail,@FormParam("psw") String Pass,@FormParam("lvl") String Niv){
        if(Niv.equals("Paciente")){
            Niv="3";
        }else if(Niv.equals("Médico")){
            Niv="2";
        }else if(Niv.equals("Consultorio")){
            Niv="1";
        }
        cUsuario user = new cUsuario(User,Mail,Integer.parseInt(Niv));
        String msj = user.RegUsr(Pass);
        return "<script>alert('"+msj+"');location.href='"+serv.getClientURL()+"/index.html'</script>";
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegistrarUserJSON(@FormParam("usr") String User,@FormParam("eml") String Mail,@FormParam("psw") String Pass,@FormParam("lvl") String Niv){
        if(Niv.equals("Paciente")){
            Niv="3";
        }else if(Niv.equals("Médico")){
            Niv="2";
        }else if(Niv.equals("Consultorio")){
            Niv="1";
        }
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
