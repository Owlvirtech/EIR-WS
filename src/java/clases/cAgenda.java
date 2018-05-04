package clases;

import java.sql.SQLException;
import java.util.*;

/**
 * Clase que contendra las citas de un usuario y permitira mostrarlas de manera
 * ordenada
 */
public class cAgenda extends cBDatos {

    public ArrayList<Integer> citas;

    /**
     * Default constructor
     */
    public cAgenda() {
    }

    /**
     * @param idUsuario
     */
    public void cAgenda(int idUsuario) {
        try {
            Conectar();
            procedure = conn.prepareCall("call GetCitasUser(?)");
            procedure.setInt(1,idUsuario);
            procedure.execute();
            sulset = procedure.getResultSet();
            while(sulset.next()){
                citas.add(sulset.getInt("idCita"));
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al obtener citas del usuario");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param Fecha
     * @return
     */
    public ArrayList<String> GetCitas(String Fecha) {
        // TODO implement here
        return null;
    }

    /**
     * @param idUsuario
     * @return
     */
    public ArrayList<String> GetCitas(int idUsuario) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<String> GetCitas() {
        // TODO implement here
        return null;
    }

    /**
     * Metodo para registrar una cita
     * (Incompleto)
     * @param Fech
     * @param Hora
     * @return
     */
    public String RegCita(String Fech, int Hora,String IdUser) {
        String ans="";
        try {
            Conectar();
            procedure = conn.prepareCall("call RegCita(?,?,?,?)");
            procedure.setString(1,IdUser);
            procedure.setString(2,Fech);
            procedure.setInt(3,Hora);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                int Num = sulset.getInt("id");
                if(Num!=0){
                    ans="Registrado";
                }else{
                    ans="Ya existe";
                }
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al registrar la cita");
            System.out.println(ex.getMessage());
        }
        return ans;
    }

    /**
     * @param idCita
     * @return
     */
    public void DelCita(int idCita) {
        // TODO implement here
    }

    /**
     * @return
     */
    public void DelTodasCitas() {
        // TODO implement here
    }

}
