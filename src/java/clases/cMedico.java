package clases;


import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class cMedico extends cUsuario{

    /**
     * Default constructor
     */
    public cMedico() {
        
    }

    /**
     * Nombre completo del médico
     */
    private String nomDoc;

    /**
     * Cedula profesional como médico
     */
    private String cedula;

    /**
     * Dirección de vivienda del médico
     */
    private String dirección;

    /**
     * Especialidad realizada por el médico
     */
    private String especialidad;

    /**
     * Numero celular (movil) del médico
     */
    private Long celular;
    
    /**
     * Genero del médico
     */
    private char sexo;
    
    /**
     * Identificador en BD de la clinica que registro al médico
     */
    private int clinica;
    
    /**
     * Identificador en BD del horario del doctor
     */
    private int horario;

    /**
     * Constructor con variables de clase y super clase
     * @param User
     * @param nomDoc
     * @param cedula
     * @param dirección
     * @param especialidad
     * @param celular
     * @param sexo
     * @param clinica 
     * @param DiasHora
     */
    public cMedico(cUsuario User,String nomDoc, String cedula, String dirección, String especialidad, Long celular, char sexo, int clinica,String[] DiasHora) {
        super.idUsuario = User.getIdUsuario();
        super.user = User.getUser();
        super.email = User.getEmail();
        super.nivel = User.getNivel();
        super.agenda = User.getAgenda();
        this.nomDoc = nomDoc;
        this.cedula = cedula;
        this.dirección = dirección;
        this.especialidad = especialidad;
        this.celular = celular;
        this.sexo = sexo;
        this.clinica = clinica;
        setHorario(DiasHora);
    }
    /**
     * Constructor a partir de un usuario
     * @param User
     */
    public cMedico(cUsuario User) {
        super.idUsuario = User.getIdUsuario();
        super.user = User.getUser();
        super.email = User.getEmail();
        super.nivel = User.getNivel();
        super.agenda = User.getAgenda();
        
        try {
            Conectar();
            procedure = conn.prepareCall("call GetDoc(?)");
            procedure.setInt(1,idUsuario);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                this.nomDoc = sulset.getString(1);
                this.cedula = sulset.getString(2);
                this.dirección = sulset.getString(3);
                this.especialidad = sulset.getString(4);
                this.celular = sulset.getLong(5);
                this.sexo = sulset.getString(6).charAt(0);
                this.clinica = sulset.getInt(7);
                this.horario = sulset.getInt(8);
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar al medico (from user)");
        }
    }

    /**
     * @return
     */
    public String RegDoc() {
        //<editor-fold defaultstate="collapsed" desc="CamposDB">
        /*
        Nombre varchar(30),
        Cedula varchar(30),
        Especialidad varchar(30),
        Celular long,
        Sexo char(1),
        Direccion varchar(40),
        Clinica int(5), foreign key(Clinica) references clinicas(idClini),
        idUsuario int(5), foreign key(idUsuario) references usuarios(idUsuario),
        Horario int(5), foreign key(Horario) references horarios(idHoras)
        */
//</editor-fold>
       String ans = "...";
        try {
            Conectar();
            procedure = conn.prepareCall("call RegDoc(?,?,?,?,?,?,?,?)");
            procedure.setInt(1,idUsuario);
            procedure.setString(2,nomDoc);
            procedure.setString(3,cedula);
            procedure.setString(4,especialidad);
            procedure.setLong(5,celular);
            procedure.setString(6,String.valueOf(sexo));
            procedure.setString(7,dirección);
            procedure.setInt(8,clinica);
            procedure.setInt(9,horario);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                ans = sulset.getString("msj");
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al registrar al doctor");
            System.out.println(ex.getMessage());
        }
        return ans;
    }

    /**
     * @return
     */
    public void ModDoc() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void DelDoc() {
        // TODO implement here
    }

    //<editor-fold defaultstate="collapsed" desc="SETTERs">
    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public void setDirección(String dirección) {
        this.dirección = dirección;
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public void setCelular(Long celular) {
        this.celular = celular;
    }
    
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    public void setClinica(int clinica) {
        this.clinica = clinica;
    }
    
    public void setHorario(int horario) {
        this.horario = horario;
    }
    /**
     * Setter del horario a partir de los dias de la semana (Lunes-Domingo)
     * @param Dias 
     */
    private void setHorario(String[] Dias) {
        int horarioDoc = 0;
        try {
            Conectar();
            procedure = conn.prepareCall("call GetHorarioDoc(?,?,?,?,?,?,?)");
            for(int i=0;i<Dias.length;i++){
                procedure.setString(i,Dias[i]);
            }
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                horarioDoc = sulset.getInt("idHorasF");
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar el horario del medico");
        }
        this.horario = horarioDoc;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GETTERs">
    public String getNomDoc() {
        return nomDoc;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public String getDirección() {
        return dirección;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    public Long getCelular() {
        return celular;
    }
    
    public char getSexo() {
        return sexo;
    }
    
    public int getClinica() {
        return clinica;
    }
    
    public int getHorario() {
        return horario;
    }
//</editor-fold>
    
    
    
}