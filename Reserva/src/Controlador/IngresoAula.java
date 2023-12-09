
package Controlador;

import Modelo.Aula;
import Modelo.Edificio;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class IngresoAula implements Initializable {

    @FXML
    private TextField txtCapacidad;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private TextField txtNumeroAula;
    @FXML
    private AnchorPane anchorPane;
    TablaAula tablaAula = new TablaAula();
    @FXML
    private ComboBox<String> comboEdificio;
    
    ArrayList<String> listaEdificio = new ArrayList<>();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaEdificio=seleEdificio();
        //comboEdificio.getItems().addAll(listaEdificio);
        //actualizarSumaEdificio("Suipacha");
       ObservableList<String> nombresEdificios = FXCollections.observableArrayList("polo",
            "suipacha");
       comboEdificio.setItems(nombresEdificios);
           }  
    
    
    public void setControladorEscena1(TablaAula controladorEscena1) {
        this.tablaAula = controladorEscena1;
         comboEdificio.setStyle("-fx-alignment: CENTER_LEFT;");
        
    }
    public void llamarMetodoDeOtraEscena() {
        if (tablaAula != null) {
            JOptionPane.showMessageDialog(null,"Entra al metodo llamar metodoDeOtraScena","aviso" , JOptionPane.INFORMATION_MESSAGE);   
            //ActionEvent event = null;
            // Llamar al método del controlador de la primera escena
            //tablaAula.refrescar(null);
             tablaAula.refresch();
            //tablaAula.rellenarTablaCarrera();   
            //tablaAula.refrescarTabla();           
        }
    }
    
    public ArrayList<String> seleEdificio(){
                
        ArrayList<String> lista  = new ArrayList<>();        
        String query = "select edificio FROM aula where 1 ORDER BY edificio ASC;";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                      String edificio =rs.getString("edificio");
                    lista.add(edificio);        
                    //edificio="";
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCarrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
       // System.out.println("la carrera es " + carrera.getNombre_carrera());
       return lista;             
    }
    
    public Aula traerAula(Aula a){ //trae lo seleccionado de la tabla
                                     
        if(a!=null){             
            this.txtNumeroAula.setText(a.getNumAula()+ "");         
            this.txtCapacidad.setText(a.getCapacidad()+"") ;
            this.comboEdificio.setValue(a.getEdificio());
            JOptionPane.showMessageDialog(null, "ENTRO AL METODO TraerAula con numeroAula = " + a.getNumAula(), "Error",JOptionPane.WARNING_MESSAGE); 
            }
             String query= "select numeroAula , capacidad , edificio ,id_aula from aula where numeroAula='"+a.getNumAula()+ "'";
             TransaccionesBD trscns = new TransaccionesBD();
             ResultSet rs = trscns.realizarConsulta(query);
            try{
                if(rs.next()){                                                            
                    a.setId(rs.getInt("id_aula"));                                        
                     a.setNumAula(rs.getInt("numeroAula"));                                                                                            
                     a.setCapacidad(rs.getInt("capacidad"));    
                     a.setEdificio(rs.getString("edificio"));                                                                                                                                   
                    }           
             }catch(HeadlessException | SQLException ex){
                   JOptionPane.showMessageDialog(null,"error en metodo traer aula " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
                    }   return a;           
   }
         
   private void guardarAula() {
         Aula a = new Aula();          
         IngresoAula ia = new IngresoAula();
         TablaAula ta = new TablaAula();
                                         
        if(this.txtNumeroAula.getText().isEmpty() || this.txtCapacidad.getText().isEmpty() || comboEdificio.getValue()== null ){           
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //ta.rellenarTablaAula();
                   
        } else {            
                boolean isNumerico = this.txtNumeroAula.getText().chars().allMatch( Character::isDigit );
                boolean isNumerico1 = this.txtCapacidad.getText().chars().allMatch( Character::isDigit );

                    if(isNumerico != false && isNumerico1 != false) {              
                        boolean isNumero = false;                                                                                                                                
                    Aula a1 = ta.buscarAulaNumero(Integer.parseInt(this.txtNumeroAula.getText())); 
                   
                            if(a1 !=null ){                                    
                                JOptionPane.showMessageDialog(null,"El aula ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     
                                }else {                                                                                                                                                                                                                                                 
                                    a.setNumAula(Integer.parseInt(this.txtNumeroAula.getText()));
                                    a.setCapacidad(Integer.parseInt(this.txtCapacidad.getText()));
                                    a.setEdificio(comboEdificio.getValue());
                                    ia.insertar(a);
                                   // Edificio edi=buscarID(this.comboEdificio.getValue());
                                    //actualizarSumaEdificio(edi.getId());
                                    actualizarSumaEdificio();

                                    
                                    JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                                    Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                                   
                                    stage.close();  
                                    //ta.refresch();//return;
                                    //ta.configurarVentana();
                                }                                                                                                                 
                }else{                
                    JOptionPane.showMessageDialog(null,"Debe ingresar numeros " ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                  }          
            }            
    }
     
    private void eliminarAula() {
        TablaAula tc = new TablaAula();           
        Aula a = new Aula();  
        
            if( this.txtNumeroAula.getText().isEmpty() || this.txtCapacidad.getText().isEmpty()){

                JOptionPane.showMessageDialog(null,"falta seleccionar campos " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
                //tc.rellenarTablaCarrera();
                return ;

                }else if(this.txtNumeroAula!=null){                                                                  
                    int opcion = JOptionPane.showConfirmDialog(null, 
                            "Desea eliminar " + "el registro ? " , "confirmacion" ,JOptionPane.YES_NO_OPTION,2);

                        if(opcion== JOptionPane.YES_OPTION  ){

                                String query ="DELETE FROM `aula` WHERE `aula`.`numeroAula` ='" + this.txtNumeroAula.getText() +"' ";// traer() trae id
                                TransaccionesBD trscns = new TransaccionesBD();
                                boolean exito = trscns.ejecutarQuery(query);                                                
                                actualizarRestaEdificio();
                                                           
                                JOptionPane.showMessageDialog(null,"Se elimino correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                                vaciarCampos();                           
                                Stage stage =(Stage) this.btnEliminar.getScene().getWindow();
                                stage.close();
                         }else if(opcion== JOptionPane.NO_OPTION){
                                    vaciarCampos();
                                    Stage stage =(Stage) this.btnEliminar.getScene().getWindow();
                                    stage.close(); 
                                    JOptionPane.showMessageDialog(null,"Operacion cancelada" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                            }                                                      
                }else{
                        JOptionPane.showMessageDialog(null,"Haga un refresh luego seleccione aula" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                        Stage stage =(Stage) this.btnModificar.getScene().getWindow();
                        stage.close();           
                  }                     
    }
    
    public boolean insertar(Aula a){        
        String query = "INSERT INTO aula(numeroAula,capacidad,edificio)" 
                + "VALUES(' "
                + a.getNumAula()
                + " ',' " 
                +  a.getCapacidad()
                +" ',' " 
                +    a.getEdificio()
                + "' )";

                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);
               
                       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
    
     public void actualizarSumaEdificio(){  
         
            String nombre = null;           
           if (this.comboEdificio != null) {
               nombre = this.comboEdificio.getValue();              
            }
                                
           String query = "UPDATE edificio SET cantidadAula = cantidadAula + 1 WHERE nombre = '" + nombre.trim() + "'";
             
                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);  
                
            try {
                if (exito) {
                    JOptionPane.showMessageDialog(null, "La actualización fue exitosa para " + nombre, "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }    
                         
      }
     
    public void actualizarRestaEdificio(){  
         
            String nombre = null;
            
           if (this.comboEdificio != null) {
               nombre = this.comboEdificio.getValue();
                //String trim = nombre.trim();
               System.out.println("combo lleno : '" + nombre + "'");
            }
           nombre = this.comboEdificio.getValue();
                String trim = nombre.trim();
           System.out.println("Valor de nombre: '" + nombre + "'");
           String query = "UPDATE edificio SET cantidadAula = cantidadAula - 1 WHERE nombre = '" +trim + "'";
     
                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);  
                
            try {
                if (exito) {
                    JOptionPane.showMessageDialog(null, "La actualización fue exitosa para " + nombre, "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }    
                         
      }      
          
    public void vaciarCampos(){            
        txtNumeroAula.setText("");                   
        txtCapacidad.setText("");         
    }
    
    private void ModificarAula() {   
        if(this.txtNumeroAula.getText()!=null && this.txtCapacidad.getText()!=null && comboEdificio.getValue()!= null){  
                Aula a = new Aula();               
                TablaAula ta = new TablaAula();   
                Stage stage= (Stage)this.anchorPane.getScene().getWindow();//paso 1
                Aula u = (Aula)stage.getUserData();//paso2
                a.setNumAula(Integer.parseInt(this.txtNumeroAula.getText()));
                a.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                a.setEdificio(this.comboEdificio.getValue());        
                String valor =this.comboEdificio.getValue();

                JOptionPane.showMessageDialog(null,"valor del combo es :" +valor +"" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                JOptionPane.showMessageDialog(null,"valor del objeto es :" +u.getEdificio() +"" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 

            if(txtNumeroAula.getText().isEmpty() || txtCapacidad.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
                //rellenarTablaMateria();
                return ;

              } else if(a.equals(u)){ 
                   
                      JOptionPane.showMessageDialog(null,"Debe ingresar modificaciones " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                          

                    }else if(!a.equals(u)){
                            String query = "UPDATE aula SET "
                               + "numeroAula = '" +this.txtNumeroAula.getText()+ "',"
                               + " capacidad ='"  + this.txtCapacidad.getText() + "',"
                            + " edificio='"  + this.comboEdificio.getValue() + "' WHERE id_aula = '" + u.getId()+"' " ;
                            
                           TransaccionesBD trscns = new TransaccionesBD();
                           boolean exito = trscns.ejecutarQuery(query);
                           JOptionPane.showMessageDialog(null,"aula modificada " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                         // vaciarCampos();
                           stage =(Stage) this.btnModificar.getScene().getWindow();
                           stage.close(); 
                           ta.refresch();

                        }else{
                             JOptionPane.showMessageDialog(null,"Haga un refrech luego seleccione aula" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                              stage =(Stage) this.btnModificar.getScene().getWindow();
                              stage.close();
                         } 
        }else{
             JOptionPane.showMessageDialog(null,"Debe seleccionar aula" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
             
           }
    }   
         
    @FXML
    private void actionEvent(ActionEvent e) {
        Object evento = e.getSource();
        
        if(evento.equals(btnEliminar)){            
            eliminarAula(); 

            llamarMetodoDeOtraEscena();                 
         }else if(evento.equals(btnModificar)){               
                ModificarAula();               
                llamarMetodoDeOtraEscena();        

             }else if(evento.equals(btnGuardar)){  
                   guardarAula();
                   
                   llamarMetodoDeOtraEscena(); 
                }
    }
   
}
