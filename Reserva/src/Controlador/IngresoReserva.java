/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Aula;
import Modelo.Edificio;
import Modelo.Horario;
import Modelo.Reserva;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class IngresoReserva implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private ComboBox<Integer> comboNumeroAula;
    @FXML
    private ComboBox<Horario> comboHora;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private ComboBox<String> comboDia;
    @FXML
    private ComboBox<String> comboNombreEdificio;
    @FXML
    private TextField txtCapacidadAula;
     
    TablaAsignar tablaAsignar = new TablaAsignar(); 
    ObservableList<String> listaEdificio = FXCollections.observableArrayList();     
    ObservableList<Integer> listaAula = FXCollections.observableArrayList();
    
    
    ArrayList<Horario> listaHora = new ArrayList<>();
    ArrayList<String> listaDia = new ArrayList<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listaAula =seleNumeroAula();
            comboNumeroAula.setItems(listaAula);        
      listaEdificio=seleEdificio();         
            comboNombreEdificio.setItems(listaEdificio);
      listaHora=seleHora();
            comboHora.getItems().addAll(listaHora); 
      listaDia=seleDia();
            comboDia.getItems().addAll(listaDia);
      
      
      
     // listaAula=seleNumeroAula();
      
  // listaAula= seleCapacidadAula();
       
    }    
    
     public void setControladorEscena1(TablaAsignar controladorEscena1) {
        this.tablaAsignar= controladorEscena1;
    }
    public void llamarMetodoDeOtraEscena() {
        if (tablaAsignar!= null) {
            JOptionPane.showMessageDialog(null,"Entra al metodo llamar metodoDeOtraScena","aviso" , JOptionPane.INFORMATION_MESSAGE);   
            //ActionEvent event = null;
            // Llamar al método del controlador de la primera escena
            //tablaHorario.refrescar(null); 
           //tablaCarrera.refresch();
            //tablaCarrera.rellenarTablaCarrera();   
            //tablaCarrera.refrescarTabla();          
        }
    }
    
        public ObservableList<String>seleEdificio(){
               
        ObservableList<String>lista  = FXCollections.observableArrayList();;        
        String query = "select nombre FROM edificio ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){ 
                    
                    String e="";                   
                    e =rs.getString("nombre");                  
                    lista.add(e);
                    System.out.println("lo que trajo la consulta :" +e);            
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleEdificio" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     
       return lista;             
    } 
        
    public ObservableList<Integer>seleNumeroAula(){
               
        ObservableList<Integer>lista  = FXCollections.observableArrayList();  
        String query = "select numeroAula FROM aula ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                    
                    int a=rs.getInt("numeroAula");
                                     
                    lista.add(a);
                    System.out.println("lo que trajo la consulta de numeroAula :"+ a);                    
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleEdificio" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
             
            
       return lista;             
    }
    
      public ObservableList<Integer>seleCapacidadAula(){
               
        ObservableList<Integer>lista  = FXCollections.observableArrayList();;        
        String query = "select capacidad FROM aula ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   
                    int a=rs.getInt("numeroAula");                                     
                    lista.add(a);
                    System.out.println("lo que trajo la consulta de numeroAula :"+ a);                  
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCapacidadAula" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     
       return lista;             
    }
      
       public ArrayList<Horario> seleHora(){
        
        ArrayList<Horario> lista  = new ArrayList<>();        
        String query = "select hora FROM horario where 1 ORDER BY hora ASC;";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                    Horario horario = new Horario();
                     horario.setHora(rs.getInt("hora")+"");
                    
                    lista.add(horario); 
                                       
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleHora" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
       // System.out.println("la carrera es " + carrera.getNombre_carrera());
       return lista;             
    }
       
        public ArrayList<String> seleDia(){
        
        ArrayList<String> lista  = new ArrayList<>();                
        String query = "SELECT 'Lunes' AS dia UNION SELECT 'Martes' UNION SELECT 'Miércoles' UNION SELECT 'Jueves' UNION SELECT 'Viernes';";
             
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                    
                     String dia= rs.getString("dia");                
                    lista.add(dia); 
                   // System.out.println("los dias son " + dia);                
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleHora" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
        //System.out.println("la carrera es ");
       return lista;             
    }
   
    @FXML
    private void guardarReserva(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"entro en metodo gguardar" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
          //if(comboNombreEdificio.getSelectionModel().getSelectedItem() !=null && comboNumeroAula.getSelectionModel().getSelectedItem()!=null && 
               /*   this.txtCapacidadAula.getText().isEmpty() &&
                 comboHora.getSelectionModel().getSelectedItem() !=null && comboDia.getSelectionModel().getSelectedItem() !=null && dateFecha.getValue()!=null  ){*/
         
              
              String nombreEdificio= this.comboNombreEdificio.getValue();
              int numAula = comboNumeroAula.getValue();
             int capAula = Integer.parseInt(this.txtCapacidadAula.getText());
              
           Horario hora = (Horario) comboHora.getValue();
              int horaEntera = Integer.parseInt(hora.getHora());
              String dia = (String) this.comboDia.getValue();
              LocalDate fecha = dateFecha.getValue();
              insertarFecha(fecha);
              int id=seleIDFecha(fecha);
              Reserva reserva= new Reserva(nombreEdificio,horaEntera ,dia,id);
              
             /* LocalDate fechaSeleccionada = dateFecha.getValue();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
               String fechaFormateada = fechaSeleccionada.format(formatter);*/
      
             // insertar(reserva);
                 
         
        // }else{
             JOptionPane.showMessageDialog(null,"Falta seleccionar" ,"aviso", JOptionPane.INFORMATION_MESSAGE);  
        // }  
        
       
    }
    
    public boolean insertarFecha(LocalDate fecha){
        boolean exito = false ;
         try{
              String sqlFecha = "INSERT INTO `fecha` (`id_fecha`, `fecha`) VALUES (NULL, '"+fecha+"');";                           
                TransaccionesBD trscns = new TransaccionesBD();
            exito = trscns.ejecutarQuery(sqlFecha ); 
                  
              }catch(Exception ex){
                  JOptionPane.showMessageDialog(null,"error en metodo de seleHora" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
              
              } 
       
             return exito;             
    }
    
   public int seleIDFecha(LocalDate fecha){
         int a = 0;      
        ObservableList<Integer>lista  = FXCollections.observableArrayList();;        
        String query = "select id_fecha FROM fecha where fecha ='"+fecha+"' ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   
                    a=rs.getInt("id_fecha");                                     
                    //lista.add(a);
                    System.out.println("lo que trajo la consulta de numeroAula :"+ a);                  
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleFecha" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     JOptionPane.showMessageDialog(null,"el id de la fecha es "+a ,"aviso", JOptionPane.INFORMATION_MESSAGE);
       return a;          
    }
    
    
    public boolean insertarReserva(Reserva r){
         boolean exito = false;
        try{
                                      
      String query = "INSERT INTO reserva (id_aula, id_horario_dia, id_fecha)\n" +
  "VALUES (\n" +
    "    (SELECT id_aula\n" +
      "     FROM aula\n" +
        "     WHERE edificio = '"+r.getNombreEdificio()+"' AND capacidad = '"+r.getCapacidadAula()+"'),\n" +
           "    (SELECT id_horario_dia\n" +
              "     FROM horario_dia\n" +
                 "     WHERE id_dia = (SELECT id_dia FROM dia WHERE dia = '"+r.getDia()+"')\n" +
                    "     AND id_horario = (SELECT id_horario FROM horario WHERE hora = '"+r.getHora()+"')),\n" +
                       "    '"+r.getId_fecha()+"'\n" +
                             ");";
        
         TransaccionesBD trscns = new TransaccionesBD();
                 exito = trscns.ejecutarQuery(query); 
                 }catch(Exception ex){
                  JOptionPane.showMessageDialog(null,"error en metodo de seleHora" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
              
              }
      
                

  return exito;
    }

    @FXML
    private void actionEvent(ActionEvent event) {
    }
    
    
    
}
