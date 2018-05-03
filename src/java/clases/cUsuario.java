package clases;

import java.sql.SQLException;

/**
 * clase que permitira un manejo rapido del usuario y mejor navegación en el sistema
 */
public class cUsuario extends cBDatos{
    /**
     * id del usuario en el sistema
     */
    protected int idUsuario;
    /**
     * Agenda con las citas del usuario
     */
    protected cAgenda agenda;
    /**
     * Nombre de usuario
     */
    protected String user;
    /**
     * Correo del usuario
     */
    protected String email;
    /**
     * Nivel de acceso del usuario en el sistema
     */
    protected int nivel;
    
    /**
     * Default constructor
     */
    public cUsuario() {
    }
    /**
     * @param idUser
     */
    public cUsuario(int idUser) {
        // TODO implement here
    }

    public cUsuario(String user, String email, int nivel) {
        this.user = user;
        this.email = email;
        this.nivel = nivel;
    }
    
    public boolean Encuentra(String User){
        boolean ok = false;
        try {
            Conectar();
            procedure = conn.prepareCall("call BuscaUser(?)");
            procedure.setString(1, User);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                idUsuario = sulset.getInt("idUsuario");
                user = User;
                nivel = sulset.getInt("Nivel");
                email = sulset.getString("Correo");
                ok=true;
            }else{
                ok=false;
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar al usuario");
        }
        return ok;
    }
    public boolean ValidaA(String Psw){
        boolean ok=false;
        try {
            Conectar();
            procedure = conn.prepareCall("call ValidaUser(?,?)");
            procedure.setInt(1,idUsuario);
            procedure.setString(2, Psw);
            procedure.execute();
            sulset = procedure.getResultSet();
            if(sulset.first()){
                if(sulset.getString("msj").equals("Valido")){
                    ok=true;
                }
            }else{
                ok=false;
            }
            Cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al validar contraseña");
        }
        return ok;
    }
    
    public String RegUsr(String Contra){
        String ans="";
        try {
            Conectar();
            procedure = conn.prepareCall("call RegUser(?,?,?,?)");
            procedure.setString(1,user);
            procedure.setString(2,email);
            procedure.setString(3,Contra);
            procedure.setInt(4,nivel);
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
            System.out.println("Error al registrar usuario");
            System.out.println(ex.getMessage());
        }
        return ans;
    }

    //<editor-fold defaultstate="collapsed" desc="GETTERs">
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public cAgenda getAgenda() {
        return agenda;
    }
    
    public String getUser() {
        return user;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public String getEmail() {
        return email;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SETTERs">
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public void setAgenda(cAgenda agenda) {
        this.agenda = agenda;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
//</editor-fold>
    
    
}