package org.uma.bdbio2018.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;

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

    JComboBox<String> combobox1; // check database manager
    JComboBox<String> combobox2; //check database language

    GridBagConstraints constraints; // elements position

    //window accessories

    Font font;

    //--------
    Event event;

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

        exist = new JButton("Exist");

        delete = new JButton("Delete");
        send = new JButton("Send");

        textarea1 = new TextArea("Write here your request");
        textarea2 = new TextArea();

        checkbox = new JCheckBox("optimize");

        combobox1 = new JComboBox();
        combobox1.addItem("Check database manager");

        combobox1.addItem("Mariadb");
        combobox1.addItem("Mysql");
        combobox1.addItem("postgres");
        combobox1.addItem("sqlite");

        combobox2 = new JComboBox();
        combobox2.addItem("Type of request");
        combobox2.addItem("Xquery");
        combobox2.addItem("Sql");

    }

    public void ButtonConfiguration() {

        InitializeComponents();
        PositionComponents();
    }

    public void EventsConfiguration() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exist.addActionListener(new EventoSalir());
        //hay que seguir añadiendo eventos a los botones.
    }

    public void PositionComponents() {

        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(delete, constraints);

        constraints.gridx = 4;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(send, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridheight = 2;
        constraints.gridwidth = 2;
        this.getContentPane().add(textarea1, constraints);

        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(combobox1, constraints);

        constraints.gridx = 4;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        this.getContentPane().add(combobox2, constraints);

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

}

