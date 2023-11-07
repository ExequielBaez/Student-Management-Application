
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Alumnos;
import modelo.Carreras;
import modelo.ConsultasAlumnos;
import modelo.ConsultasCarreras;
import modelo.ConsultasInscripcion;
import modelo.ConsultasMaterias;
import modelo.Materias;
import vista.frmMenu;
import vista.frmAlumnos;
import vista.frmCarreras;
import vista.frmInscripcion;
import vista.frmMaterias;

public class CtrlMenu implements ActionListener{
 
    private frmMenu miFrm;
    
    public CtrlMenu(frmMenu miMenu) {
        this.miFrm = miMenu;
        this.miFrm.jButtonAltaAl.addActionListener(this);
        this.miFrm.jButtonCarreras.addActionListener(this);
        this.miFrm.jButtonMaterias.addActionListener(this);
        this.miFrm.jButtonMatricula.addActionListener(this);
    }

   
    
    
    public void iniciar(){
        
        miFrm.setTitle("Alumnos");
        miFrm.setLocationRelativeTo(null);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //ALTA ALUMNOS
       if(e.getSource() == miFrm.jButtonAltaAl){
           //System.out.println("abrioo");
           Alumnos miAlum=new Alumnos();
           ConsultasAlumnos conAl=new ConsultasAlumnos();
           frmAlumnos frmAl=new frmAlumnos();
           
           CtrlAlumnos ctrl=new CtrlAlumnos(miAlum, conAl, frmAl);
           ctrl.iniciar();
           frmAl.setVisible(true);
           miFrm.dispose();
           
       }
       //ALTACARRERAS
       if(e.getSource() == miFrm.jButtonCarreras){
           
           Carreras miC=new Carreras();
           ConsultasCarreras conC=new ConsultasCarreras();
           frmCarreras frmC=new frmCarreras();
           
           CtrlCarreras ctrl=new CtrlCarreras(miC, conC, frmC);
           ctrl.iniciar();
           frmC.setVisible(true);
           miFrm.dispose();
           
       }
       //ALTAMATERIAS
       if(e.getSource() == miFrm.jButtonMaterias){
           
           Materias miM=new Materias();
           Carreras miC=new Carreras();
           ConsultasMaterias conM=new ConsultasMaterias();
           frmMaterias frmM=new frmMaterias();
           
           CrtlMaterias ctrl=new CrtlMaterias(miM, conM, frmM, miC);
           ctrl.iniciar();
           frmM.setVisible(true);
           miFrm.dispose();
           
       }
       //INSCRIPCION MATERIAS
       if(e.getSource() == miFrm.jButtonMatricula){
           
           Materias miM=new Materias();
           Alumnos miA=new Alumnos();
           Carreras miC=new Carreras();
           ConsultasInscripcion conIns=new ConsultasInscripcion();
           frmInscripcion frmIns=new frmInscripcion();
           
           CtrlInscripcion ctrl=new CtrlInscripcion(miA, miC, miM, conIns, frmIns);
           ctrl.iniciar();
           frmIns.setVisible(true);
           miFrm.dispose();
           
       }
        
    }
    
    
    
    
}
