/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceClases;

import clases.cClinica;
import clases.cMedico;
import clases.cUsuario;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
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
@Path("/wbClin")
public class wbClinManager {
    Gson gson = new Gson();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{usr}")
    public String ObtenClinica(@PathParam("usr") String Usr){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            cClinica cli = new cClinica(user);
            if(cli.getTelefono()==null){
                return gson.toJson("Primera vez");
            }else{
                return gson.toJson(cli);
            }
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegClinica(String Usr,String NomCli, String Direc, String NomRep, Long Tel){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            cClinica clin = new cClinica(user,NomCli,Direc,NomRep,Tel);
            String msj = clin.RegClinic();
            return gson.toJson(msj);
        }else{
            return gson.toJson("No exite el usuario");
        }
    }
}
