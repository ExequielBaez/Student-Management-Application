package controlador;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.Alumnos;
import modelo.ConsultasAlumnos;
import vista.frmAlumnos;
import vista.frmMenu;


public class CtrlAlumnos implements ActionListener, FocusListener{
    
    private Alumnos alum;
    private ConsultasAlumnos cAlum;
    private frmAlumnos frmAl;

    public CtrlAlumnos(Alumnos alum, ConsultasAlumnos cAlum, frmAlumnos frmAl) {
        this.alum = alum;
        this.cAlum = cAlum;
        this.frmAl = frmAl;
        this.frmAl.jButtonRegistar.addActionListener(this);
        this.frmAl.jButtonModificar.addActionListener(this);
        this.frmAl.jButtonEliminar.addActionListener(this);
        this.frmAl.jButtonBuscar.addActionListener(this);
        this.frmAl.jButtonAgregarFoto.addActionListener(this);
        this.frmAl.jButtonLimpiar.addActionListener(this);
        this.frmAl.jComboBoxNac.addActionListener(this);
        this.frmAl.jButtonVolver.addActionListener(this);
        this.frmAl.jTextFieldEdad.addFocusListener(this);
    }
    
    public void iniciar(){
        
        frmAl.setTitle("Alumnos");
        frmAl.setLocationRelativeTo(null);
        frmAl.jTextFieldID.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        //REGISTRAR//
        if(e.getSource()==frmAl.jButtonRegistar){
            estableceFecha();
            alum.setNombre(frmAl.jTextFieldNombre.getText());
            alum.setApellido(frmAl.jTextFieldApellido.getText());
            alum.setDni(Integer.parseInt(frmAl.jTextFieldDni.getText()));
            //System.out.println(frmAl.jDate.getDate());
            //alum.setFechaNac(frmAl.jTextFieldFecha.getText());
           // alum.setFechaNac(frmAl.jDate.getDateFormatString());
            //System.out.println(frmAl.jDate.getDateFormatString());
            alum.setEdad(Integer.parseInt(frmAl.jTextFieldEdad.getText()));
            alum.setTelefono(frmAl.jTextFieldTelefono.getText());
            alum.setEmail(frmAl.jTextFieldEmail.getText());
            alum.setDireccion(frmAl.jTextFieldDirecc.getText());
            alum.setNacion((String) frmAl.jComboBoxNac.getSelectedItem());
            alum.setRutaFoto(frmAl.jTextFieldRuta.getText());
            alum.setFoto4x4(frmAl.jCheckBoxFoto.isSelected());
            alum.setCus(frmAl.jCheckBoxCus.isSelected());
            alum.setAnalitico(frmAl.jCheckBoxAnalit.isSelected());
            alum.setCooperadora(frmAl.jCheckBoxCoop.isSelected());
            
            if(cAlum.registar(alum)){
                JOptionPane.showMessageDialog(null,"Registro Guardado");
                limpiar();
            }
               
            else JOptionPane.showMessageDialog(null,"error al Guardar");
            
        }
        //MODIFICAR//
        if(e.getSource()==frmAl.jButtonModificar){
             estableceFecha();
            alum.setIdAlumno(Integer.parseInt(frmAl.jTextFieldID.getText()));
            alum.setNombre(frmAl.jTextFieldNombre.getText());
            alum.setApellido(frmAl.jTextFieldApellido.getText());
            alum.setDni(Integer.parseInt(frmAl.jTextFieldDni.getText()));
            //alum.setFechaNac(frmAl.jDate.getDate());
            alum.setEdad(Integer.parseInt(frmAl.jTextFieldEdad.getText()));
            alum.setTelefono(frmAl.jTextFieldTelefono.getText());
            alum.setEmail(frmAl.jTextFieldEmail.getText());
            alum.setDireccion(frmAl.jTextFieldDirecc.getText());
            alum.setNacion((String) frmAl.jComboBoxNac.getSelectedItem());
            alum.setFoto4x4(frmAl.jCheckBoxFoto.isSelected());
            alum.setCus(frmAl.jCheckBoxCus.isSelected());
            alum.setAnalitico(frmAl.jCheckBoxAnalit.isSelected());
            alum.setCooperadora(frmAl.jCheckBoxCoop.isSelected());
            //System.out.println(alum.getNacion());
            if(cAlum.modificar(alum)){
                JOptionPane.showMessageDialog(null,"Registro Modificado");
                limpiar();
            }
               
            else JOptionPane.showMessageDialog(null,"error al Modificar");
            
        }
        //ELIMINAR//
        
        if(e.getSource()==frmAl.jButtonEliminar){
            //System.out.println("aquieliminar");
            alum.setIdAlumno(Integer.parseInt(frmAl.jTextFieldID.getText()));
             System.out.println(frmAl.jTextFieldID.getText());           
            if(cAlum.eliminar(alum)){
                JOptionPane.showMessageDialog(null,"Registro Eliminado");
                limpiar();
            }
               
            else JOptionPane.showMessageDialog(null,"error al Eliminar");
            
        }
        //BUSCAR//
        if(e.getSource()==frmAl.jButtonBuscar){
            alum.setDni(Integer.parseInt(frmAl.jTextFieldBuscar.getText()));
           
            
            if(cAlum.buscar(alum)){
                recuperaFecha();
                frmAl.jTextFieldID.setText(String.valueOf(alum.getIdAlumno()));
                frmAl.jTextFieldNombre.setText(alum.getNombre());
                frmAl.jTextFieldApellido.setText(alum.getApellido());
                frmAl.jTextFieldDni.setText(String.valueOf(alum.getDni()));
                //frmAl.jTextFieldFecha.setText(alum.getFechaNac());
                //frmAl.jDate.setDate((alum.getFechaNac()));
                //System.out.println("aquifecha" +alum.getFechaNac());
                frmAl.jTextFieldEdad.setText(String.valueOf(alum.getEdad()));
                frmAl.jTextFieldTelefono.setText(String.valueOf(alum.getTelefono()));
                frmAl.jTextFieldEmail.setText(alum.getEmail());
                frmAl.jTextFieldDirecc.setText(alum.getDireccion());
                //frmAl.jTextFieldNacion.setText(alum.getNacion());
                frmAl.jComboBoxNac.setSelectedItem(alum.getNacion());
                frmAl.jTextFieldRuta.setText(alum.getRutaFoto());
                frmAl.jCheckBoxFoto.setSelected(alum.isFoto4x4());
                frmAl.jCheckBoxCus.setSelected(alum.isCus());
                frmAl.jCheckBoxAnalit.setSelected(alum.isAnalitico());
                frmAl.jCheckBoxCoop.setSelected(alum.isCooperadora());
                //System.out.println(alum.getNacion());
                //String ruta=frmAl.jTextFieldRuta.getText();
                //System.out.println(ruta);
                agregaFoto();
                
                
            }
               
            else JOptionPane.showMessageDialog(null,"No se encontro registro");
            
        }
        //AGREGAR FOTO
        if(e.getSource()==frmAl.jButtonAgregarFoto){
            System.out.println("aqui");
           JFileChooser j=new JFileChooser("D:\\JAVA\\caras.");
            int ventana=j.showOpenDialog(frmAl);
                if(ventana==JFileChooser.APPROVE_OPTION){
                    String ruta=j.getSelectedFile().getAbsolutePath();
                    File archivo=j.getSelectedFile();
                    frmAl.jTextFieldRuta.setText(ruta);
                    ImageIcon imgIcon=new ImageIcon(archivo.toString());
                    Icon icono=new ImageIcon(imgIcon.getImage().getScaledInstance(frmAl.jLabelFoto.getWidth(), frmAl.jLabelFoto.getHeight(), Image.SCALE_DEFAULT));
                    frmAl.jLabelFoto.setIcon(icono);
                }
            
        }
                
        //LIMPIAR
        if(e.getSource()==frmAl.jButtonLimpiar){
          
            limpiar();
            
        }
        //CARGA COMBO NACION
        /*if(e.getSource()==frmAl.jComboBoxNac){
            alum.setNacion((String) frmAl.jComboBoxNac.getSelectedItem());
            frmAl.jTextFieldNacion.setText(alum.getNacion());
           
        }*/
        //VOLVER
        if(e.getSource()==frmAl.jButtonVolver){
                frmMenu miM=new frmMenu();
                CtrlMenu ctrlM= new CtrlMenu(miM);
                ctrlM.iniciar();
                miM.setVisible(true);
                frmAl.dispose();
        }
        
              
    }
    
