package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Carreras;
import modelo.ConsultasMaterias;
import modelo.Materias;
import vista.frmMaterias;
import vista.frmMenu;

public class CrtlMaterias implements ActionListener, MouseListener, KeyListener{
    private Materias mat;
    private Carreras carr;
    private ConsultasMaterias conM;
    private frmMaterias frmM;
    DefaultTableModel modeloT=new DefaultTableModel();
    
    
    public CrtlMaterias(Materias mat, ConsultasMaterias conM, frmMaterias frmM, Carreras carr) {
        this.mat = mat;
        this.carr = carr;
        this.conM = conM;
        this.frmM = frmM;
        this.frmM.jButtonAgregar.addActionListener(this);
        this.frmM.jButtonModificar.addActionListener(this);
        this.frmM.jButtonEliminar.addActionListener(this);
        this.frmM.jButtonBuscar.addActionListener(this);
        this.frmM.jButtonLimpiar.addActionListener(this);
        this.frmM.jTableMaterias.addMouseListener(this);
        this.frmM.jButtonVolver.addActionListener(this);
        this.frmM.jComboBoxCarrera.addActionListener(this);
        this.frmM.jTextFieldBuscar.addKeyListener(this);
    }
     public void iniciar(){
        
        frmM.setTitle("Materias");
        frmM.setLocationRelativeTo(null);
        frmM.jTextFieldIdCarrera.setVisible(false);
        frmM.jTextFieldIdMat.setVisible(false);
        mostrarTabla(frmM.jTableMaterias);
        LlenaCombo(frmM.jComboBoxCarrera);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //AGREGAR
        if(e.getSource()==frmM.jButtonAgregar){
            if(frmM.jTextFieldIdCarrera.getText().length()==0) {
                JOptionPane.showMessageDialog(frmM, "Debe seleccionar una Carrera");
            }
              else{   
                 mat.setNombreM(frmM.jTextFieldNomM.getText());
                 mat.setAgno(Integer.parseInt(frmM.jTextFieldAgno.getText()));
                 mat.setSem(Integer.parseInt(frmM.jTextFieldSemestre.getText()));
                 carr.setIdCarrera(Integer.parseInt(frmM.jTextFieldIdCarrera.getText()));
                        if(conM.registar(mat, carr)){
                             JOptionPane.showMessageDialog(null,"Registro Guardado");
                             mostrarTabla(frmM.jTableMaterias);
                             limpiar();
                            }
               
                         else JOptionPane.showMessageDialog(null,"error al Guardar");
            
                
           }
               
   }
                  
        //MODIFICAR//
        if(e.getSource()==frmM.jButtonModificar){
            mat.setIdMateria(Integer.parseInt(frmM.jTextFieldIdMat.getText()));
            mat.setNombreM(frmM.jTextFieldNomM.getText());
            mat.setAgno(Integer.parseInt(frmM.jTextFieldAgno.getText()));
            mat.setSem(Integer.parseInt(frmM.jTextFieldSemestre.getText()));
            carr.setIdCarrera(Integer.parseInt(frmM.jTextFieldIdCarrera.getText()));
            
            if(conM.modificar(mat, carr)){
                JOptionPane.showMessageDialog(null,"Registro Modificado");
                mostrarTabla(frmM.jTableMaterias);
                limpiar();
            }
               
            else JOptionPane.showMessageDialog(null,"error al Modificar");
            
        }
        //ELIMINAR//
        
        if(e.getSource()==frmM.jButtonEliminar){
            //System.out.println("aquieliminar");
            mat.setIdMateria(Integer.parseInt(frmM.jTextFieldIdMat.getText()));
                       
            if(conM.eliminar(mat)){
                JOptionPane.showMessageDialog(null,"Registro Eliminado");
                mostrarTabla(frmM.jTableMaterias);
                limpiar();
            }
               
            else JOptionPane.showMessageDialog(null,"error al Eliminar");
            
        }
         //BUSCAR
         if(e.getSource()==frmM.jButtonBuscar){
            mat.setIdMateria(Integer.parseInt(frmM.jTextFieldBuscar.getText()));
            mostrarTabla(frmM.jTableMaterias);
             //System.out.println("holiddad");
            if(conM.buscar(mat, carr)){
                
                frmM.jTextFieldIdMat.setText(String.valueOf(mat.getIdMateria()));
                frmM.jTextFieldNomM.setText(mat.getNombreM());
                frmM.jTextFieldAgno.setText(String.valueOf(mat.getAgno()));
                frmM.jTextFieldSemestre.setText(String.valueOf(mat.getSem()));
                frmM.jTextFieldIdCarrera.setText(String.valueOf(carr.getIdCarrera()));
                frmM.jComboBoxCarrera.setSelectedItem(carr.getNombreC());
                System.out.println(carr.getNombreC());
                
            }
               
            else JOptionPane.showMessageDialog(null,"No se encontro registro");
            
        }   
        //LIMPIAR
        if(e.getSource()==frmM.jButtonLimpiar){
            limpiar();
            mostrarTabla(frmM.jTableMaterias);
            
            
        }
        //VOLVER
        if(e.getSource()==frmM.jButtonVolver){
                frmMenu miM=new frmMenu();
                CtrlMenu ctrlM= new CtrlMenu(miM);
                ctrlM.iniciar();
                miM.setVisible(true);
                frmM.dispose();
        }
        //SELECCIONA COMBO
        if(e.getSource()==frmM.jComboBoxCarrera){
            carr.setNombreC((String) frmM.jComboBoxCarrera.getSelectedItem());
            if(conM.alcoyana(carr)){
                frmM.jTextFieldIdCarrera.setText(String.valueOf(carr.getIdCarrera()));
                 
            }
               
        }
}
       
