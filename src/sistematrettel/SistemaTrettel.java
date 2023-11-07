package sistematrettel;

import controlador.CrtlLogin;
import modelo.LogueaUsuarios;
import modelo.Usuarios;
import vista.frmPrincipal;

public class SistemaTrettel {

    public static void main(String[] args) {
      
        Usuarios user=new Usuarios();
        LogueaUsuarios lg=new LogueaUsuarios();
        frmPrincipal frm=new frmPrincipal();
        
        CrtlLogin crtl=new CrtlLogin(user, lg, frm);
        crtl.iniciar();
        
        frm.setVisible(true);
    }

    public SistemaTrettel() {
    }
    
}
