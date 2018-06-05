package org.uma.bdbio2018.gui;
import org.uma.bdbio2018.benchmark.DBConnectionFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;
import org.uma.bdbio2018.benchmark.DBBenchmark;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import java.awt.event.*;
import java.sql.SQLException;



/**
 * @author Elena Espinosa Garcia
 */

public class Window extends JFrame {

    //Definicion de botones

    JButton exist;//exist button
    JButton delete;// delete button
    JButton send;// send button

    TextArea textarea1;//request
    TextArea textarea2;//result

    JCheckBox checkbox; //optimize database

    JComboBox combobox1; // check database manager
    //JComboBox<String> combobox2; //check database language

    GridBagConstraints constraints; // elements position

    //window accessories

    Font font;

    //Constructor

    public Window() {

        ButtonConfiguration();
        WindowsConfiguration();
        EventsConfiguration();
    }

    public void WindowsConfiguration() {

        this.setSize(800, 500);
        this.setTitle("Ingenieria del software avanzada");
        this.setVisible(true);
        this.setForeground(Color.blue);
        font = new Font("Arial", Font.PLAIN, 20);
    }

    public void InitializeComponents() {

        this.getContentPane().setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        exit = new JButton("Exit");

        delete = new JButton("Delete");
        send = new JButton("Send");

        textarea1 = new TextArea("Write here your request");
        textarea2 = new TextArea();

        checkbox = new JCheckBox("optimize");

        combobox1 = new JComboBox();
        combobox1.addItem("Check database manager");

        combobox1.addItem("mariadb");
        combobox1.addItem("Mysql");
        combobox1.addItem("postgresql");
        combobox1.addItem("sqlite");
        combobox1.addItem("Existdb");

        //combobox2 = new JComboBox();
        //combobox1.addItem("Check database xml manager");
        //combobox2.addItem("sql");
        
    }

    public void ButtonConfiguration() {

        InitializeComponents();
        PositionComponents();
    }

    public void EventsConfiguration() {

        send.addActionListener(new Eventos());
        //send.setActionCommand("send");
        combobox1.addActionListener(new Eventos());
        exit.addActionListener(new Eventos());
        delete.addActionListener(new Eventos());
        //delete.setActionCommand("delete");

    }

    public void PositionComponents() {

        constraints.gridx = 3;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(delete, constraints);

        constraints.gridx = 4;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(send, constraints);


        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridheight = 2;
        constraints.gridwidth = 2;
        this.getContentPane().add(textarea1, constraints);


        constraints.gridx = 4;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(combobox1, constraints);
        
        /*
        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(combobox2, constraints);
        
         */
        
        constraints.gridx = 4;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(checkbox, constraints);


        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(exist, constraints);


        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        constraints.gridwidth = 2;
        this.getContentPane().add(textarea2, constraints);

    }

    private class Eventos implements ActionListener {


        public void actionPerformed(ActionEvent e)  {

            DBConnection conection;
            Boolean optimized= true;
            DBBenchmark benchmark;
            long time;
            

            Properties databaseConf = new Properties();

            try (InputStream input = new FileInputStream( "" +
            		"//Mac/Home/Desktop/B/BDFinal/src/main/java/org/uma/bdbio2018/gui/databases.properties"))
   
            	 {

                databaseConf.load(input);
                DBConnectionFactory ConectionFactory= new DBConnectionFactory(databaseConf);

                if (e.getActionCommand().equals("send")) {

                    String consulta = textarea1.getText();
                    String gestor= null;

                    try{

                        if(combobox1.getSelectedItem() == "Mysql"){

                            gestor = "mysql";
                        }

                        else if(combobox1.getSelectedItem() == "mariadb"){

                            gestor = "mariadb";
                        }

                        else if(combobox1.getSelectedItem() == "postgresql"){

                            gestor = "postgresql";
                        }

                        else if(combobox1.getSelectedItem() == "sqlite"){

                            gestor = "sqlite";
                        }

                        else if(combobox1.getSelectedItem() == "existdb"){

                            gestor = "existdb";
                        }

                        else{;}

                        conection = ConectionFactory.makeConnection(gestor, optimized);
                        DBBenchmark.Executor ex = new DBBenchmark.Executor(conection);
                        benchmark = ex.executeQuery(consulta);
                        time = benchmark.getExecutingStatementDuration();
                        textarea2.setText("Required time:"+ " " + time);

                    } catch(BenchmarkException exception){

                        exception.printStackTrace();

                    }

                } else if (e.getActionCommand().equals("exit")) {

                    try {
                    
                        Thread.sleep(500); 
                        System.exit(0); 
                
                    } catch(Exception e){
                    
                        System.exit(0);
                    }   


                } else if (e.getActionCommand().equals("delete")) {

                    textarea1.setText(" ");
                    textarea2.setText(" ");
                } else {;}

            } catch (IOException er) {
                er.printStackTrace();
            }


        }

    }
}