        //LIMPIAR
       public void limpiar(){
           
           frmM.jTextFieldIdMat.setText(null);
           frmM.jTextFieldNomM.setText(null);
           frmM.jTextFieldAgno.setText(null);
           frmM.jTextFieldSemestre.setText(null);
           frmM.jTextFieldBuscar.setText(null);
           frmM.jTextFieldIdCarrera.setText(null);
           frmM.jComboBoxCarrera.setSelectedIndex(0);
       }
    
      //MOSTRARTABLA
       public void mostrarTabla(JTable tabla) {
           
           modeloT=(DefaultTableModel) tabla.getModel();
           modeloT.setRowCount(0);
           List<Materias>lista=conM.listar(frmM.jTextFieldBuscar.getText());
           Object[]object=new Object[5];
                for(int i=0;i<lista.size();i++){
                    object[0]=lista.get(i).getIdMateria();
                    object[1]=lista.get(i).getNombreM();
                    object[2]=lista.get(i).getAgno();
                    object[3]=lista.get(i).getSem();
                    object[4]=lista.get(i).getCarrera().getNombreC();
                    
                    modeloT.addRow(object);
                     
           }
           frmM.jTableMaterias.setModel(modeloT);
               
       }
      
       //LLENA COMBO
       public void LlenaCombo(JComboBox JCB) {
           
           List<Carreras>listaC=conM.listarCombo();
           Object[]object=new Object[2];
                for(int i=0;i<listaC.size();i++){
                    object[0]=listaC.get(i).getIdCarrera();
                    object[1]=listaC.get(i).getNombreC();
                    
                   JCB.addItem(object[1]);
                    
           }
           
               
       }
       
     //MOUSELISTENER  
    @Override
    public void mouseClicked(MouseEvent e) {
        int fila=frmM.jTableMaterias.getSelectedRow();
        frmM.jTextFieldIdMat.setText(frmM.jTableMaterias.getValueAt(fila, 0).toString());
        frmM.jTextFieldNomM.setText(frmM.jTableMaterias.getValueAt(fila, 1).toString());
        frmM.jTextFieldAgno.setText(frmM.jTableMaterias.getValueAt(fila, 2).toString());
        frmM.jTextFieldSemestre.setText(frmM.jTableMaterias.getValueAt(fila, 3).toString());
        frmM.jComboBoxCarrera.setSelectedItem(frmM.jTableMaterias.getValueAt(fila, 4).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
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
         System.out.println("valor "+frmM.jTextFieldBuscar.getText());
       conM.listar(frmM.jTextFieldBuscar.getText());
        mostrarTabla(frmM.jTableMaterias);
    }
       
     
    
    
    
    
}
