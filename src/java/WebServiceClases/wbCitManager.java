/*
 * Developer: Flores López Angel Raymundo
 * Owlvirtech Inc.
 */
package WebServiceClases;

import clases.cAgenda;
import clases.cPaciente;
import clases.cUsuario;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author angelraymundo
 */
@Path("/wbCts")
public class wbCitManager {
    Gson gson = new Gson();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{usr}")
    public String ObtenCitasUsr(@PathParam("usr") String Usr){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            user.GeneraAgenda();
            cAgenda agend = user.getAgenda();
            return gson.toJson(agend.GetCitas());
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegistraCita(@FormParam("usr")String User,@FormParam("fech")String Fecha,@FormParam("hora")String Hora,@FormParam("clin")String Clini){
        cUsuario usr = new cUsuario();
        if(usr.Encuentra(User)){
            usr.GeneraAgenda();
            return gson.toJson(usr.AgendaCita(Fecha, Hora,Integer.parseInt(Clini)));
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
}
