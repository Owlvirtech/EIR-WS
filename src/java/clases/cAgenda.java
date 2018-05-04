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
     * @param Clinica
     * @param IdUser
     * @return
     */
    public String RegCita(String Fech,String Hora,int Clinica,String IdUser) {
        String ans="";
        String nombreDia = "";
        try {
            Conectar();
            GregorianCalendar cal = new GregorianCalendar();
            String[] datosFech = Fech.split("-");
            cal.setTime(new Date(Integer.parseInt(datosFech[0]),Integer.parseInt(datosFech[1]),Integer.parseInt(datosFech[2])));
            int dia = cal.get(Calendar.DAY_OF_WEEK);//1=Domingo 2=Lunes
            switch (dia){
               case 1: nombreDia = "Domingo";
                break;
               case 2: nombreDia = "Lunes";
                break;
               case 3: nombreDia = "Martes";
                break;
               case 4: nombreDia = "Miercoles";
                break;
               case 5: nombreDia = "Jueves";
                break;
               case 6: nombreDia = "Viernes";
                break;
               case 7: nombreDia = "Sabado";
                break;
            }
            procedure = conn.prepareCall("select "+nombreDia+",MedID from horariosmedicos where idClini = "+Clinica+" ");
            
            procedure.execute();
            sulset = procedure.getResultSet();
            while(sulset.next()){
               String ans1 = sulset.getString(1);
               
                String [] hora = ans1.split("-");
                String [] hora1 = hora[0].split(":");
                String [] hora2 = hora[1].split(":");
                String [] horaTraida = Hora.split(":");
                
                int horaBuena1 = Integer.parseInt(hora1[0]);
                int horaBuena2 = Integer.parseInt(hora2[0]);
                int horaChida = Integer.parseInt(horaTraida[0]);
                int doc = sulset.getInt(2);
                if(horaBuena1<horaChida && horaBuena2>horaChida ){
                    procedure = conn.prepareCall("call RegCita(?,?,?,?)");
                    procedure.setString(1,IdUser);
                    procedure.setString(2,Fech);
                    procedure.setString(3,Hora);
                    procedure.setInt(4,doc);
                    procedure.execute();
                    sulset = procedure.getResultSet();
                    if(sulset.first()){
                        ans = sulset.getString("msj");
                    }    
                }
                else{
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
