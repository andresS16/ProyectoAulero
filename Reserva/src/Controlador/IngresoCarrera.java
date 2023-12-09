/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Aula;
import Modelo.Carrera;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Silva
 */
public class IngresoCarrera implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<Integer> comboCursada;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    private long id;
    @FXML
    private AnchorPane anchorPane;
    TablaCarrera tablaCarrera= new TablaCarrera();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboCursada.getItems().addAll(1,2,3,4,5);                  
    }  
    
    /*METODOS:  ActionEVENT, vaciarCampos ,traerCarrera, eliminarCarrera, modificarCarrera, 
    guardarCarrera, ID_Incrementable, insertar, verificarTexto, verificarTexto*/
    
    public void setControladorEscena1(TablaCarrera controladorEscena1) {
        this.tablaCarrera = controladorEscena1;
    }
    public void llamarMetodoDeOtraEscena() {
        if (tablaCarrera != null) {
            JOptionPane.showMessageDialog(null,"Entra al metodo llamar metodoDeOtraScena","aviso" , JOptionPane.INFORMATION_MESSAGE);   
            //ActionEvent event = null;
            // Llamar al método del controlador de la primera escena
            tablaCarrera.refrescar(null); 
           //tablaCarrera.refresch();
            //tablaCarrera.rellenarTablaCarrera();   
            //tablaCarrera.refrescarTabla(); 
           
        }
    }
     
      @FXML
    private void actionEvent(ActionEvent e) {
        
        Object evento = e.getSource();
        
        if(evento.equals(btnEliminar)){            
            eliminarCarrera(); 
            llamarMetodoDeOtraEscena();
            //JOptionPane.showMessageDialog(null,"llama al metodo eliminar"+ nombre,"aviso" , JOptionPane.INFORMATION_MESSAGE);         
        }else if(evento.equals(btnModificar)){               
            modificarCarrera();               
            llamarMetodoDeOtraEscena();        
            //JOptionPane.showMessageDialog(null,"Entra al ActionEven Modificar","aviso" , JOptionPane.INFORMATION_MESSAGE);               
        }else if(evento.equals(btnGuardar)){  
            guardarCarrera();
            llamarMetodoDeOtraEscena(); 
        }                                       
    }
    
     public void bandera(){
       JOptionPane.showMessageDialog(null,"Entra al metodo BANDERA","aviso" , JOptionPane.INFORMATION_MESSAGE);   
     
     }
    
    public void vaciarCampos(){            
        txtNombre.setText("");                  
        comboCursada.setValue(null);          
    }
    
    public void traerCarrera(Carrera c){
                           
        if(c !=null){                                            
            this.txtNombre.setText(c.getNombre());          
            this.comboCursada.setValue(c.getAño());                                  
        }       
        String query = "select id_carrera from carrera where nombre='"+ c.getNombre()+"'";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        try{
            if(rs.next()){                   
                //JOptionPane.showMessageDialog(null, "entro en el if para asignar", "Error",JOptionPane.WARNING_MESSAGE);                 
                c.setId(rs.getLong("id_carrera")); 
                //this.id = c.getId(); 
            //c.setNombre(rs.getString("nombre"));
            }           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"error en metodo traer carrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
        }   
    }
     
    private void eliminarCarrera() { 
         Stage stage= (Stage)this.anchorPane.getScene().getWindow();//paso 1
         Carrera ca = (Carrera)stage.getUserData();//paso2                            
         TablaCarrera tc = new TablaCarrera();   
         Carrera carrera=null;                                      
         carrera= tc.buscarCarreraNombre(ca.getNombre());
         ca.setNombre(this.txtNombre.getText());
         txtNombre.setEditable(false);// para que se pueda editar 
     
       // JOptionPane.showMessageDialog(null,"metodo buscarCarrera trajo id:"+ carrera.getId() +carrera.getNombre() + ca ,"aviso" , JOptionPane.INFORMATION_MESSAGE);     
        //String bandera= (String)carrera.getNombre();
                                
        if( !this.txtNombre.getText().isEmpty()&& 
                this.comboCursada.getValue()!=null)if(ca.getNombre().equals(carrera.getNombre())){                                                                  
                    int opcion = JOptionPane.showConfirmDialog(null,
                            "Desea eliminar " + "el registro ? " , "confirmacion" ,JOptionPane.YES_NO_OPTION,2);
                    
                    if(opcion== JOptionPane.YES_OPTION  ){
                        
                        String query ="DELETE FROM `carrera` WHERE `carrera`.`nombre` ='" + this.txtNombre.getText() +"' ";// traer() trae id
                        TransaccionesBD trscns = new TransaccionesBD();
                        boolean exito = trscns.ejecutarQuery(query);
                        
                        JOptionPane.showMessageDialog(null,"Se elimino correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                        vaciarCampos();
                        /*Stage*/ stage =(Stage) this.btnEliminar.getScene().getWindow();
                        stage.close();                                                                                  
                    }else if(opcion== JOptionPane.NO_OPTION){
                        vaciarCampos();
                        /*Stage */stage =(Stage) this.btnEliminar.getScene().getWindow();
                        stage.close();
                        JOptionPane.showMessageDialog(null,"Operacion cancelada" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Debe seleccionar carrera" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                    vaciarCampos();
                    /*Stage */stage =(Stage) this.btnEliminar.getScene().getWindow();
                    stage.close();
                }else {
            JOptionPane.showMessageDialog(null,"falta seleccionar campos " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
            //tc.rellenarTablaCarrera();
            return ;            
        }     
       
    }
    
    private void modificarCarrera() {   
        
        Stage stage= (Stage)this.anchorPane.getScene().getWindow();//paso 1
        Carrera viajero = (Carrera)stage.getUserData();//paso2  
        Carrera consultaBD=null;
        TablaCarrera tc = new TablaCarrera();
        consultaBD= tc.buscarCarreraNombre(viajero.getNombre());// retorna carrera consultada en BD
        
                                                                 
        //String nombre = this.txtNombre.getText();              
    // JOptionPane.showMessageDialog(null,"Metodo mODIFICAR EL ID ES :"+ id ,"aviso" , JOptionPane.INFORMATION_MESSAGE);       
        if(  this.txtNombre.getText().isEmpty()|| this.comboCursada.getValue()== null  ){
               
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //rellenarTablaMateria();
            return ;
                    
        }else {
            
            String valorA=viajero.getNombre();
            String valorB=this.txtNombre.getText();
            JOptionPane.showMessageDialog(null,"valor viajero "+valorA+ " ,valor caja texto "+valorB  ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
            if (valorA.equalsIgnoreCase(valorB)){                                      
            
                JOptionPane.showMessageDialog(null,"debe ingresar modificacion materia" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                
                                      
            }else{
                
                String query = "UPDATE carrera SET "
                        + "nombre = '"+ this.txtNombre.getText()+"',"
                        + " año='"  + this.comboCursada.getValue()+ "' WHERE id_carrera = '" + consultaBD.getId()+"' " ;

                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);
                JOptionPane.showMessageDialog(null,"carrera modificado " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                //vaciarCampos();
                stage =(Stage) this.btnGuardar.getScene().getWindow();
                stage.close();             

            }                                                                                                                                                                                    
         }
    }
    

    private void guardarCarrera() {
        
        Carrera c= new Carrera();   
        Carrera c1=null;
        IngresoCarrera ic= new IngresoCarrera();
        TablaCarrera tc=new TablaCarrera();                 
        long id ;         
        id=ic.id_incrementable();               
        String bandera =this.txtNombre.getText(); 
        boolean isNumerico = bandera.chars().allMatch( Character::isDigit );                   
        String nombre = this.txtNombre.getText(); 
                                                                      
        if(bandera.equals("") || nombre.equals("") ||  this.comboCursada.getValue()== null){
            
            JOptionPane.showMessageDialog(null,"Falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //tc.rellenarTablaCarrera();
            return ;
            
        }else if(isNumerico != true) {              
            boolean isNumero = false;            
            isNumero = !nombre.matches(".*\\d.*"); // if ternario :contiene un numero
             // no contiene un numero }                                             
            if( isNumero != false){                                                     
                c1= tc.buscarCarreraNombre(this.txtNombre.getText());  
                
                if(c1 !=null ){                                    
                    JOptionPane.showMessageDialog(null,"La carrera ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     
                }else {                                                                                                                                                                                                                                                 
                    c.setId(id);
                    c.setNombre(this.txtNombre.getText());                                                          
                    c.setAño(this.comboCursada.getValue());                             
                    ic.insertar(c);
                    JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                    Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                    stage.close();                               //return; 
                }                                                                         
            }                  
        }else{                
            JOptionPane.showMessageDialog(null,"Debe ingresar letras" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
          }             
    }

  
    
    public long id_incrementable(){
           
        long id=0;       
        String query="SELECT MAX(id_carrera) FROM carrera";              
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        try{
            while(rs.next()){                                                        
                id= rs.getLong(1)+1;
             }                                             
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"error al buscar id" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);                     
        }
        finally{//clase que cierra la coexion para evitar consumo de memoria
            try{
                
           rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar id" + ex , "ERROR", JOptionPane.ERROR_MESSAGE); 
            }                     
        }       
        return id;
    }
    
    public boolean insertar(Carrera c){
         
        String query = "INSERT INTO carrera(id_carrera,nombre,año)" 
                + "VALUES(' "
                
                +  c.getId()
                + " ',' " 
                + c.getNombre()
                + " ',' " 
                + c.getAño()                                           
                + "' )";
          
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        
       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
    private void verificarTexto(java.awt.event.KeyEvent e){
        
         int key = e.getKeyChar(); 
       // key event.getCharacter()
        
        boolean mayusculas = key >= 65 && key <=90;
        boolean minusculas = key >= 97 && key <=122;
        
        if(!(mayusculas || minusculas)){
            e.consume();
        }
        
    }

    @FXML
    private void verificarTexto(KeyEvent e) {
       
    String str = e.getCharacter();
    int tamaño = str.length();
    for(int i=0;i< tamaño; i++){
        Character c = str.charAt(i);
        if(! Character.isLetter(c)){
            e.consume();
        }
    }}
    
}
