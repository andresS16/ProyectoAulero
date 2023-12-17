/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ControladorPrincipal implements Initializable {

    @FXML
    private BorderPane panel;
    @FXML
    private MenuButton btnEditarDatos;
    @FXML
    private MenuItem miAula;
    @FXML
    private MenuItem miEdificio;
    @FXML
    private MenuItem miCarrera;
    //private MenuItem miReserva;
    @FXML
    private MenuButton btnReserva;
    @FXML
    private MenuItem reservaExterna;
    @FXML
    private MenuItem reservaAula;
    @FXML
    private MenuItem btnAsignarAula;
                                  

    public ControladorPrincipal() {
        // Inicialización del BorderPane
        panel = new BorderPane();
    }

    public BorderPane getBorderPane() {
        return panel;
    }
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evento(ActionEvent event) {
       
             Object evento = event.getSource();// metodo p/ saber en que nodo se aplico el evento , donde esta posicionado
        
        if(evento.equals(miAula)){//se aplica condicional para saber que boton se acciono                           
            try{
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(ControladorPrincipal.class.getResource("/Vista/TablaAula.fxml"));//FormularioLU.fxml
            
                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                //TablaProfesor tblc = loader.getController();                                                             
               //principal.setScene(scene);
                   
                } catch(IOException e){
                    System.out.println("tratar error"+ e.getMessage());        
                }            
        }else if(evento.equals(miEdificio)){   
            
            try{
                  
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(ControladorPrincipal.class.getResource("/Vista/TablaEdificio.fxml"));//FormularioLU.fxml

                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                //TablaProfesorVistaController tblc = loader.getController();
                //TablaAlumno tblc = loader.getController();
                                                                  
            } catch(IOException e){
                 System.out.println("tratar error"+ e.getMessage());            
                  }
                                          
          }else if(evento.equals(miCarrera)){                                            
            try{
                  
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(ControladorPrincipal.class.getResource("/Vista/TablaCarrera.fxml"));//FormularioLU.fxml

                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                //TablaProfesorVistaController tblc = loader.getController();
                //TablaCarrera tblc = loader.getController();
                                                                  
             }catch(IOException e){
                    System.out.println("tratar error"+ e.getMessage());           
               }                                          
          }  
     
    }


    @FXML
    private void eventoReserva(ActionEvent event) {
          Object evento = event.getSource();
        if(evento.equals(reservaExterna)){
              
              try{
                  
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(ControladorPrincipal.class.getResource("/Vista/TablaHorario_ReservaExterna.fxml"));//FormularioLU.fxml

                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                //TablaProfesorVistaController tblc = loader.getController();
                //TablaAlumno tblc = loader.getController();
                                                                  
            } catch(IOException e){
                 System.out.println("tratar error"+ e.getMessage());            
                  }
         
          }
       
    }

    @FXML
    private void EventoAsignarAula(ActionEvent event) {
         Object evento = event.getSource();
        if(evento.equals(btnAsignarAula)){
              try{
                  
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(ControladorPrincipal.class.getResource("/Vista/TablaAsignarAula.fxml"));//FormularioLU.fxml

                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                //TablaProfesorVistaController tblc = loader.getController();
                //TablaAlumno tblc = loader.getController();
                                                                  
            } catch(IOException e){
                 System.out.println("tratar error"+ e.getMessage());            
               }      
          }      
    }
    
  public void ofrecerOpcion(){
      
              try {
     FXMLLoader loader= new FXMLLoader();
                loader.setLocation(ControladorPrincipal.class.getResource("/Vista/TablaOfrecerOpcion.fxml"));//FormularioLU.fxml

                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
  
    // Obtener el controlador si es necesario
    ///TablaOfrecerOpcion  controlador = loader.getController();
    // Realizar operaciones con el controlador si es necesario
    
        } catch (IOException e) {
            System.out.println("Error al cargar la interfaz: " + e.getMessage());
            // Manejar la excepción de manera más detallada si es necesario
        }
  
    }
}
