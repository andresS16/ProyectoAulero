/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Horario;
import Modelo.ModeloDeDatos;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TablaOfrecerOpcion implements Initializable {
    private final ObservableList<ModeloDeDatos> listaOpciones = FXCollections.observableArrayList();

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
    private TableView<ModeloDeDatos> tblOpciones;
    @FXML
    private TableColumn<ModeloDeDatos,String> colEdificio;
    @FXML
    private TableColumn<ModeloDeDatos, Integer> colNumeroAula;
    @FXML
    private TableColumn<ModeloDeDatos, Integer> colCapacidadAula;
    @FXML
    private TableColumn<ModeloDeDatos, Integer> colHora;
    @FXML
    private TableColumn<ModeloDeDatos, String> colDia;
    @FXML
    private TableColumn<ModeloDeDatos,String> colFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }    
        
     public void configurarVentana(ObservableList<ModeloDeDatos> datos){ //se enlaza tabla con atributos de la clase Horario
        
        colEdificio.setCellValueFactory(new PropertyValueFactory<>("edificio"));
        colNumeroAula.setCellValueFactory(new PropertyValueFactory<>("numeroAula"));
        colCapacidadAula.setCellValueFactory(new PropertyValueFactory<>("capacidad"));  
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
       colDia.setCellValueFactory(new PropertyValueFactory<>("dia")); 
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha")); 
        //fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        tblOpciones.setItems(datos);               
    }

    @FXML
    private void buscarAlumno(ActionEvent event) {
    }

    @FXML
    private void refrescar(ActionEvent event) {
    }

    @FXML
    private void nuevoAula(ActionEvent event) {
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        
         ModeloDeDatos modelo = this.tblOpciones.getSelectionModel().getSelectedItem();    
       
        if(modelo ==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);   
            
        }else{  
            
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/IngresoReserva.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoReserva controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
        //controlador.setControladorEscena1(this);
            controlador.traerOpcion(modelo); //metod para cargar tabla desde base de datos 5/12/23 
            //controlador.seleCarrera();
            Scene scene = new Scene(root);
            Stage stageB = new Stage(); 
            stageB.setUserData(modelo);
           controlador.setStageA((Stage) tblOpciones.getScene().getWindow());
           
            this.tblOpciones.getSelectionModel().clearSelection();
            stageB.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stageB.setScene(scene);            
           
                stageB.showAndWait();  
            Stage stageA = (Stage) tblOpciones.getScene().getWindow(); // Obtener la referencia al Stage de la escena A
            stageA.close(); // Cerrar la escena A
            
          }catch (IOException ex) {             
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();  
          }   
        
        }
           
    }
    
}
