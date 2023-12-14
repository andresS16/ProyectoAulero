/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Carrera;
import Modelo.Edificio;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class IngresoEdificio implements Initializable {

    @FXML
    private AnchorPane anchorPane;  
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    
    @FXML
    private TextField txtNumeroAula;
    @FXML
    private TextField txtNumeroEdificio;
  // private ComboBox<String> comboEdificio;
    
    TablaEdificio tablaEdificio= new TablaEdificio();
     ArrayList<String> listaEdificio = new ArrayList<>();
    @FXML
    private TextField txtNombreEdificio;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaEdificio=seleEdificio();
        //comboEdificio.getItems().addAll(listaEdificio);
       
    } 
    
      public void setControladorEscena1(TablaEdificio controladorEscena1) {
        this.tablaEdificio = controladorEscena1;
    }
    public void llamarMetodoDeOtraEscena() {
        if (tablaEdificio != null) {
            JOptionPane.showMessageDialog(null,"Entra al metodo llamar metodoDeOtraScena","aviso" , JOptionPane.INFORMATION_MESSAGE);   
            //ActionEvent event = null;
            // Llamar al método del controlador de la primera escena        
            tablaEdificio.refresch();
           //tablaCarrera.refresch();
            //tablaCarrera.rellenarTablaCarrera();   
            //tablaCarrera.refrescarTabla();
        }
    }

    @FXML
    private void actionEvent(ActionEvent e) {
          Object evento = e.getSource();
        
        if(evento.equals(btnEliminar)){            
                eliminarEdificio(); 
                llamarMetodoDeOtraEscena();
                 
          }else if(evento.equals(btnModificar)){               
                modificarEdificio();               
                llamarMetodoDeOtraEscena();                            
              }else if(evento.equals(btnGuardar)){  
                    guardarEdificio();
                    llamarMetodoDeOtraEscena(); 
                }    
    }
    /*public void vaciarCampos(){            
        txtNumeroEdificio.setText("");
        txtNumeroAula.setText("");          
    }*/
       public ArrayList<String> seleEdificio(){
                
        ArrayList<String> lista  = new ArrayList<>();        
        String query = "select nombre FROM edificio ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                      String edificio =rs.getString("nombre");
                    lista.add(edificio);        
                    edificio="";
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCarrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
       // System.out.println("la carrera es " + carrera.getNombre_carrera());
       return lista;             
    }

    
    private void eliminarEdificio() { 
         
         Stage stage= (Stage)this.anchorPane.getScene().getWindow();//paso 1
         Edificio viajeroEliminar= (Edificio)stage.getUserData();//paso2 
         String ver =this.txtNumeroEdificio.getText();
         String ala = this.txtNumeroAula.getText();      
        // JOptionPane.showMessageDialog(null,"Entra al metodoElimunar num edificio : "+viajeroEliminar.getNumeroEdificio()+ver+ala ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
         TablaEdificio te = new TablaEdificio();   //tc
         Edificio edificio =null;       
         
         if( !this.txtNumeroEdificio.getText().isEmpty() && !this.txtNumeroAula.getText().isEmpty()){
              String bandera= this.txtNumeroEdificio.getText();
              String bandera2= this.txtNumeroAula.getText();
              boolean isNumerico = bandera.matches("[+-]?\\d*(\\.\\d+)?");// expresion regular qu verifica si es numero
              boolean isNumerico2 = bandera2.matches("[+-]?\\d*(\\.\\d+)?");
            
                if(isNumerico ==true && isNumerico2 ==true){
                     edificio= te.buscarEdificioNumero(viajeroEliminar.getNumeroEdificio());
                     
                     if(edificio != null){
                        int opcion = JOptionPane.showConfirmDialog(null,
                        "Desea eliminar " + "el registro ? " , "confirmacion" ,JOptionPane.YES_NO_OPTION,2);
                        
                        if(opcion== JOptionPane.YES_OPTION  ){

                                String query ="DELETE FROM `edificio` WHERE `edificio`.`id_edificio` ='" + edificio.getId() +"' ";// traer() trae id
                                TransaccionesBD trscns = new TransaccionesBD();
                                boolean exito = trscns.ejecutarQuery(query);

                                JOptionPane.showMessageDialog(null,"Se elimino correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                                //vaciarCampos();
                                /*Stage*/ stage =(Stage) this.btnEliminar.getScene().getWindow();
                                stage.close();                                                                                  
                            }else if(opcion== JOptionPane.NO_OPTION){
                                //vaciarCampos();
                                /*Stage */stage =(Stage) this.btnEliminar.getScene().getWindow();
                                stage.close();
                                JOptionPane.showMessageDialog(null,"Operacion cancelada" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                                stage =(Stage) this.btnEliminar.getScene().getWindow();
                                stage.close();
                            }
                                               
                     }else {
                      JOptionPane.showMessageDialog(null," numero no encontrado" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                     }                                           
                }else{
                    JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                }
                           
            }else{
                    JOptionPane.showMessageDialog(null,"Debe seleccionar edificio" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                    stage.close();
                             JOptionPane.showMessageDialog(null,"Operacion cancelada" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                             stage =(Stage) this.btnEliminar.getScene().getWindow();
                             stage.close();
        }    
    }
   
    private void modificarEdificio() {   
        
        Stage stage= (Stage)this.anchorPane.getScene().getWindow();//paso 1
        Edificio viajero = (Edificio)stage.getUserData();//paso2  
        Edificio consultaBD=null;
        TablaEdificio tc = new TablaEdificio();                                                                            
            //JOptionPane.showMessageDialog(null,"entra en metodo modificar, numeroAula :"+ viajero.getNumeroAula() ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
 
                if(this.txtNumeroEdificio.getText().isEmpty() && this.txtNumeroAula.getText().isEmpty() ){
                    JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                           

                    }else{
                            String numeroEdificio= this.txtNumeroEdificio.getText();
                            boolean isNumericoEdificio = numeroEdificio.matches("[+-]?\\d*(\\.\\d+)?");// expresion regular qu verifica si es numero
                            String numeroAula= this.txtNumeroAula.getText();
                            boolean isNumericoAula = numeroAula.matches("[+-]?\\d*(\\.\\d+)?");


                        if(isNumericoEdificio == true && isNumericoAula == true && viajero != null){                      
                                consultaBD= tc.buscarEdificioNumero(viajero.getNumeroEdificio());
                                JOptionPane.showMessageDialog(null,"Entra al if ..valida si es numero " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                                int valorA= Integer.parseInt( numeroEdificio);
                                int valorB=Integer.parseInt( numeroAula);
                                viajero.setNumeroEdificio(valorA);
                                viajero.setNumeroAula(valorB);
                                
                            if(!consultaBD.equals(viajero)){
                                    //JOptionPane.showMessageDialog(null,"valor viajero "+valorA+ " ,valor caja texto "+valorB  ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                                    String query = "UPDATE edificio SET "
                                    + "numeroEdificio = '"+ this.txtNumeroEdificio.getText()+"',"
                                    + " numeroAula='"  + this.txtNumeroAula.getText()+ "' WHERE id_edificio = '" + consultaBD.getId()+"' " ;

                                    TransaccionesBD trscns = new TransaccionesBD();
                                    boolean exito = trscns.ejecutarQuery(query);
                                    JOptionPane.showMessageDialog(null," edificio modificado " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                                   //vaciarCampos();
                                    stage =(Stage) this.btnGuardar.getScene().getWindow();
                                    stage.close(); 

                             }else{
                             JOptionPane.showMessageDialog(null,"debe ingresar modificacion edificio" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                            }
                        }else{
                              JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                        }
                }

     }
    
    private void guardarEdificio() {
        
        Edificio e= new Edificio();   
        Edificio c1=null;
        IngresoEdificio ic= new IngresoEdificio();
       TablaEdificio tc=new TablaEdificio();                 
        long id ;         
        id=ic.id_incrementable();               
        String bandera =this.txtNumeroEdificio.getText(); 
        //boolean isNumerico = bandera.chars().allMatch( Character::isDigit ); para validar el ingreso de solo letras
        boolean isNumerico = bandera.matches("[+-]?\\d*(\\.\\d+)?");
        String nombre = this.txtNumeroAula.getText(); 
        JOptionPane.showMessageDialog(null,"Entro al metodo guaradar" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //tc.rellenarTablaCarrera();
                                                                      
        if(bandera.equals("") || nombre.equals("") ){
            
            JOptionPane.showMessageDialog(null,"Falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //tc.rellenarTablaCarrera();
            return ;
            
        }else if(isNumerico == true) {                                                                           
                c1= tc.buscarEdificioNumero(Integer.parseInt(this.txtNumeroEdificio.getText()));             
                if(c1 !=null ){                                    
                    JOptionPane.showMessageDialog(null,"La edificio ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     
                }else {                                                                                                                                                                                                                                                 
                    //e.setId(id);
                    e.setNombreEdificio(this.txtNombreEdificio.getText());  
                    e.setNumeroEdificio(Integer.parseInt(this.txtNumeroEdificio.getText()));                                                          
                    e.setNumeroAula(Integer.parseInt(this.txtNumeroAula.getText()));                             
                    ic.insertar(e);
                    JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                    Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                    stage.close();                               //return; 
                }                                                                         
           // }                  
        }else{                
            JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
          }             
    }
    
     public long id_incrementable(){
           
        long id=0;       
        String query="SELECT MAX(id_edificio) FROM edificio";              
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
    
    public boolean insertar(Edificio c){
         
        String query = "INSERT INTO edificio(nombre,numeroEdificio,cantidadAula)" 
                + "VALUES(' " 
                + c.getNombreEdificio()
                   + " ',' " 
                + c.getNumeroEdificio()
                + " ',' " 
                + c.getNumeroAula()
                + "' )";
          
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        Long id=id_incrementable();
           
       JOptionPane.showMessageDialog(null,"entro en metodo insertar " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }      
}
