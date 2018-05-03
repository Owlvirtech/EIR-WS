/*
 * Developer: Flores López Angel Raymundo
 * Owlvirtech Inc.
 */
package WebServiceClases;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author angelraymundo
 */
@Path("/wbPac")
public class wbPacManager {
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String ModificarPac(){
        return null;
    }
}
