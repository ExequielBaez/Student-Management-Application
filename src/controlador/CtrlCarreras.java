
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Carreras;
import modelo.ConsultasCarreras;
import vista.frmCarreras;
import vista.frmMenu;

public class CtrlCarreras implements ActionListener, MouseListener, KeyListener{

    private Carreras car;
    private ConsultasCarreras conC;
    private frmCarreras frmC;
    DefaultTableModel modeloT=new DefaultTableModel();
    
     public CtrlCarreras(Carreras car, ConsultasCarreras conC, frmCarreras frmC) {
        this.car = car;
        this.conC = conC;
        this.frmC = frmC;
        this.frmC.jButtonAgregar.addActionListener(this);
        this.frmC.jButtonModificar.addActionListener(this);
        this.frmC.jButtonEliminar.addActionListener(this);
        this.frmC.jButtonBuscar.addActionListener(this);
        this.frmC.jButtonLimpiar.addActionListener(this);
        this.frmC.jTableCarreras.addMouseListener(this);
        this.frmC.jButtonVolver.addActionListener(this);
        this.frmC.jTextFieldBuscar.addKeyListener(this);
    }
     public void iniciar(){
        
        frmC.setTitle("Alumnos");
        frmC.setLocationRelativeTo(null);
        frmC.jTextFieldIdCarrera.setVisible(false);
        mostrarTabla(frmC.jTableCarreras);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //AGREGAR
        if(e.getSource()==frmC.jButtonAgregar){
            car.setNombreC(frmC.jTextFieldCarrera.getText());
           if(conC.registar(car)){
                JOptionPane.showMessageDialog(null,"Registro Guardado");
                
                mostrarTabla(frmC.jTableCarreras);
                limpiar();
            }
               
            else JOptionPane.showMessageDialog(null,"error al Guardar");
            
        }
        //MODIFICAR//
        if(e.getSource()==frmC.jButtonModificar){
            car.setIdCarrera(Integer.parseInt(frmC.jTextFieldIdCarrera.getText()));
            car.setNombreC(frmC.jTextFieldCarrera.getText());
            
            if(conC.modificar(car)){
                JOptionPane.showMessageDialog(null,"Registro Modificado");
                limpiar();
                mostrarTabla(frmC.jTableCarreras);
            }
               
            else JOptionPane.showMessageDialog(null,"error al Modificar");
            
        }
        //ELIMINAR//
        
        if(e.getSource()==frmC.jButtonEliminar){
            //System.out.println("aquieliminar");
            car.setIdCarrera(Integer.parseInt(frmC.jTextFieldIdCarrera.getText()));
                       
            if(conC.eliminar(car)){
                JOptionPane.showMessageDialog(null,"Registro Eliminado");
                limpiar();
                mostrarTabla(frmC.jTableCarreras);
            }
               
            else JOptionPane.showMessageDialog(null,"error al Eliminar");
            
        }
         //BUSCAR//
        if(e.getSource()==frmC.jButtonBuscar){
            car.setIdCarrera(Integer.parseInt(frmC.jTextFieldBuscar.getText()));
           
            
            if(conC.buscar(car)){
                
                frmC.jTextFieldIdCarrera.setText(String.valueOf(car.getIdCarrera()));
                frmC.jTextFieldCarrera.setText(car.getNombreC());
                
                
                
            }
               
            else JOptionPane.showMessageDialog(null,"No se encontro registro");
            
        }   
        //LIMPIAR
        if(e.getSource()==frmC.jButtonLimpiar){
            limpiar();
            mostrarTabla(frmC.jTableCarreras);
            
        }
        //VOLVER
        if(e.getSource()==frmC.jButtonVolver){
                frmMenu miM=new frmMenu();
                CtrlMenu ctrlM= new CtrlMenu(miM);
                ctrlM.iniciar();
                miM.setVisible(true);
                frmC.dispose();
        }
}
       
    //MOUSELISTENER
    //JTable miTabla=new JTable();
    @Override
    public void mouseClicked(MouseEvent e) {
        int fila=frmC.jTableCarreras.getSelectedRow();
        frmC.jTextFieldIdCarrera.setText(frmC.jTableCarreras.getValueAt(fila, 0).toString());
        frmC.jTextFieldCarrera.setText(frmC.jTableCarreras.getValueAt(fila, 1).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}    
    
    
    
        //LIMPIAR
       public void limpiar(){
           
           frmC.jTextFieldIdCarrera.setText(null);
           frmC.jTextFieldCarrera.setText(null);
           frmC.jTextFieldBuscar.setText(null);
           
       }
       //MOSTRARTABLA
       public void mostrarTabla(JTable tabla) {
           
           modeloT=(DefaultTableModel) tabla.getModel();
           modeloT.setRowCount(0);
           List<Carreras>lista=conC.listar(frmC.jTextFieldBuscar.getText());
           Object[]object=new Object[2];
                for(int i=0;i<lista.size();i++){
                    object[0]=lista.get(i).getIdCarrera();
                    object[1]=lista.get(i).getNombreC();
                    
                    modeloT.addRow(object);
                     
           }
           frmC.jTableCarreras.setModel(modeloT);
               
       }

    @Override
    public void keyTyped(KeyEvent e) {
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //String valor=frmC.jTextFieldBuscar.getText();
        System.out.println("valor "+frmC.jTextFieldBuscar.getText());
       conC.listar(frmC.jTextFieldBuscar.getText());
        mostrarTabla(frmC.jTableCarreras);
    }

    

    
      
    
    
    
}
