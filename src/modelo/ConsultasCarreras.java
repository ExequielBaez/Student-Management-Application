
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConsultasCarreras extends Conexion{
    //REGISTRAR
    public boolean registar(Carreras miC){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="INSERT INTO carreras (nombreCarrera) VALUES(?)";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1, miC.getNombreC());
            
            ps.execute();
            return true;
                  
        }
        catch(SQLException e){
            System.out.println("aquiestavez");
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
    //MODIFICAR
    public boolean modificar(Carreras miC){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="UPDATE carreras SET nombreCarrera=? WHERE idCarrera=?";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1, miC.getNombreC());
            ps.setInt(2,miC.getIdCarrera());
            ps.execute();
            return true;
                  
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
    //ELIMINAR
    public boolean eliminar(Carreras miC){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="DELETE FROM carreras WHERE idCarrera=?";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setInt(1,miC.getIdCarrera());
            ps.execute();
            return true;
                  
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
    //BUSCAR
    public boolean buscar(Carreras miC){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT * FROM carreras WHERE idCarrera=?";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setInt(1,miC.getIdCarrera());
            
            rs=ps.executeQuery();
            
            if(rs.next()){
                
                miC.setIdCarrera(Integer.parseInt(rs.getString("idCarrera")));
                miC.setNombreC(rs.getString("nombreCarrera"));
                
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
    //LISTAR 
    public List listar(String valor){
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT * FROM carreras WHERE nombreCarrera LIKE '%"+valor+"%'";
        List<Carreras>datos=new ArrayList<>();
         try{
             ps=miCon.prepareStatement(sql);
             rs=ps.executeQuery();
             while(rs.next()){
                 Carreras miC=new Carreras();
                 miC.setIdCarrera(rs.getInt("idCarrera"));
                 miC.setNombreC(rs.getString("nombreCarrera"));
                 datos.add(miC);
             }
             
         }catch(Exception e){
            
        }
        return datos;
     }
}