package clases;


import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class cClinica extends cUsuario{

    /**
     * Default constructor
     */
    public cClinica() {
    }

    /**
     * 
     */
    private String nomClinic;

    /**
     * 
     */
    private String direccion;

    /**
     * 
     */
    private String nomRep;
    private String rfc;
    /**
     * 
     */
    private Long telefono;

    /**
     * 
     */
    private cBitacora bitacora;


    /**
     * @param User 
     * @return
     */
    public cClinica(cUsuario User) {
        super.idUsuario = User.getIdUsuario();
        super.user = User.getUser();
        super.email = User.getEmail();
        super.nivel = User.getNivel();
        super.agenda = User.getAgenda();
        
        try {
            Conectar();
            procedure = conn.prepareCall("call GetClinic(?)");
            procedure.setInt(1,idUsuario);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                nomClinic = sulset.getString(1);
                nomRep = sulset.getString(2);
                telefono = sulset.getLong(3);
                direccion = sulset.getString(4);
                rfc = sulset.getString(5);
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar la clinica (from user)");
        }
    }
    
    public cClinica(cUsuario User,String nomClinic, String direccion, String nomRep,String rfcP, Long telefono) {
        super.idUsuario = User.getIdUsuario();
        super.user = User.getUser();
        super.email = User.getEmail();
        super.nivel = User.getNivel();
        super.agenda = User.getAgenda();
        
        this.nomClinic = nomClinic;
        this.direccion = direccion;
        this.nomRep = nomRep;
        this.telefono = telefono;
        this.rfc = rfcP;
    }
    
    

    /**
     * Metodo para registrar una clinica
     * @return
     */
    public String RegClinic() {
        //<editor-fold defaultstate="collapsed" desc="CamposDB">
        /*
        idClini int(5) primary key not null,
        Nombre varchar(30),
        NomRep varchar(30),
        Telefono long,
        Correo varchar(30),
        idUsuario int(5), foreign key(idUsuario) references usuarios(idUsuario),
        Direccion int(5), foreign key(Direccion) references direcciones(idDir)
        */
//</editor-fold>
        String ans="...";
        try {
            procedure.execute();
            //Procedimiento para registrar la clinica
            procedure = conn.prepareCall("call RegClinic(?,?,?,?,?,?)");
            procedure.setInt(1,idUsuario);
            procedure.setString(2,nomClinic);
            procedure.setString(3,nomRep);
            procedure.setString(4,rfc);
            procedure.setLong(5,telefono);
            procedure.setString(6, direccion);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                ans = sulset.getString("msj");
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al registrar a la clinica");
            System.out.println(ex.getMessage());
        }
        return ans;
    }

    /**
     * @return
     */
    public void DelClinic() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void ModClinic() {
        // TODO implement here
    }

    //<editor-fold defaultstate="collapsed" desc="SETTERs">
    public void setNomClinic(String nomClinic) {
        this.nomClinic = nomClinic;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion ;
    }
    
    public void setNomRep(String nomRep) {
        this.nomRep = nomRep;
    }
    
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    
    public void setBitacora(cBitacora bitacora) {
        this.bitacora = bitacora;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GETTERs">
    public String getNomClinic() {
        return nomClinic;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public String getNomRep() {
        return nomRep;
    }
    
    public Long getTelefono() {
        return telefono;
    }
    
    public cBitacora getBitacora() {
        return bitacora;
    }
//</editor-fold>

}