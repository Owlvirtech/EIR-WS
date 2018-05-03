/*
 * Developer: Flores López Angel Raymundo
 * Owlvirtech Inc.
 */
package WebServiceClases;

import clases.cPaciente;
import clases.cUsuario;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import javax.ws.rs.FormParam;

/**
 *
 * @author angelraymundo
 */
@Path("/wbAcs")
public class wbAcceso {
    Gson gson = new Gson();
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String ValidaAcceso(@FormParam("usr")String User,@FormParam("psw")String Pass){
        cUsuario user = new cUsuario();
        if(user.Encuentra(User)){
            if(user.ValidaA(Pass)){
                switch (user.getNivel()) {
                    case 1:
                        // Si es director de la clinica
                        return gson.toJson("Building");
                    case 2:
                        // Si es médico
                        return gson.toJson("Building");
                    case 3:
                        //Si es paciente
                        return gson.toJson(new cPaciente(user));
                    default:
                        return gson.toJson("Nivel desconocido");
                }
            }else{
                return gson.toJson("Invalido");
            }
        }else{
            return gson.toJson("Invalido");
        }
    }
}
