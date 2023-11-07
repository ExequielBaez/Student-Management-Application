package controlador;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.LogueaUsuarios;
import modelo.Usuarios;
import vista.frmMenu;
import vista.frmPrincipal;


public class CrtlLogin implements ActionListener {
    
    private Usuarios user;
    private LogueaUsuarios lg;
    private frmPrincipal frm;

    public CrtlLogin(Usuarios user, LogueaUsuarios lg, frmPrincipal frm) {
        this.user = user;
        this.lg = lg;
        this.frm = frm;
        this.frm.jButtonIngresar.addActionListener(this);
        
    }

    public void iniciar(){
        
        frm.setTitle("SISTEMA ALUMNOS");
        frm.setBackground(Color.yellow);
        frm.setLocationRelativeTo(null);
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == frm.jButtonIngresar){
           
           
           user.setNombreUsuario(frm.jTextUsuario.getText());
           user.setClave(frm.jPassword.getText());
            
           if(lg.ingresar(user)){
                JOptionPane.showMessageDialog(null,"Correcto");
               
                
                frmMenu miM=new frmMenu();
                CtrlMenu ctrlM= new CtrlMenu(miM);
                ctrlM.iniciar();
                miM.setVisible(true);
                frm.dispose();
                
                
                
            }
           else JOptionPane.showMessageDialog(null,"Usuario o Clave incorrecta");
    
    
        
        
    }
    
    }
}