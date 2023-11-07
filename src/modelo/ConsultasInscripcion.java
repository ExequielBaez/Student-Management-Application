package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasInscripcion extends Conexion {
    //REGISTRAR
    public boolean registar(Alumnos miAlumno, Carreras miCarrera, Materias miMateria, int agno ){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="INSERT INTO cursan (idAlumno, idCarrera, idMateria, agno) VALUES (?,?,?,?)";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setInt(1, miAlumno.getIdAlumno());            
            ps.setInt(2, miCarrera.getIdCarrera());
            ps.setInt(3, miMateria.getIdMateria());
            ps.setInt(4, agno);
            
            ps.execute();
            return true;
                  
        }
        catch(SQLException e){
            System.out.println("error aqui");
            System.err.println(e);
            return false;
        }
        finally{
        try {
            miCon.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    }  
    //LISTAR TABLA ALUMNOS
    public List listar(String valor){
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT * FROM alumnos WHERE CONCAT (nombre,' ',apellido) LIKE '%"+valor+"%'";
        List<Alumnos>datos=new ArrayList<>();
         try{
             ps=miCon.prepareStatement(sql);
             rs=ps.executeQuery();
             while(rs.next()){
                 Alumnos miA=new Alumnos();
                 miA.setIdAlumno(rs.getInt("idAlumno"));
                 miA.setNombre(rs.getString("nombre"));
                 miA.setApellido(rs.getString("apellido"));
                 miA.setDni(rs.getInt("dni"));
                 datos.add(miA);
             }
             
         }catch(Exception e){
            
        }
        return datos;
     }
    //LISTAR LIST
    public List listarList(String valor){
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql=" SELECT * FROM materias WHERE idCarrera="+valor+"";
        List<Materias>datosM=new ArrayList<>();
         try{
             ps=miCon.prepareStatement(sql);
             rs=ps.executeQuery();
             while(rs.next()){
                 
                 Materias miM=new Materias();
                 miM.setIdMateria(rs.getInt("idMateria"));
                 miM.setNombreM(rs.getString("nombreMateria"));
                 
                 datosM.add(miM);
             }
             
         }catch(Exception e){
            
        }
        return datosM;
     }
    
    //LISTAR COMBO
    public List listarCombo(){
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql=" SELECT * FROM carreras ";
        List<Carreras>datosC=new ArrayList<>();
         try{
             ps=miCon.prepareStatement(sql);
             rs=ps.executeQuery();
             while(rs.next()){
                 
                 Carreras miC=new Carreras();
                 miC.setIdCarrera(rs.getInt("idCarrera"));
                 miC.setNombreC(rs.getString("nombreCarrera"));
                 
                 datosC.add(miC);
             }
             
         }catch(Exception e){
            
        }
        return datosC;
     }
    
    //ALCOYANA COMBO CARRERAS
    public boolean alcoyanaC(Carreras miCarrera){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT idCarrera FROM carreras WHERE nombreCarrera=? ";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1,miCarrera.getNombreC());
            rs=ps.executeQuery();
            
            if(rs.next()){
                
                miCarrera.setIdCarrera(Integer.parseInt(rs.getString("idCarrera")));
                                
                return true;
            }
            
            
            return false;
                  
        }
        catch(SQLException e){
            System.err.println(e);
            return false;
        }
        finally{
        try {
            miCon.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
    //ALCOYANA COMBO Materias
    public boolean alcoyanaM(Materias miMateria){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT idMateria FROM materias WHERE nombreMateria=? ";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1,miMateria.getNombreM());
            rs=ps.executeQuery();
            
            if(rs.next()){
                
                miMateria.setIdMateria(Integer.parseInt(rs.getString("idMateria")));
                                
                return true;
            }
            
            
            return false;
                  
        }
        catch(SQLException e){
            System.err.println(e);
            return false;
        }
        finally{
        try {
            miCon.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
}
