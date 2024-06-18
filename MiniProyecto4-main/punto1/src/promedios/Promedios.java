//?Imports____________________________________________________
package promedios;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane; //Joptionpane
import javax.swing.JTextField;

import java.util.HashMap;
import java.util.Map;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//!Creacion de la clase Prom__________________________________
public class Promedios extends JFrame implements ActionListener
{
    
    // *Creacion de los campos de texto e inputs
    JFrame window;
    Container container;
    JTextField gradeInput;
    JTextField nameInput;
    JButton submmit;
    
    Map<String, Float> data;//<-------

    //?Clase promedios, aqui se crea la ventana________________
    public Promedios() 
    {
        container = getContentPane();
        gradeInput = new JTextField();
        gradeInput.setColumns(20);
        nameInput = new JTextField();
        nameInput.setColumns(20);
        submmit = new JButton("Añadir estudiante");
        data = new HashMap<>();
        setLayout(new FlowLayout());
        
        submmit.addActionListener(this);

        container.add(nameInput);
        container.add(gradeInput);
        container.add(submmit);
        
        setVisible(true);
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Promedios");
    }

    

    //?Psvm___________________________________________________
    public static void main(String[] args)
    {
        Promedios promedios = new Promedios();
    }


    
    @Override
    public void actionPerformed(ActionEvent e) {
        //si usuario valio añadir a hashmap nameimput = key, gradeimput = cont
        String name = nameInput.getText();
        try {
            Float grado = Float.parseFloat(gradeInput.getText());
        } catch (NumberFormatException ex) {
            
        }
    }
}
