package clases;


import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class cPaciente extends cUsuario{

    /**
     * Default constructor
     */
    public cPaciente() {
    }
    /**
     * 
     */
    public String nomPac;
    /**
     * 
     */
    private String afiliación;
    /**
     * 
     */
    private String idAfil;
    /**
     * 
     */
    private Long celular;
    /**
     * 
     */
    private String curp;
    /**
     * 
     */
    private int edad;
    /**
     * 
     */
    private char sexo;
    /**
     * 
     */
    private ArrayList<String> alergis;

    /**
     * 
     */
    private ArrayList<String> padCronic;
    /**
     * Constructor con variables de clase basicas
     * @param nomPac
     * @param afiliación
     * @param idAfil
     * @param celular
     * @param curp
     * @param edad
     * @param sexo 
     */
    public cPaciente(String nomPac, String afiliación, String idAfil, Long celular, String curp, int edad, char sexo) {
        this.nomPac = nomPac;
        this.afiliación = afiliación;
        this.idAfil = idAfil;
        this.celular = celular;
        this.curp = curp;
        this.edad = edad;
        this.sexo = sexo;
    }
    /**
     * Constructor a partir de un objeto usuario
     * @param User
     */
    public cPaciente(cUsuario User) {
        cToolBoxFlar tools = new cToolBoxFlar();
        super.idUsuario = User.getIdUsuario();
        super.user = User.getUser();
        super.email = User.getEmail();
        super.nivel = User.getNivel();
        super.agenda = User.getAgenda();
        
        try {
            Conectar();
            procedure = conn.prepareCall("call GetPac(?)");
            procedure.setInt(1,idUsuario);
            procedure.execute();
            sulset = procedure.getResultSet();
            while(sulset.next()){
                nomPac = sulset.getString("NomPac");
                afiliación = sulset.getString("Filial");
                idAfil = sulset.getString("NumFilial");
                celular = sulset.getLong("Cel");
                curp = sulset.getString("Curp");
                edad = sulset.getInt("Edad");
                sexo = sulset.getString("Sexo").charAt(0);
                alergis = tools.ParseArray(sulset.getString("Alergis").split(","));
                padCronic = tools.ParseArray(sulset.getString("PadCrons").split(","));
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar al paciente (from user)");
        }
        
    }

    /**
     * @return
     */
    public String RegPac() {
        /*idPac int(5) primary key not null,
            Nombre varchar(30),
            Afiliacion varchar(20),
            IDAfiliado varchar(30),
            Celular long,
            CURP varchar(18),
            Edad int(3),
            Sexo char(1),
            idUsuario int(5),
            */
        String ans = "";
        try {
            Conectar();
            procedure = conn.prepareCall("call RegPac(?,?,?,?,?,?,?,?)");
            procedure.setString(1,nomPac);
            procedure.setString(2,afiliación);
            procedure.setString(3,idAfil);
            procedure.setLong(4,celular);
            procedure.setString(5,curp);
            procedure.setInt(6,edad);
            procedure.setString(7,String.valueOf(sexo));
            procedure.setInt(8,idUsuario);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.isFirst()){
                ans = sulset.getString("msj");
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al registrar al paciente");
        }
        return ans;
    }

    /**
     * @return
     */
    public void ModPac() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void DelPac() {
        // TODO implement here
    }

}