/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceClases;

import clases.cMedico;
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
 * @author Alumno
 */
@Path("/wbMed")
public class wbMedManager {
    Gson gson = new Gson();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{usr}")
    public String ObtenMedicoJSON(@PathParam("usr") String Usr){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            cMedico med = new cMedico(user);
            if(med.getCedula()==null){
                return gson.toJson("Primera vez");
            }else{
                return gson.toJson(med);
            }
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
    @POST
    //@Consumes(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegistraMedicoJSON(@FormParam("usr") String Usr,@FormParam("nomM")String NomMed,@FormParam("ced")String Cedula,@FormParam("dir")String Direc,
                        @FormParam("esp")String Especiali,@FormParam("cel")String Cel,@FormParam("sex")String Sexo,@FormParam("clin")String Clin, @FormParam("diahors")String DiaHors){
        cUsuario user = new cUsuario();
        String[] horario = DiaHors.split(",");
        if(user.Encuentra(Usr)){
            cMedico med = new cMedico(user, NomMed, Cedula, Direc, Especiali,Long.parseLong(Cel),Sexo.charAt(0),Integer.parseInt(Clin),horario);
            String msj = med.RegDoc();
            return gson.toJson(msj);
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
}
