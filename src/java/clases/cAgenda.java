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
    public cAgenda(int idUsuario) {
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
     * @param Fech formato year-month-day
     * @param Hora
     * @return
     */
    public String RegCita(String Fech,String Hora,int Clinica,String IdUser) {
        GregorianCalendar cal = new GregorianCalendar();
        String[] datosFech = Fech.split("-");
	cal.setTime(new Date(Integer.parseInt(datosFech[0]),Integer.parseInt(datosFech[1]),Integer.parseInt(datosFech[2])));
	int dia = cal.get(Calendar.DAY_OF_WEEK);//1=Domingo 2=Lunes
        String ans="";
        try {
            Conectar();
            procedure = conn.prepareCall("call RegCita(?,?,?,?)");
            procedure.setString(1,IdUser);
            procedure.setString(2,Fech);
            procedure.setString(3,Hora);
            procedure.setInt(4,Clinica);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                ans = sulset.getString("msj");
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
