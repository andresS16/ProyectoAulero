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
import java.sql.Connection;
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
import java.sql.PreparedStatement;

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
    ObservableList<String> listaCarrera = FXCollections.observableArrayList();
    ObservableList<String> listaMateria = FXCollections.observableArrayList();
    
    
    ArrayList<Horario> listaHora = new ArrayList<>();
    ArrayList<String> listaDia = new ArrayList<>();
    @FXML
    private ComboBox<String> comboCarrera;
    @FXML
    private ComboBox<String> comboMateria;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JOptionPane.showMessageDialog(null,"Entra en ingreso Reserva " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        listaAula =seleNumeroAula();
            //comboNumeroAula.setItems(listaAula);        
         listaEdificio=seleEdificio();         
            comboNombreEdificio.setItems(listaEdificio);
          listaHora=seleHora();
            comboHora.getItems().addAll(listaHora); 
         listaDia=seleDia();
            comboDia.getItems().addAll(listaDia); 
         listaCarrera=seleCarrera();         
            comboCarrera.setItems(listaCarrera);
            // Dentro del método initialize o donde configuras tus componentes
          comboCarrera.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
          //ObservableList<String> materiaSeleccionada = seleMateria(newValue); // Llama a tu método seleMateria con el nuevo valor seleccionado
           listaMateria=seleMateria(newValue);                        
                        //comboMateria.setItems(materiaSeleccionada); // Configura el nuevo conjunto de materias en el ComboBox comboMateria                           
                        comboMateria.setItems(listaMateria);
                    }
      
           });     

    }   
    /*SetConrolDOReSCEB() , seleEdiificio() , selenumeroAula , seleCapacidadAula, seleHora, seleDia, , 
    seleCarrera(), seleMateria(), guardarReserva(), insertarFecha(), seleIdFecha(), numAula(), horarioID(), insertarReserva()*/
    
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
            
               JOptionPane.showMessageDialog(null,"Ingresa metodo seleEdificios", "ERROR", JOptionPane.INFORMATION_MESSAGE);  
               
        ObservableList<String>lista  = FXCollections.observableArrayList();;        
        String query = "select nombre FROM edificio ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){ 
                    
                    String e="";                   
                    e =rs.getString("nombre");                  
                    lista.add(e);
                    System.out.println("lo que trajo la consulta BD edificios :" +e);            
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleEdificio" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     
       return lista;             
    } 
        
    public ObservableList<Integer>seleNumeroAula(){
              JOptionPane.showMessageDialog(null,"Ingresa metodo seleNumeroAula - trae numeros de aulas ", "ERROR", JOptionPane.INFORMATION_MESSAGE); 
        ObservableList<Integer>lista  = FXCollections.observableArrayList();  
        String query = "select numeroAula FROM aula ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                    
                    int a=rs.getInt("numeroAula");
                                     
                    lista.add(a);
                    System.out.println("Trae numeros de aulas :"+ a);                    
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleEdificio" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
                        
       return lista;             
    }
    
      public ObservableList<Integer>seleCapacidadAula(){
                
        JOptionPane.showMessageDialog(null,"Ingresa metodo seleCapacidadAula trae capaciddad de aulas ", "ERROR", JOptionPane.INFORMATION_MESSAGE);    
        
        ObservableList<Integer>lista  = FXCollections.observableArrayList();        
        String query = "select capacidad FROM aula ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   
                    int a=rs.getInt("numeroAula");                                     
                    lista.add(a);
                    System.out.println("segun capacidad numeroAula :"+ a);                  
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCapacidadAula" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     
       return lista;             
    }
      
       public ArrayList<Horario> seleHora(){
        JOptionPane.showMessageDialog(null,"Ingresa metodo seleHora : ordena las horas de 8 a 21 hs ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(null,"Ingresa metodo seleDia: trae los dias de la semana", "ERROR", JOptionPane.ERROR_MESSAGE);
        
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
    
     public ObservableList<String>seleCarrera(){
               JOptionPane.showMessageDialog(null,"Ingresa metodo seleCarrera : trae las carreras ", "ERROR", JOptionPane.ERROR_MESSAGE);
        ObservableList<String>lista  = FXCollections.observableArrayList();;        
        String query = "select nombre FROM carrera ";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){ 
                    
                    String e="";                   
                    e =rs.getString("nombre");                  
                    lista.add(e);
                    System.out.println("Nombres de las carreras segun BD es :" +e);            
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCarrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     
       return lista;             
    }
     
      public ObservableList<String>seleMateria(String materia){
          
       
        JOptionPane.showMessageDialog(null,"Ingresa metodo seleMateria : trae materias de las carreras... valor del comboMateria es "+materia, "ERROR", JOptionPane.INFORMATION_MESSAGE);
        ObservableList<String>lista  = FXCollections.observableArrayList();;        
        String query = "select nombre FROM materia where carrera ='"+materia+"'";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){ 
                    
                    String e="";                   
                    e =rs.getString("nombre");                  
                    lista.add(e);
                    System.out.println("materias de la carrera BD materia:" +e);            
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleMateria " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
     
       return lista;             
    }
     
   
    @FXML
    private void guardarReserva(ActionEvent event) {
        
        JOptionPane.showMessageDialog(null,"entro en metodo GuardarReserva ..vericar los id para reserva" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
          
              String nombreEdificio= this.comboNombreEdificio.getValue();
              //nombreEdificio.trim();
              //int numAula = comboNumeroAula.getValue();
             int capAula = Integer.parseInt(this.txtCapacidadAula.getText());            
           Horario hora = (Horario) this.comboHora.getValue();
              int horaEntera = Integer.parseInt(hora.getHora());
              String dia = (String) this.comboDia.getValue();
              LocalDate fecha = dateFecha.getValue();
              insertarFecha(fecha);
              int id_fecha=seleIDFecha(fecha);
              
            
              
               int id_aula=numAula(nombreEdificio,capAula );
               int id_horario_dia= HorarioID(dia,horaEntera);
               
               System.out.println("id_aula :"+id_aula);  
               System.out.println("id_horario_dia :"+id_horario_dia);  
               System.out.println("id_fecha : "+id_fecha);  
               
              Reserva reserva= new Reserva();
              reserva.setId_aula(id_aula);
              reserva.setId_horario_dia(id_horario_dia);
              reserva.setId_fecha(id_fecha);
       
              insertarReserva(reserva);
       //insertarCarreraMateria(id_aula);
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
        JOptionPane.showMessageDialog(null,"Ingresa metodo numAula para buscar capacidad de aula ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
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
                    System.out.println("aula encontrada con id :"+ a);                  
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
                    System.out.println(" depurando  id_Horario_Dia :"+ a);                  
                }           
            }catch(SQLException ex){
                         JOptionPane.showMessageDialog(null,"error en metodo de Horario_ID" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
                      
       return a;          
    }
    
     
  /*public boolean insertarReserva(Reserva r){
        
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
    }*/





    
  /*  boolean exito = false;
    JOptionPane.showMessageDialog(null, "Entra en inertar Reserva ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            String query = "SELECT COUNT(*) AS countReservas\n" +
                            "FROM reserva r\n" +
                            "JOIN fecha f ON r.id_fecha = f.id_fecha\n" +
                            "WHERE  r.id_horario_dia = ?  \n" +
                            "  AND r.descripcion IS NULL\n" +
                            "  AND f.fecha = ?  \n" +
                            "  AND r.id_aula = ? ;";

    
    TransaccionesBD trscns = new TransaccionesBD();
LocalDate fecha = this.dateFecha.getValue();
    try {
        int count = trscns.contarDuplicadosNumero(query, r.getId_horario_dia(), fecha, r.getId_aula() );

        if (count > 0) {
            JOptionPane.showMessageDialog(null, "Sí existe registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No existe registro SE INSERTA", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            String queryInsercion  ="INSERT INTO `reserva` (`id_horario_dia`, `id_fecha`, `id_aula`) VALUES ('"+r.getId_aula()+"', '"+r.getId_horario_dia()+"', '"+r.getId_fecha()+"')";
            exito = trscns.ejecutarQuery(queryInsercion);
            System.out.println("Método insertar reserva");
            return false;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;*/
   public boolean insertarReserva(Reserva r) {
    TransaccionesBD trscns = new TransaccionesBD();
    LocalDate fecha = this.dateFecha.getValue();
    int id_aula=r.getId_aula();
    int horaDia= 0;
         horaDia = r.getId_horario_dia();
JOptionPane.showMessageDialog(null, "Entra ametodo insertar"+"id aula "+id_aula+"  id_h_Dia "+horaDia +" fecha "+ fecha, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    try {
        String queryDuplicados = "SELECT COUNT(*) AS countReservas\n" +
                            "FROM reserva r\n" +
                            "JOIN fecha f ON r.id_fecha = f.id_fecha\n" +
                            "WHERE r.id_horario_dia = ?\n" +
                            "  AND r.descripcion IS NULL\n" +
                            "  AND f.fecha = ?  \n" +
                            "  AND r.id_aula = ? ;";

       
       int count =  trscns.contarDuplicadosNumero(queryDuplicados,horaDia,fecha, id_aula);

        if (count > 0) { 
            JOptionPane.showMessageDialog(null, "Ya existe un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No existe registro, SE INSERTA", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            String queryInsercion = "INSERT INTO reserva (id_horario_dia, id_fecha, id_aula) VALUES (?, ?, ?)";
            //boolean exito = trscns.ejecutarQuery(queryInsercion, r.getId_horario_dia(), fecha, r.getId_aula());

            //return exito;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al verificar duplicados o al insertar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    return false;
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
       JOptionPane.showMessageDialog(null,"busca el aula selecciona da por su capacidda id :" +a  , "ERROR", JOptionPane.INFORMATION_MESSAGE);
       
        this.comboNumeroAula.getItems().add(a);               
        this.comboNumeroAula.setValue(a);
                           
       return a;          
    }
     
      public void insertarCarreraMateria(int id ){
             
             JOptionPane.showMessageDialog(null,"Ingresa metodo insertar carreraMateria", "ERROR", JOptionPane.INFORMATION_MESSAGE);  
             
        ObservableList<Integer>lista  = FXCollections.observableArrayList(); 
        lista=buscarIdCarreraMateria();
        int id_materia =0;
        id_materia=lista.get(1);
        int id_Carrera =0;
        id_Carrera=lista.get(0);
        boolean exito=false;
        
         try{
              String sqlFecha = "INSERT INTO carrera_materia (id_carrera, id_materia, id_aula)\n" +
                                    "VALUES ('"+id_Carrera+"', '"+id_materia+"', '"+id+"')";                           
              TransaccionesBD trscns = new TransaccionesBD();
              exito = trscns.ejecutarQuery(sqlFecha ); 
                 System.out.println("metodo insertaFecha :");  
              }catch(Exception ex){
                  JOptionPane.showMessageDialog(null,"error en metodo de seleHora" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
              
              }                     
    }
         
    public ObservableList<Integer>buscarIdCarreraMateria(){
                
        JOptionPane.showMessageDialog(null,"Ingresa metodo seleCapacidadAula. busca id_carrera id_materia ", "ERROR", JOptionPane.INFORMATION_MESSAGE);    
        
        ObservableList<Integer>lista  = FXCollections.observableArrayList();        
        String query = "SELECT id_materia, id_carrera FROM materia\n" +
                                "JOIN carrera ON materia.nombre = '"+this.comboMateria.getValue()+"' AND carrera.nombre = '"+this.comboCarrera.getValue()+"'";   

        
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            int a=0;
            int b=0;
            try{
                while(rs.next()){
                   
                    a=rs.getInt("id_carrera"); 
                    b=rs.getInt("id_materia"); 
                    lista.add(a);
                    lista.add(b);
                    System.out.println(" el id de  carrera es  :"+ a);    
                    System.out.println(" el id de  materia es  :"+ b); 
                }           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCapacidadAula" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
      JOptionPane.showMessageDialog(null,"Los id de carrera y materia : " +a +" "+b +"" , "ERROR", JOptionPane.INFORMATION_MESSAGE);
       return lista;            
    }

    @FXML
    private void actionEvent(ActionEvent event) {
    }
    
    
    
}



