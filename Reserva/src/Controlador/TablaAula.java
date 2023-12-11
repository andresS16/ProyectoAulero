/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Aula;
import java.sql.ResultSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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



public class TablaAula implements Initializable {

    private ObservableList<Aula> aulas = FXCollections.observableArrayList();  

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
    private TableView<Aula> tblAula;
    
    @FXML
    private TableColumn<Aula, Integer> colNumAula;
    @FXML
    private TableColumn<Aula,Integer> colCapacidad;
    @FXML
    private TableColumn<Aula,String> colEdificio;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //chcBuscar.getItems().addAll("capacidad","numeroAula");
        configurarVentana();
        rellenarTablaAula();
        //Aula a = this.tblAula.getSelectionModel().getSelectedItem(); 24/11 anule porque no vi que se usaba.
    }   
     public void configurarVentana(){       
        colNumAula.setCellValueFactory(new PropertyValueFactory<>("numAula"));//cada col. se asigna setvalufactory    
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));    
        colEdificio.setCellValueFactory(new PropertyValueFactory<>("edificio"));
        tblAula.setItems(aulas);                
    }
     
      
     public void rellenarTablaAula(){        
        //aulas.clear();                   
        TablaAula tabla= new TablaAula();
        ObservableList<Aula> resultado=tabla.buscarTodos();  
         aulas.setAll(resultado);
       // tblAula.setItems(resultado);
        //int resultados=resultado.size();       
        //lblResultado.setText("resultado :" + resultados);                    
        //cuantos resultados hay en la lista                                                     
    }
     
    public ObservableList<Aula> buscarTodos(){
                         
        String query = "SELECT * FROM aula";
        TransaccionesBD trscns = new TransaccionesBD(); 
        ResultSet rs = trscns.realizarConsulta(query);
        Aula a =null;                 
        ObservableList<Aula>aulas = FXCollections.observableArrayList();
       
       try{
            while(rs.next()){  
                
                a=new Aula();
                a.setNumAula(rs.getInt("numeroAula"));
                a.setCapacidad(rs.getInt("capacidad")); 
                a.setEdificio(rs.getString("edificio"));
                aulas.add(a);                                      
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar aula" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return aulas;
       }
     
    @FXML
    private void buscarAlumno(ActionEvent event) {
               
        
    }

    @FXML
    private void refrescar(ActionEvent event) {
          
        rellenarTablaAula();
        
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        }              
    }
    
    public void refresch() {
           
        rellenarTablaAula();
        
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        }         
    }
    
     @FXML
    private void nuevoAula(ActionEvent event) {
         try {             
            FXMLLoader loader;//carga una gerarqui DE OBJETOS       
             loader = new FXMLLoader(getClass().getResource("/Vista/IngresoAula.fxml"));
            Parent root = loader.load();//carga el parent             
            ///IngresoAula controlador = loader.getController();//carga el controlador de esa vista           
            //controlador.initAttributes(personas);            
            Scene scene = new Scene(root);
            Stage stage = new Stage();                    
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);
            stage.showAndWait();
                                 
        } catch (IOException ex) {
            
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setTitle("Error");
             alert.setContentText(ex.getMessage());
             alert.showAndWait();  
        }             
    }

    @FXML
    private void seleccionar(MouseEvent event) {
      
        Aula a = this.tblAula.getSelectionModel().getSelectedItem();
        // JOptionPane.showMessageDialog(null, "getSelectionModel -->>>  "+ a.getNumAula(), "Error",JOptionPane.WARNING_MESSAGE);        
        if(a ==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);   
            
        }else{              
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/IngresoAula.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoAula controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.setControladorEscena1(this);
             controlador.traerAula(a);
            //TablaAula ta = new TablaAula();
            //ta.refrescar(event);           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setUserData(a);
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
    
  
    public Aula buscarAulaNumero(int numBusqueda){
         
        String query ="SELECT * FROM aula WHERE numeroAula = " + numBusqueda;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);           
        Aula a =null;
          
            try{
                if(rs.next()){
                    //idBusqueda=rs.getLong("id");
                    a=new Aula();
                    a.setNumAula(rs.getInt("numeroAula"));
                    a.setCapacidad(rs.getInt("capacidad"));
                    a.setEdificio(rs.getString("edificio"));

                    }           
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"error al buscar numero de aula " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                     //JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error"+ a.getNumAula(),JOptionPane.WARNING_MESSAGE);       
                     return a;                                  
     }
   
    public Aula buscarAulaID(int id){
         
        String query ="SELECT * FROM aula WHERE id_aula= " + id;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);           
        Aula a =null;
          
        try{
            if(rs.next()){
                //idBusqueda=rs.getLong("id");
                a=new Aula();
                a.setId(rs.getInt("id"));
                a.setNumAula(rs.getInt("numeroAula"));
                a.setCapacidad(rs.getInt("capacidad"));
                a.setEdificio(rs.getString("edificio"));
                              
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar numero de aula " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    // JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return a;                                  
    }
    
    public Aula llevar(){
       Aula a = this.tblAula.getSelectionModel().getSelectedItem();               
        return a;
    } 
      
}
