/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Horario;
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
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TablaAsignar implements Initializable {

    private final ObservableList<Horario> listaHorario = FXCollections.observableArrayList();
    private final ObservableList<String> listaDia = FXCollections.observableArrayList("lunes","martes","miercoles","jueves","viernes");
    @FXML
    private TextField textBuscar;
    @FXML
  /* private ChoiceBox<?> chcBuscar;
    @FXML*/
    private Button btnBuscar;
    @FXML
    private TextField lblResultado;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TableView<Horario> tblAsignarAula;
    @FXML
    private TableColumn<Horario, String> colHora;
    @FXML
    private TableColumn<Horario, String> colLunes;
    @FXML
    private TableColumn<Horario, String> colMartes;
    @FXML
    private TableColumn<Horario, String> colMiercoles;
    @FXML
    private TableColumn<Horario, String> colJueves;
    @FXML
    private TableColumn<Horario, String> colViernes;
    @FXML
    private Button btnuevaAsignacion;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JOptionPane.showMessageDialog(null,"Ingresa Tabla Asignar " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        configurarVentana();
        rellenarTablaHorario();
     
    }    
    
    public void agregarHora(){// metodo solo se usa para cargar una sola vez las horas en BD, Sino se cargarian vrias veces el mismo dato con distinto id
      
        for(int h=8 ; h < 22; h++){              
           //lista.addAll(new Horario("  9:00", "matematicas ", "","","","" ));           
            //String bandera= h +":00"+" a " + a +":00" ; 
            String bandera= h+"";          
            insertarHorario(bandera);
            listaHorario.addAll(new Horario(bandera));
        }   
    }
     
    public void agregarDia(){//este metodo se debe usar solo una vez 
         
        for(String dia: listaDia){
             insertarDia(dia);         
         }
     }
      
    public void configurarVentana(){ //se enlaza tabla con atributos de la clase Horario
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colLunes.setCellValueFactory(new PropertyValueFactory<>("lunes"));
        colMartes.setCellValueFactory(new PropertyValueFactory<>("martes"));
        colMiercoles.setCellValueFactory(new PropertyValueFactory<>("miercoles"));     
        colJueves.setCellValueFactory(new PropertyValueFactory<>("jueves")); 
        colViernes.setCellValueFactory(new PropertyValueFactory<>("viernes")); 
        //fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        tblAsignarAula.setItems(listaHorario);               
    }
    
    
    public boolean insertarHorario(String a){        
        String query = "INSERT INTO horario(hora)" + "VALUES(' " + a + "' )";
       // String query ="INSERT INTO `fecha`(`fecha`) VALUES ('"+a+"')";

                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);    

                       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
    
    public boolean insertarDia(String a){        
        String query = "INSERT INTO dia(dia)" + "VALUES(' " + a + "' )";

            TransaccionesBD trscns = new TransaccionesBD();
             boolean exito = trscns.ejecutarQuery(query);    
            // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
      return exito;
    }
    
    public void rellenarTablaHorario(){ //consulta en BD trae y setea elementos en la tabla     
        //aulas.clear();                   
        TablaHorario_ReservaExterna  tabla= new TablaHorario_ReservaExterna ();
        ObservableList<Horario> resultado=tabla.buscarTodos();  
         listaHorario.setAll(resultado);
       // tblAula.setItems(resultado);
        //int resultados=resultado.size();       
        //lblResultado.setText("resultado :" + resultados);                    
        //cuantos resultados hay en la lista                                                     
    }
    
    @FXML
    private void seleccionar(MouseEvent event) {
        Horario c = this.tblAsignarAula.getSelectionModel().getSelectedItem();    
       
        if(c ==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);   
            
        }else{  
            
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/IngresoReserva.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoReserva controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
             controlador.setControladorEscena1(this);
            //controlador.traerHorario(c); metod para cargar tabla desde base de datos 5/12/23 
            //controlador.seleCarrera();
            Scene scene = new Scene(root);
            Stage stage = new Stage(); 
            stage.setUserData(c);
            this.tblAsignarAula.getSelectionModel().clearSelection();
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
      @FXML
    private void buscarAlumno(ActionEvent event) {
    }

    @FXML
    private void refrescar(ActionEvent event) {
          rellenarTablaHorario();
        
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        }  
    }


    
    
    @FXML
    private void nuevaAsignacion(ActionEvent event) {
    }
    

   
    
}
