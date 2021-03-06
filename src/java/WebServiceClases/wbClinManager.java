/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceClases;

import clases.cClinica;
import clases.cMedico;
import clases.cServHandler;
import clases.cToolBoxFlar;
import clases.cUsuario;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alumno
 */
@Path("/wbClin")
public class wbClinManager {
    Gson gson = new Gson();
    cServHandler serv = new cServHandler();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{usr}")
    public String ObtenClinicaJSON(@PathParam("usr") String Usr){
        cUsuario user = new cUsuario();
        cClinica cli = null;
        if(user.Encuentra(Usr)){
            cli = new cClinica(user);
            if(cli.getRfc()!=null){
                return gson.toJson(cli);
            }else{
                return gson.toJson("Primera vez");
            }
        }else{
            return gson.toJson("No existe el usuario");
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String RegClinicaHTML(@FormParam("usr") String Usr,@FormParam("nomC") String NomCli,@FormParam("dir") String Direc,
                                @FormParam("nomR") String NomRep,@FormParam("rfc") String RFC,@FormParam("tel") Long Tel){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            cClinica clin = new cClinica(user,NomCli,Direc,NomRep,RFC,Tel);
            String msj = clin.RegClinic();
            return "<script>alert('"+msj+"');location.href='"+serv.getClientURL()+"/Consultorio/perfilClinica.jsp?usr="+user.getUser()+"'</script>";
        }else{
            return "<script>alert('No existe el usuario');location.href='"+serv.getClientURL()+"/Consultorio/perfilClinica.jsp'</script>";
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String RegClinicaJSON(@FormParam("usr") String Usr,@FormParam("nomC") String NomCli,@FormParam("dir") String Direc,
                                @FormParam("nomR") String NomRep,@FormParam("rfc") String RFC,@FormParam("tel") Long Tel){
        cUsuario user = new cUsuario();
        if(user.Encuentra(Usr)){
            cClinica clin = new cClinica(user,NomCli,Direc,NomRep,RFC,Tel);
            String msj = clin.RegClinic();
            return gson.toJson(msj);
        }else{
            return gson.toJson("No exite el usuario");
        }
    }
}
