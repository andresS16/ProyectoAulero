/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Aula;
import Modelo.Carrera;
import Modelo.Edificio;
import Modelo.Horario;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TablaEdificio implements Initializable {
    private final ObservableList<Edificio> listaEdificio = FXCollections.observableArrayList();

    @FXML
    private TextField textBuscar;
    @FXML
    private ChoiceBox<?> chcBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField lblResultado;
    @FXML
    private Button btnRefrescar;
    @FXML
    private Button bttNuevoAlumno;
    @FXML
    private TableColumn<Edificio, Integer> colEdificio;
    @FXML
    private TableColumn<Edificio,Integer> colNumeroAula;
    @FXML
    private TableView<Edificio> tblEdificio;
    @FXML
    private TableColumn<Edificio, String> colNombre;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // agregarEdificio();
        configurarVentana();
        rellenarTablaEdificio();
        
    }    /*configurrarventana, seleccionar*/
    
    public void configurarVentana(){       
        colEdificio.setCellValueFactory(new PropertyValueFactory<>("numeroEdificio"));
        colNumeroAula.setCellValueFactory(new PropertyValueFactory<>("numeroAula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEdificio")); 
        tblEdificio.setItems(listaEdificio);               
     }
    
     @FXML
    private void seleccionar(MouseEvent event) {
                 
        Edificio c = this.tblEdificio.getSelectionModel().getSelectedItem();    
       
        if(c ==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);   
            
        }else{  
            
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/IngresoEdificio.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoEdificio controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.setControladorEscena1(this);
                                                 // controlador.traerEdificio(c);
            //controlador.seleCarrera();
            Scene scene = new Scene(root);
            Stage stage = new Stage(); 
            stage.setUserData(c);
            this.tblEdificio.getSelectionModel().clearSelection();
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);            
            stage.showAndWait();  
            
          }catch (IOException ex) {             
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();  
          }           
        }              
    }
              
    /*public void agregarEdificio(){// metodo solo se usa para cargar una sola vez los numero de edificios en BD, Sino se cargarian vrias veces el mismo dato con distinto id
      
        for(int h=1 ; h < 3; h++){              
            //lista.addAll(new Horario("  9:00", "matematicas ", "","","","" ));           
            //String bandera= h +":00"+" a " + a +":00" ; 
            //String bandera= h+"";          
            insertarEdificio(h);
            listaEdificio.addAll(new Edificio(h));
        }    
    } */
    
    public void rellenarTablaEdificio(){        
        //aulas.clear();                   
         TablaEdificio tabla= new TablaEdificio();
         ObservableList<Edificio> resultado=tabla.buscarTodos();  
         listaEdificio.setAll(resultado);
        // tblAula.setItems(resultado);
        //int resultados=resultado.size();       
        //lblResultado.setText("resultado :" + resultados);                    
        //cuantos resultados hay en la lista                                                     
    }
    
     @FXML
    public void refrescar(ActionEvent event) {
             
        rellenarTablaEdificio();      
         //JOptionPane.showMessageDialog(null, "Entro al metodo refrescar(ActionEvent)", "Error",JOptionPane.WARNING_MESSAGE);
        try {               
            DefaultTableModel modelo = new DefaultTableModel();         
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        }      
    }
     public void refresch() {
           
        rellenarTablaEdificio();    
        JOptionPane.showMessageDialog(null, "Entro al metodo refresch", "Error",JOptionPane.WARNING_MESSAGE);
       
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        }         
    }
    
    
    public ObservableList<Edificio> buscarTodos(){
                         
        String query = "SELECT * FROM edificio";
        TransaccionesBD trscns = new TransaccionesBD(); 
        ResultSet rs = trscns.realizarConsulta(query);
        Edificio e =null;                 
        ObservableList<Edificio>listaMetodoBuscar= FXCollections.observableArrayList();
       
       try{
            while(rs.next()){  
                
                e=new Edificio();
                
                e.setNumeroEdificio(rs.getInt("numeroEdificio"));
                 e.setNumeroAula(rs.getInt("cantidadAula"));
                 e.setNombreEdificio(rs.getString("nombre"));
               
               // a.setCapacidad(rs.getInt("capacidad")); 
               //String suma= a.getHora();
               //int valor= Integer.parseInt(suma)+1;
               //a1.setHora(a.getHora()+":00"+" a "+ valor+":00");
               
                listaMetodoBuscar.add(e);                                      
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar edificio" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return listaMetodoBuscar;
       }

    @FXML
    private void buscarAlumno(ActionEvent event) {
        
        
        
    }

    
    /*public boolean insertarEdificio(int a){    a    
        String query = "INSERT INTO edificio(numeroEdificio)" + "VALUES(' " + a + "' )";

                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);    

                       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }*/
     
    public Edificio buscarEdificioNumero(int numeroEdificio){
         
        //JOptionPane.showMessageDialog(null, "Ingreso en metodo buscarprofeApellido", "Error",JOptionPane.WARNING_MESSAGE);            
        String query = "SELECT * FROM edificio WHERE numeroEdificio LIKE '%" + numeroEdificio+ "%'";           
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);       
        Edificio e =null;  
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);           
        try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);                   
                    e=new Edificio();
                    e.setId(rs.getInt("id_edificio"));
                    e.setNumeroEdificio(rs.getInt("numeroEdificio"));                  
                    e.setNumeroAula(rs.getInt("cantidadAula")); 
                    e.setNombreEdificio(rs.getString("nombre"));
                  // materiaNombre.add(m);  
                }           
           }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar edificio " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
        return e;                        
    } 

    @FXML
    private void nuevoEdificio(ActionEvent event) {
          try {             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoEdificio.fxml"));//carga una gerarqui DE OBJETOS       
            Parent root = loader.load();//carga el parent              
            IngresoEdificio controlador = loader.getController();//carga el controlador de esa vista           
            //controlador.initAttributes(personas); 
            controlador.setControladorEscena1(this);                            
            Scene scene = new Scene(root);
            Stage stage = new Stage();                    
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);
            stage.showAndWait();                            
        }catch (IOException ex) {           
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();  
        }   
    }
       
}
