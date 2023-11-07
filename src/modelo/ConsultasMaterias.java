package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasMaterias extends Conexion {
    
    public boolean registar(Materias miMateria, Carreras miCarrera){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="INSERT INTO materias (nombreMateria, agno, semestre,idCarrera)VALUES(?,?,?,?)";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1, miMateria.getNombreM());
            ps.setInt(2, miMateria.getAgno());
            ps.setInt(3, miMateria.getSem());
            ps.setInt(4, miCarrera.getIdCarrera());
            
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
    
    public boolean modificar(Materias miMateria, Carreras miCarrera){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="UPDATE materias SET nombreMateria=?, agno=?, semestre=?, idCarrera=? WHERE idMateria=?";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1, miMateria.getNombreM());
            ps.setInt(2, miMateria.getAgno());
            ps.setInt(3, miMateria.getSem());
            ps.setInt(4,miCarrera.getIdCarrera());
            ps.setInt(5,miMateria.getIdMateria());
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
    
    public boolean eliminar(Materias miMateria){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="DELETE FROM materias WHERE idMateria=?";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setInt(1,miMateria.getIdMateria());
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
    
    public boolean buscar(Materias miMateria, Carreras miCarrera){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT * FROM materias m JOIN carreras c ON m.idCarrera = c.idCarrera WHERE idMateria=? ";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setInt(1,miMateria.getIdMateria());
            rs=ps.executeQuery();
            
            if(rs.next()){
                
                miMateria.setIdMateria(Integer.parseInt(rs.getString("idMateria")));
                miMateria.setNombreM(rs.getString("nombreMateria"));
                miMateria.setAgno(Integer.parseInt(rs.getString("agno")));
                miMateria.setSem(Integer.parseInt(rs.getString("semestre")));
                miCarrera.setIdCarrera(Integer.parseInt(rs.getString("idCarrera")));
                miCarrera.setNombreC((rs.getString("nombreCarrera")));
                
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
    
    //LISTAR TABLA
    public List listar(String valor){
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT * FROM materias m JOIN carreras c ON m.idCarrera = c.idCarrera WHERE nombreMateria LIKE '%"+valor+"%'";
        List<Materias>datos=new ArrayList<>();
         try{
             ps=miCon.prepareStatement(sql);
             rs=ps.executeQuery();
             while(rs.next()){
                 Materias miM=new Materias();
                 Carreras miC=new Carreras();
                 miM.setIdMateria(rs.getInt("idMateria"));
                 miM.setNombreM(rs.getString("nombreMateria"));
                 miM.setAgno(rs.getInt("agno"));
                 miM.setSem(rs.getInt("semestre"));
                 miC.setNombreC(rs.getString("nombreCarrera"));
                 miM.setCarrera(miC);
                 datos.add(miM);
             }
             
         }catch(Exception e){
            
        }
        return datos;
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
    //ALCOYANA COMBO
    public boolean alcoyana(Carreras miCarrera){
        
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
    
}
