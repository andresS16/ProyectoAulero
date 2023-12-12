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
            //comboNumeroAula.setItems(listaAula);        
      listaEdificio=seleEdificio();         
            comboNombreEdificio.setItems(listaEdificio);
      listaHora=seleHora();
            comboHora.getItems().addAll(listaHora); 
      listaDia=seleDia();
            comboDia.getItems().addAll(listaDia);
      
       
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
                JOptionPane.showMessageDialog(null,"Ingresa metodo seleCapacidadAula", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                
                
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
        JOptionPane.showMessageDialog(null,"Ingresa metodo seleHora", "ERROR", JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(null,"Ingresa metodo seleDia", "ERROR", JOptionPane.ERROR_MESSAGE);
        
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
        
        JOptionPane.showMessageDialog(null,"entro en metodo GuardarReserva" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
          
              String nombreEdificio= this.comboNombreEdificio.getValue();
              //nombreEdificio.trim();
              //int numAula = comboNumeroAula.getValue();
             int capAula = Integer.parseInt(this.txtCapacidadAula.getText());
              
           Horario hora = (Horario) comboHora.getValue();
              int horaEntera = Integer.parseInt(hora.getHora());
              String dia = (String) this.comboDia.getValue();
              LocalDate fecha = dateFecha.getValue();
              insertarFecha(fecha);
              int id_fecha=seleIDFecha(fecha);
               int id_aula=numAula(nombreEdificio,capAula );
               int id_horario_dia=HorarioID(dia,horaEntera);
               
               System.out.println("id_aula :"+id_aula);  
               System.out.println("id_horario_dia :"+id_horario_dia);  
               System.out.println("id_fecha : "+id_fecha);  
               
              Reserva reserva= new Reserva(id_aula,id_horario_dia,id_fecha); 
       
              insertarReserva(reserva);
       
    }
    
    public boolean insertarFecha(LocalDate fecha){
        boolean exito = false ;
         try{
              String sqlFecha = "INSERT INTO `fecha` (`id_fecha`, `fecha`) VALUES (NULL, '"+fecha+"');";                           
              TransaccionesBD trscns = new TransaccionesBD();
              exito = trscns.ejecutarQuery(sqlFecha ); 
                 System.out.println("metodo insertaFecha :");  
              }catch(Exception ex){
                  JOptionPane.showMessageDialog(null,"error en metodo de seleHora" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
              
              } 
       
             return exito;             
    }
    
   public int seleIDFecha(LocalDate fecha){
       
        int a = 0;                  
        String query = "select id_fecha FROM fecha where fecha ='"+fecha+"' ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   
                    a=rs.getInt("id_fecha");                                     
                    //lista.add(a);
                    System.out.println("id de fechas traidas de BD :"+ a);                  
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleFecha" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     JOptionPane.showMessageDialog(null,"el id de la fecha es "+a ,"aviso", JOptionPane.INFORMATION_MESSAGE);
       return a;          
    }
   
   
    public int numAula(String edificio, int capacidad){
        JOptionPane.showMessageDialog(null,"Ingresa metodo numAula en funcion de capacidad ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        int a = 0;                   
        String query = "SELECT id_aula\n" +
                              "FROM aula\n" +
                        "WHERE edificio = '"+edificio+"' AND capacidad >= '"+capacidad+"'\n" +
                        "ORDER BY capacidad ASC\n" +
                      "LIMIT 1;";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   
                    a=rs.getInt("id_aula");                                     
                    //lista.add(a);
                    System.out.println("Metodo numAula---> id_aula :"+ a);                  
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleFecha" + ex , "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }   
                  
                       // JOptionPane.showMessageDialog(null,"el id_aula "+a ,"aviso", JOptionPane.INFORMATION_MESSAGE);
                        seleAulaCapacidad(a);
       return a;          
    }
    
    public int HorarioID(String dia, int hora){
         int a = 0;      
              
        String query = "SELECT horario_dia.id_horario_dia\n" +
                            "FROM horario_dia\n" +
                               "JOIN dia ON horario_dia.id_dia = dia.id_dia\n" +
                                   "JOIN horario ON horario_dia.id_horario = horario.id_horario\n" +
                                           "WHERE dia.dia = '"+dia+"' AND hora = '"+hora+"';";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   
                    a=rs.getInt("id_horario_dia");                                     
                    //lista.add(a);
                    System.out.println("Metodo id_Horario_Dia :"+ a);                  
                }           
            }catch(SQLException ex){
                         JOptionPane.showMessageDialog(null,"error en metodo de Horario_ID" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
                      
       return a;          
    }
    
     
    public boolean insertarReserva(Reserva r){
        
        boolean exito = false;
        try{                                     
               // String query = "INSERT INTO reserva (id_aula, id_horario_dia, id_fecha)\n" + "VALUES ("+r.getId_aula()+", "+r.getId_horario_dia()+", "+r.getId_fecha()+")";
                
             String query ="INSERT INTO `reserva` (`id_horario_dia`, `id_fecha`, `id_aula`) VALUES ('"+r.getId_aula()+"', '"+r.getId_horario_dia()+"', '"+r.getId_fecha()+"')";

                TransaccionesBD trscns = new TransaccionesBD();
                exito = trscns.ejecutarQuery(query); 
                System.out.println("Metodo insertar reserva");  
       }catch(Exception ex){
               JOptionPane.showMessageDialog(null,"error en metodo de seleHora" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);             
              }                
  return exito;
    }
    
     public int seleAulaCapacidad(int id){
     
        int a = 0;                  
        String query = "select numeroAula FROM aula where id_aula ='"+id+"' ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   
                    a=rs.getInt("numeroAula");                                     
                    //lista.add(a);
                    System.out.println("id de fechas traidas de BD :"+ a);                  
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleFecha" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
       JOptionPane.showMessageDialog(null,"entra en metodo seleAulaCapacidad para obtener numero de aula segun cntidad ingresada " +a  , "ERROR", JOptionPane.INFORMATION_MESSAGE);
       
        this.comboNumeroAula.getItems().add(a);               
        this.comboNumeroAula.setValue(a);
             
               
               
       return a;          
    }

    @FXML
    private void actionEvent(ActionEvent event) {
    }
    
    
    
}
