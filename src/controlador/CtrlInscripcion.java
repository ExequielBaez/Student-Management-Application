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
import modelo.Alumnos;
import modelo.Carreras;

import modelo.ConsultasInscripcion;

import modelo.Materias;

import vista.frmInscripcion;


public class CtrlInscripcion implements ActionListener, KeyListener, MouseListener{
    
    private Materias mat;
    private Alumnos alum;
    private Carreras carr;
    private ConsultasInscripcion conIns;
    private frmInscripcion frmIns;
    DefaultTableModel modeloT=new DefaultTableModel();
    private static int priVez=0;
    
    
    public CtrlInscripcion(Alumnos alum, Carreras carr ,Materias mat, ConsultasInscripcion conIns, frmInscripcion frmIns) {
        this.mat = mat;
        this.carr=carr;
        this.alum = alum;
        this.conIns = conIns;
        this.frmIns = frmIns;
        this.frmIns.jTextFieldBuscar.addKeyListener(this);
        this.frmIns.jTableMaterias.addMouseListener(this);
        this.frmIns.jTableAlumnos.addMouseListener(this);
        this.frmIns.jComboBoxCarr.addActionListener(this);
        
    }
     public void iniciar(){
        
        frmIns.setTitle("Inscripcion Alumnos");
        frmIns.setLocationRelativeTo(null);
        frmIns.jTextFieldIdAl.setVisible(false);
        frmIns.jTextFieldIdCarr.setVisible(false);
        frmIns.jTextFieldIdM.setVisible(false);
        frmIns.jTextFieldMat.setVisible(false);
        mostrarTabla(frmIns.jTableAlumnos);
        llenaCombo(frmIns.jComboBoxCarr);
        
    }
     //LLENA LISTA Materias
       public void LlenaLista(JTable lista) {
            modeloT=(DefaultTableModel) lista.getModel();
            modeloT.setRowCount(0);
            List<Materias> listaMat=conIns.listarList(frmIns.jTextFieldIdCarr.getText());
            Object[] mList=new Object[2];
            
                for(int i=0;i<listaMat.size();i++){
                    
                    mList[0]=listaMat.get(i).getNombreM();
           
                    modeloT.addRow(mList);
                       }  
           }
       
       //LLENA COMBO CARR
       public void llenaCombo(JComboBox JCB) {
           
           List<Carreras>listaC=conIns.listarCombo();
           Object[]object=new Object[2];
                for(int i=0;i<listaC.size();i++){
                    object[0]=listaC.get(i).getIdCarrera();
                    object[1]=listaC.get(i).getNombreC();
                    
                   JCB.addItem(object[1]);
                    
           }
           
               
       }      
     
       
     @Override
    public void actionPerformed(ActionEvent e) {
        //SELECCIONA COMBO
        if(e.getSource()==frmIns.jComboBoxCarr){
            carr.setNombreC((String) frmIns.jComboBoxCarr.getSelectedItem());
            if(conIns.alcoyanaC(carr)){
                frmIns.jTextFieldIdCarr.setText(String.valueOf(carr.getIdCarrera()));
                LlenaLista(frmIns.jTableMaterias); 
            }
               
        }
        }
        
        
    
    //MOSTRARTABLALUMNOS
    
