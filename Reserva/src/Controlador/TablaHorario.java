/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Aula;
import Modelo.Carrera;
import Modelo.Horario;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 *
 * @author Usuario
 */
public class TablaHorario implements Initializable {
    private final ObservableList<Horario> listaHorario = FXCollections.observableArrayList();
    private final ObservableList<String> listaDia = FXCollections.observableArrayList("lunes","martes","miercoles","jueves","viernes");
    

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
    private TableView<Horario> tblHorario;
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
    private TableColumn<Horario,String> colViernes;
    @FXML
    private Button bttNuevaReserva;
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {// alert verde.. ver si hay errores crear tabla siguiendo formato de tabla alumno para aprovechar formato
       configurarVentana();
       rellenarTablaHorario();
        //agregarHora(); SE CARGA LA HORA UNA VEZ
    
        /*try {                
            asociarHorario_Dia();
        } catch (SQLException ex) {
            Logger.getLogger(TablaHorario.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
        
        tblHorario.setItems(listaHorario);               
    }
    
    public void rellenarTablaHorario(){ //consulta en BD trae y setea elementos en la tabla     
        //aulas.clear();                   
       TablaHorario tabla= new TablaHorario();
        ObservableList<Horario> resultado=tabla.buscarTodos();  
         listaHorario.setAll(resultado);
       // tblAula.setItems(resultado);
        //int resultados=resultado.size();       
        //lblResultado.setText("resultado :" + resultados);                    
        //cuantos resultados hay en la lista                                                     
    }
    
    public ObservableList<Horario> buscarTodos(){
                     
        String query = "SELECT * FROM horario";
        TransaccionesBD trscns = new TransaccionesBD(); 
        ResultSet rs = trscns.realizarConsulta(query);
        Horario a =null;                 
        ObservableList<Horario>listaMetodoBuscar= FXCollections.observableArrayList();
       
            try{
                 while(rs.next()){  

                     a=new Horario();
                     Horario a1 = new Horario();
                     a.setHora(rs.getString("hora"));
                    // a.setCapacidad(rs.getInt("capacidad")); 
                     String suma= a.getHora();
                    int valor= Integer.parseInt(suma)+1;
                    a1.setHora(a.getHora()+":00"+" a "+ valor+":00");

                     listaMetodoBuscar.add(a1);                                      
                     }  
                     rs.close();
                 }catch(SQLException ex){
                     JOptionPane.showMessageDialog(null,"error al buscar horario" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
                 }
       return listaMetodoBuscar;
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
    
 
    public boolean insertarHoraDia(int a, int id){        
       // String query = "INSERT INTO dia(dia)" + "VALUES(' " + a + "' )";
       String query = " INSERT INTO `horario_dia` (`id_horario_dia`, `id_dia`, `id_horario`, `id_fecha`)\n" +
                                                "VALUES (NULL, "+a+", "+id+", 26);";
                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);    

                       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
     }
    
     public static int[] obtenerArrayDias() throws SQLException{ 
              
        String query ="SELECT id_dia FROM dia ORDER BY id_dia ASC";
        TransaccionesBD trscns = new TransaccionesBD(); 
        ResultSet rs = trscns.realizarConsulta(query);  
        int i=0;           
        int[] array = new int[5];
               
        while (rs.next()) {                  
             int id = rs.getInt("id_dia");
              array[i]=id;
              i++;               
          }
                   
        return array;
     }
    
   private void asociarHorario_Dia() throws SQLException {
       
       int[] array = new int[5];
       array=obtenerArrayDias();
       
       for(int i=0;i<array.length;i++){ // carga 14 hs por cada dia ( de 8 a 21 hs)
            int dia = array[i];
            String query ="SELECT id_horario FROM horario ORDER BY id_horario ASC";
            TransaccionesBD trscns = new TransaccionesBD(); 
            ResultSet rs = trscns.realizarConsulta(query);
                              
            while (rs.next()) {
                int id = rs.getInt("id_horario");              
                insertarHoraDia( dia, id);  
                 //System.out.println("ID: " + id);
          }        
       }  
    }
    
      @FXML
    private void buscarAlumno(ActionEvent event) {
    }

    @FXML
    private void refrescar(ActionEvent event) {
    }


    @FXML
    private void seleccionar(MouseEvent event) {
        Horario c = this.tblHorario.getSelectionModel().getSelectedItem();    
       
        if(c ==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);   
            
        }else{  
            
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/IngresoHorario.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoHorario controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.setControladorEscena1(this);
            //controlador.traerHorario(c); metod para cargar tabla desde base de datos 5/12/23 
            //controlador.seleCarrera();
            Scene scene = new Scene(root);
            Stage stage = new Stage(); 
            stage.setUserData(c);
            this.tblHorario.getSelectionModel().clearSelection();
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
    private void nuevaReserva(ActionEvent event) {
    }
}
