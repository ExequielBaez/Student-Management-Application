package modelo;



public class Alumnos {

    private int idAlumno, dni, edad;
    private boolean foto4x4, cus, analitico, cooperadora; 
    private String nombre, apellido, fechaNac, email, direccion, telefono, nacion, rutaFoto, busca;

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
    
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    public boolean isFoto4x4() {
        return foto4x4;
    }

    public void setFoto4x4(boolean foto4x4) {
        this.foto4x4 = foto4x4;
    }

    public boolean isCus() {
        return cus;
    }

    public void setCus(boolean cus) {
        this.cus = cus;
    }

    public boolean isAnalitico() {
        return analitico;
    }

    public void setAnalitico(boolean analitico) {
        this.analitico = analitico;
    }

    public boolean isCooperadora() {
        return cooperadora;
    }

    public void setCooperadora(boolean cooperadora) {
        this.cooperadora = cooperadora;
    }
    
    
}