    public void mostrarTabla(JTable tabla) {
           
           modeloT=(DefaultTableModel) tabla.getModel();
           modeloT.setRowCount(0);
           List<Alumnos>lista=conIns.listar(frmIns.jTextFieldBuscar.getText());
           Object[]object=new Object[4];
                for(int i=0;i<lista.size();i++){
                    object[0]=lista.get(i).getIdAlumno();
                    object[1]=lista.get(i).getNombre();
                    object[2]=lista.get(i).getApellido();
                    object[3]=lista.get(i).getDni();
                    
                    
                    modeloT.addRow(object);
                     
           }
           frmIns.jTableAlumnos.setModel(modeloT);
               
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
       conIns.listar(frmIns.jTextFieldBuscar.getText());
        mostrarTabla(frmIns.jTableAlumnos);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==frmIns.jTableAlumnos){
        
                int fila=frmIns.jTableAlumnos.getSelectedRow();
                frmIns.jTextFieldIdAl.setText(frmIns.jTableAlumnos.getValueAt(fila, 0).toString());
                frmIns.jTextFieldNA.setText(frmIns.jTableAlumnos.getValueAt(fila, 1).toString());
                frmIns.jTextFieldAA.setText(frmIns.jTableAlumnos.getValueAt(fila, 2).toString());
                frmIns.jTextFieldDNI1.setText(frmIns.jTableAlumnos.getValueAt(fila, 3).toString());
                }
        
        
        if(e.getSource()==frmIns.jTableMaterias){
            priVez++;
            int fila=frmIns.jTableMaterias.getSelectedRow();
            frmIns.jTextFieldMat.setText(frmIns.jTableMaterias.getValueAt(fila, 0).toString());
            mat.setNombreM(frmIns.jTextFieldMat.getText());
            //System.out.println("1"+priVez);
            if(priVez==1){
            //System.out.println("tabla materias");
            Object[] opciones={"Aceptar", "Cancelar"};
            int seleccion=JOptionPane.showOptionDialog(frmIns, "Se procederá a Inscribir a " +frmIns.jTextFieldNA.getText() + " "
                        +frmIns.jTextFieldAA.getText() +" a la Carrera "+frmIns.jComboBoxCarr.getSelectedItem()
                        + " y a la Materia " +frmIns.jTextFieldMat.getText()
                        , "Aviso",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null ,opciones, null) ;
                
                  
                
                if(seleccion==0 && priVez==1){
                                       
                    //System.out.println(""+priVez);
                    if(conIns.alcoyanaM(mat)){
                        frmIns.jTextFieldIdM.setText(String.valueOf(mat.getIdMateria()));
                        alum.setIdAlumno(Integer.parseInt(frmIns.jTextFieldIdAl.getText()));
                        carr.setIdCarrera(Integer.parseInt(frmIns.jTextFieldIdCarr.getText()));
                        mat.setIdMateria(Integer.parseInt(frmIns.jTextFieldIdM.getText()));
                    }
                            
                    else System.out.println("error alcoyana m");
                    
                    if(conIns.registar(alum, carr, mat, Integer.parseInt(frmIns.jTextFieldAgno.getText()))) {
                        
                        JOptionPane.showMessageDialog(null,"Registro Guardado");
                    }
                    else JOptionPane.showMessageDialog(null,"Error, ya esta realizada la inscripcion");
                }
           
            }
            else {
            //System.out.println("tabla materias otra vez");
            Object[] opciones={"Aceptar", "Cancelar"};
            int seleccion=JOptionPane.showOptionDialog(frmIns, "Se procederá a Inscribir a " +frmIns.jTextFieldNA.getText() + " "
                        +frmIns.jTextFieldAA.getText() +" a la Materia "+frmIns.jTextFieldMat.getText()
                        , "Aviso",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null ,opciones, null) ;
                
             if(seleccion==0){
                 if(conIns.alcoyanaM(mat)){
                        frmIns.jTextFieldIdM.setText(String.valueOf(mat.getIdMateria()));
                        alum.setIdAlumno(Integer.parseInt(frmIns.jTextFieldIdAl.getText()));
                        carr.setIdCarrera(Integer.parseInt(frmIns.jTextFieldIdCarr.getText()));
                        mat.setIdMateria(Integer.parseInt(frmIns.jTextFieldIdM.getText()));
                    }
                            
                else System.out.println("error alcoyana m");
                    
                if(conIns.registar(alum, carr, mat, Integer.parseInt(frmIns.jTextFieldAgno.getText()))){
                        
                        JOptionPane.showMessageDialog(null,"Registro Guardado");
                    }
                 
                else JOptionPane.showMessageDialog(null,"Error, ya esta realizada la inscripcion");
                    
                }
                 
                 
                 
                 
              
            }
            
        }
        
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
