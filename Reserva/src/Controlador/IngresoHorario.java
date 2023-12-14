/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.util.ArrayList;
import java.util.ResourceBundle;
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


public class IngresoHorario implements Initializable {
    TablaHorario_ReservaExterna  tablaHorario = new TablaHorario_ReservaExterna ();
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private ComboBox<Aula> comboNumeroAula;
    @FXML
    private ComboBox<Horario> comboHora;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private ComboBox<Edificio> comboNumeroEdificio;
    @FXML
    private ComboBox<String> comboDia;
    
     ArrayList<Edificio> listaEdificio  = new ArrayList<>();
     ArrayList<Aula> listaAula = new ArrayList<>();
     
     ArrayList<Horario> listaHora = new ArrayList<>();
     ArrayList<String> listaDia = new ArrayList<>();
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           JOptionPane.showMessageDialog(null,"Controlador Ingreso Horario desde Tabla horarioReservaExterna","aviso" , JOptionPane.INFORMATION_MESSAGE);  
        listaEdificio=seleEdificio();
             comboNumeroEdificio.getItems().addAll(listaEdificio);  
        listaAula=seleAula();
             comboNumeroAula.getItems().addAll(listaAula);
        listaHora=seleHora();
            comboHora.getItems().addAll(listaHora);      
        listaDia=seleDia();
            comboDia.getItems().addAll(listaDia);
    }
    
    
    public void setControladorEscena1(TablaHorario_ReservaExterna  controladorEscena1) {
        this.tablaHorario = controladorEscena1;
    }
    public void llamarMetodoDeOtraEscena() {
        if (tablaHorario!= null) {
            JOptionPane.showMessageDialog(null,"Entra al metodo llamar metodoDeOtraScena","aviso" , JOptionPane.INFORMATION_MESSAGE);   
            //ActionEvent event = null;
            // Llamar al método del controlador de la primera escena
            //tablaHorario.refrescar(null); 
           //tablaCarrera.refresch();
            //tablaCarrera.rellenarTablaCarrera();   
            //tablaCarrera.refrescarTabla();           
        }   
    }

    @FXML
    private void guardarReserva(ActionEvent event) {
        
         if(comboNumeroEdificio.getSelectionModel().getSelectedItem() !=null && comboNumeroAula.getSelectionModel().getSelectedItem()!=null && 
                 comboHora.getSelectionModel().getSelectedItem() !=null && comboDia.getSelectionModel().getSelectedItem() !=null && dateFecha.getValue()!=null  ){
         
              
              Edificio numeroEdificio= comboNumeroEdificio.getValue() ;
              int numED = numeroEdificio.getNumeroEdificio();
              Aula aula = comboNumeroAula.getValue();
              int numAula = aula.getNumAula();
              
              Horario hora = comboHora.getValue();
              int horaEntera = Integer.parseInt(hora.getHora());
              String dia = comboDia.getValue();
              LocalDate fecha = dateFecha.getValue();
              
              
              Reserva reserva = new Reserva(numED,numAula,horaEntera,dia,fecha);
              insertar(reserva);
                   System.out.println("la carrera es " + numeroEdificio.getNumeroEdificio());  
         
         }else{
             JOptionPane.showMessageDialog(null,"Falta seleccionar" ,"aviso", JOptionPane.INFORMATION_MESSAGE);  
         }            
    }
    
    public boolean insertar(Reserva r){
         
        /* String query= INSERT INTO `reserva` (`id_reserva`, `id_aula`, `id_fecha`, `id_horario_dia`, `fecha_reserva`, `dia`, `numeroEdificio`)
         VALUES (NULL, '10', '26', '2', '2023-10-16', 'lunes', '100');*/
                          
        String query = "INSERT INTO reserva (`id_aula`, `id_fecha`, `id_horario_dia`, `fecha_reserva`, `dia`, `numeroEdificio`)" 
                + "VALUES(' "                     
                + 10//r.getNumeroAula()
                + " ',' " 
                + 26 
                + " ',' " 
                + r.getHora()
                + " ',' " 
                + r.getFecha()
                +"','"
                + r.getDia()
                + " ',' " 
                + r.getNumeroEdificio()
                 
                + "' )";
          
        
 /*INSERT INTO `reserva` (`id_reserva`, `id_aula`, `id_fecha`, `id_horario_dia`, `fecha_reserva`, `dia`, `numeroEdificio`)
 VALUES (NULL, '10', '26', '1', '2023-10-16', 'lunes', '100');  */     
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        
        JOptionPane.showMessageDialog(null,"entro en metodo insertar " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }

   
    
    public ArrayList<Edificio> seleEdificio(){
               
        ArrayList<Edificio> lista  = new ArrayList<>();        
        String query = "select id_edificio,numeroEdificio FROM edificio where 1 ORDER BY numeroEdificio ASC;";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                    Edificio edificio = new Edificio();
                    edificio.setNumeroEdificio(rs.getInt("numeroEdificio"));
                    lista.add(edificio);                     
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleEdificio" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     
       return lista;             
    }
     
    public ArrayList<Aula> seleAula(){
               
        ArrayList<Aula> lista  = new ArrayList<>();        
        String query = "select id_aula, numeroAula FROM aula where 1 ORDER BY numeroAula ASC;";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                       Aula aula = new Aula();
                       aula.setNumAula(rs.getInt("numeroAula"));
                    lista.add(aula);                     
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleAula" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
       // System.out.println("la carrera es " + carrera.getNombre_carrera());
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
    private void actionEvent(ActionEvent event) {
    }
         
}
