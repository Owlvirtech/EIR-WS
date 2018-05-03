/*
 * Developer: Flores López Angel Raymundo
 * Owlvirtech Inc.
 */
package WebServiceClases;

import clases.cPaciente;
import clases.cUsuario;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author angelraymundo
 */
@Path("/wbPac")
public class wbPacManager {
    Gson gson = new Gson();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{usr}")
    public String ObtenPac(@PathParam("usr") String Usr){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            cPaciente pac = new cPaciente(user);
            if(pac.getCurp()==null){
                return gson.toJson("Primera vez");
            }else{
                return gson.toJson(pac);
            }
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegPac(@FormParam("usr") String Usr,@FormParam("nomP")String NomPac,@FormParam("fil")String Filial,@FormParam("numFil")String NumFil,
                        @FormParam("cel")String Cel,@FormParam("curp")String Curp,@FormParam("edad")String Edad,@FormParam("sex")String Sexo){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            cPaciente pac = new cPaciente(user, NomPac, Filial, NumFil,Long.parseLong(Cel), Curp,Integer.parseInt(Edad), Sexo.charAt(0));
            String msj = pac.RegPac();
            return gson.toJson(msj);
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String ModificarPac(){
        return null;
    }
}
