package org.uma.bdbio2018.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.uma.bdbio2018.benchmark.BenchmarkException;
import org.uma.bdbio2018.benchmark.DBBenchmark;
import org.uma.bdbio2018.benchmark.DBConnectionFactory;
import org.uma.bdbio2018.benchmark.contracts.DBConnection;


/**
 * @author Elena Espinosa Garcia
 */

public class Window extends JFrame {

    //Definicion de botones

    private JButton exit;//exist button
    private JButton delete;// delete button
    private JButton send;// send button

    private TextArea textarea1;//request
    private TextArea textarea2;//result

    private JCheckBox checkbox; //optimize database

    private JComboBox<String> combobox1; // check database manager
    //JComboBox<String> combobox2; //check database language

    private GridBagConstraints constraints; // elements position

    //window accessories

    private Font font;

    //Constructor

    public Window() {

        ButtonConfiguration();
        WindowsConfiguration();
        EventsConfiguration();
    }

    private void WindowsConfiguration() {

        this.setSize(800, 500);
        this.setTitle("DBBenchmark");
        this.setVisible(true);
        this.setForeground(Color.blue);
        font = new Font("Arial", Font.PLAIN, 20);
    }

    private void InitializeComponents() {

        this.getContentPane().setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        exit = new JButton("Exit");

        delete = new JButton("Delete");
        send = new JButton("Send");

        textarea1 = new TextArea("Write here your request");
        textarea2 = new TextArea();

        checkbox = new JCheckBox("optimize");

        combobox1 = new JComboBox<>();
        combobox1.addItem("Check database manager");

        combobox1.addItem("mariadb");
        combobox1.addItem("mysql");
        combobox1.addItem("postgresql");
        combobox1.addItem("sqlite");
        combobox1.addItem("existdb");

    }

    private void ButtonConfiguration() {

        InitializeComponents();
        PositionComponents();
    }

    private void EventsConfiguration() {

        send.setActionCommand("send");
        send.addActionListener(new Eventos());
        combobox1.addActionListener(new Eventos());
        exit.setActionCommand("exit");
        exit.addActionListener(new Eventos());
        delete.setActionCommand("delete");
        delete.addActionListener(new Eventos());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void PositionComponents() {

        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(delete, constraints);

        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(send, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridheight = 2;
        constraints.gridwidth = 2;
        constraints.weighty = 1;
        constraints.weightx = 2;
        this.getContentPane().add(textarea1, constraints);

        constraints.gridx = 4;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(combobox1, constraints);
        
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(checkbox, constraints);

        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(exit, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        constraints.gridwidth = 2;
        this.getContentPane().add(textarea2, constraints);

    }

    private class Eventos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Properties databaseConf = new Properties();
            try (InputStream input = new FileInputStream("./src/main/resources/databases.properties")) {
                databaseConf.load(input);
                DBConnectionFactory connectionFactory = new DBConnectionFactory(databaseConf);
                switch (e.getActionCommand()) {
                    case "send":
                        if (combobox1.getSelectedItem() == "Check database manager") {
                            JOptionPane.showMessageDialog(new JFrame(), "You have to select a DBMS to execute"
                                    + " a query.", "DBMS missing", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        try {
                            DBConnection connection = connectionFactory
                                    .makeConnection((String) combobox1.getSelectedItem(),
                                            checkbox.isSelected());
                            DBBenchmark.Executor ex = new DBBenchmark.Executor(connection);
                            DBBenchmark benchmark = ex.executeQuery(textarea1.getText());
                            long time = benchmark.getExecutingStatementDuration();
                            textarea2.setText("Required time: " + " " + time);
                        } catch (BenchmarkException exception) {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    exception.getMessage(),
                                    "Error in executing query attempt",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "exit":
                        System.exit(0);
                    case "delete":
                        textarea1.setText("");
                        textarea2.setText("");
                        break;
                }
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
    }
}
