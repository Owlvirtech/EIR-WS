/*
 * Developer: Flores López Angel Raymundo
 * Owlvirtech Inc.
 */
package WebServiceClases;

import clases.cPaciente;
import clases.cServHandler;
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
    cServHandler serv = new cServHandler();
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String ValidaAccesoJSON(@FormParam("usr")String User,@FormParam("psw")String Pass){
        cUsuario user = new cUsuario();
        if(user.Encuentra(User)){
            if(user.ValidaA(Pass)){
                return gson.toJson(user);
            }else{
                return gson.toJson("Invalido");
            }
        }else{
            return gson.toJson("Invalido");
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String ValidaAccesoHTML(@FormParam("usr")String User,@FormParam("psw")String Pass){
        cUsuario user = new cUsuario();
        if(user.Encuentra(User)){
            if(user.ValidaA(Pass)){
                switch (user.getNivel()) {
                    case 1:
                        return "<script>location.href='"+serv.getClientURL()+"/Consultorio/perfilClinica.jsp?usr="+user.getUser()+"'</script>";
                    case 2:
                        return "<script>location.href='"+serv.getClientURL()+"/Doctor/perfilMedico.jsp?usr="+user.getUser()+"'</script>";
                    case 3:
                        return "<script>location.href='"+serv.getClientURL()+"/Paciente/perfilPaciente.jsp?usr="+user.getUser()+"'</script>";
                    default:
                        return "<script>alert('invalido');location.href='"+serv.getClientURL()+"/index.html'</script>";
                }
            }else{
                return "<script>alert('invalido');location.href='"+serv.getClientURL()+"/index.html'</script>";
            }
        }else{
            return "<script>alert('invalido');location.href='"+serv.getClientURL()+"/index.html'</script>";
        }
    }
}
