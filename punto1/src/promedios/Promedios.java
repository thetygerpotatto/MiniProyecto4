//?Imports____________________________________________________
package promedios;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; //Joptionpane
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//!Creacion de la clase Prom__________________________________
public class Promedios extends JFrame implements ActionListener
{
    
    // *Creacion de los campos de texto e inputs
    JFrame window;
    Container container;
    JPanel inputPane;
    JTextField gradeInput;
    JTextField nameInput;
    JButton submmit;
    JLabel info;
    JScrollPane ScrollList;
    JTable studentList;
    StudentTable tableModel;

    String[][] studentInfo = {{"", ""}};
    String[] columnNames = {"Nombre", "Nota"};

    int studentInfoArraySize = 0;
    
    Map<String, Float> data;//<-------

    //?Clase promedios, aqui se crea la ventana________________
    public Promedios() 
    {
        setLayout(new BorderLayout());

        container = getContentPane();
        
        inputPane = new JPanel();

        gradeInput = new JTextField();
        gradeInput.setColumns(20);

        nameInput = new JTextField();
        nameInput.setColumns(20);

        submmit = new JButton("Añadir estudiante");

        data = new HashMap<>();
        
        tableModel = new StudentTable(studentInfo, columnNames, 1);
        studentList = new JTable(tableModel);
        ScrollList = new JScrollPane(studentList);
        
        info = new JLabel("", JLabel.CENTER);

        
        submmit.addActionListener(this);

        inputPane.add(nameInput);
        inputPane.add(gradeInput);
        inputPane.add(submmit);
        inputPane.add(ScrollList);

        container.add(inputPane, BorderLayout.CENTER);
        container.add(info, BorderLayout.SOUTH);
        
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
        Float grade;
        String name = nameInput.getText();

        try {
            grade = Float.parseFloat(gradeInput.getText());
        } catch (NumberFormatException ex) {
            info.setText("el numero ingresado es invalido");
            return;
        }

        data.put(name, grade);
        System.out.println(data.size());
        updateStudentTable();
    }

    private void updateStudentTable() {
        studentInfo = new String[data.size()][2];
        Iterator<String> keys = data.keySet().iterator();
        int i = 0;
        while (keys.hasNext()) {
            String name = keys.next();
            studentInfo[i][0] = name;
            studentInfo[i][1] = "" + data.get(name);
            ++i;
        }
        System.out.println("i=" + i);
      
        tableModel.changeData(studentInfo, data.size());
        tableModel.fireTableStructureChanged();

    } 
}
