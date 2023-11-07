package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultasAlumnos extends Conexion{
    
    public boolean registar(Alumnos miAlumno){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="INSERT INTO alumnos (nombre, apellido, dni, fechaNac, edad, "
                + "telefono, email, direccion, nacionalidad, rutaFoto, chkfoto,"
                + "chkCus, chkAnalitico, chkCooperadora)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1, miAlumno.getNombre());
            ps.setString(2, miAlumno.getApellido());
            ps.setInt(3, miAlumno.getDni());
            ps.setString(4, miAlumno.getFechaNac());
            ps.setInt(5, miAlumno.getEdad());
            ps.setString(6, miAlumno.getTelefono());
            ps.setString(7, miAlumno.getEmail());
            ps.setString(8, miAlumno.getDireccion());
            ps.setString(9, miAlumno.getNacion());
            ps.setString(10, miAlumno.getRutaFoto());
            ps.setBoolean(11, miAlumno.isFoto4x4());
            ps.setBoolean(12, miAlumno.isCus());
            ps.setBoolean(13, miAlumno.isAnalitico());
            ps.setBoolean(14, miAlumno.isCooperadora());
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
    
    public boolean modificar(Alumnos miAlumno){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="UPDATE alumnos SET nombre=?, apellido=?, dni=?, fechaNac=?, edad=?, telefono=?, email=?, direccion=?, nacionalidad=? , rutaFoto=? , chkFoto=?, chkCus=?, chkAnalitico=?, chkCooperadora=? WHERE idAlumno=?";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setString(1, miAlumno.getNombre());
            ps.setString(2, miAlumno.getApellido());
            ps.setInt(3, miAlumno.getDni());
            ps.setString(4, miAlumno.getFechaNac());
            ps.setInt(5, miAlumno.getEdad());
            ps.setString(6, miAlumno.getTelefono());
            ps.setString(7, miAlumno.getEmail());
            ps.setString(8, miAlumno.getDireccion());
            ps.setString(9, miAlumno.getNacion());
            ps.setString(10,miAlumno.getRutaFoto());
            ps.setBoolean(11,miAlumno.isFoto4x4());
            ps.setBoolean(12,miAlumno.isCus());
            ps.setBoolean(13,miAlumno.isAnalitico());
            ps.setBoolean(14,miAlumno.isCooperadora());
            ps.setInt(15,miAlumno.getIdAlumno());
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
    
    public boolean eliminar(Alumnos miAlumno){
        
        PreparedStatement ps=null;
        Connection miCon=getConexion();
        String sql="DELETE FROM alumnos WHERE idAlumno=?";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setInt(1,miAlumno.getIdAlumno());
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
    
    public boolean buscar(Alumnos miAlumno){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection miCon=getConexion();
        String sql="SELECT * FROM alumnos WHERE dni=? ";
        try{
            ps=miCon.prepareStatement(sql);
            ps.setInt(1,miAlumno.getDni());
            
            
            rs=ps.executeQuery();
            
            if(rs.next()){
                
                miAlumno.setIdAlumno(Integer.parseInt(rs.getString("idAlumno")));
                miAlumno.setNombre(rs.getString("nombre"));
                miAlumno.setApellido(rs.getString("apellido"));
                miAlumno.setDni(Integer.parseInt(rs.getString("dni")));
                miAlumno.setFechaNac(rs.getString("fechaNac"));
                miAlumno.setEdad(Integer.parseInt(rs.getString("edad")));
                miAlumno.setTelefono(rs.getString("telefono"));
                miAlumno.setEmail(rs.getString("email"));
                miAlumno.setDireccion(rs.getString("direccion"));
                miAlumno.setNacion(rs.getString("nacionalidad"));
                miAlumno.setRutaFoto(rs.getString("rutaFoto"));
                miAlumno.setFoto4x4(rs.getBoolean("chkFoto"));
                miAlumno.setCus(rs.getBoolean("chkCus"));
                miAlumno.setAnalitico(rs.getBoolean("chkAnalitico"));
                miAlumno.setCooperadora(rs.getBoolean("chkCooperadora"));
                //System.out.println(miAlumno.getNacion());
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
