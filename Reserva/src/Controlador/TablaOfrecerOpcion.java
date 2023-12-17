/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Horario;
import Modelo.ModeloDeDatos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    }
    
}