    public void limpiar(){
        frmAl.jTextFieldID.setText(null);
        frmAl.jTextFieldNombre.setText(null);
        frmAl.jTextFieldApellido.setText(null);
        frmAl.jTextFieldDni.setText(null);
        //frmAl.jTextFieldFecha.setText(null);
        frmAl.jTextFieldEdad.setText(null);
        frmAl.jTextFieldTelefono.setText(null);
        frmAl.jTextFieldEmail.setText(null);
        frmAl.jTextFieldDirecc.setText(null);
        frmAl.jComboBoxNac.setSelectedIndex(0);
        //frmAl.jComboBoxNac.setSelectedIndex(0);
        frmAl.jTextFieldRuta.setText(null);
        frmAl.jTextFieldBuscar.setText(null);
        frmAl.jLabelFoto.setIcon(null);
        frmAl.jCheckBoxFoto.setSelected(false);
        frmAl.jCheckBoxCus.setSelected(false);
        frmAl.jCheckBoxAnalit.setSelected(false);
        frmAl.jCheckBoxCoop.setSelected(false);
        frmAl.jDate.setDate(null);
    }
    
   public void agregaFoto(){
                   
                   ImageIcon imgIcon=new ImageIcon(frmAl.jTextFieldRuta.getText());
                   Icon icono=new ImageIcon(imgIcon.getImage().getScaledInstance(frmAl.jLabelFoto.getWidth(), frmAl.jLabelFoto.getHeight(), Image.SCALE_DEFAULT));
                   //frmAl.jLabelFoto.setIcon(new ImageIcon(frmAl.jTextFieldRuta.getText()));
                   frmAl.jLabelFoto.setIcon(icono);
                   //System.out.println("aaqui");
                    //System.out.println(ruta);
    }
   
   public void estableceFecha(){
       
            //System.out.println("fecha" +frmAl.jDate.getDate());
            Date fecha=frmAl.jDate.getDate();
            //frmAl.jDate.setDate(fecha);
            DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
            String fecha2=f.format(fecha);
            alum.setFechaNac(fecha2);
            LocalDate cumple=LocalDate.parse(fecha2);
            //System.out.println("fecha2"+fecha2);
            LocalDate ahora=LocalDate.now();
            //System.out.println("cumple"+cumple);
            Period periodo=Period.between(cumple, ahora);
            //System.out.println("nowwwww"+periodo.getYears());
            frmAl.jTextFieldEdad.setText(String.valueOf(periodo.getYears()));
           
            
            
            //System.out.println(alum.getFechaNac());
        
            
   }
   
   public void recuperaFecha(){
       SimpleDateFormat miFormato=new SimpleDateFormat("yyyy-MM-dd");
       String strFecha=alum.getFechaNac();
       Date fecha=null;
        try {
            fecha=miFormato.parse(strFecha);
            frmAl.jDate.setDate(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(CtrlAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }

    @Override
    public void focusGained(FocusEvent e) {
       estableceFecha();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

   
       
   }




