package promedios;
//?Imports____________________________________________________

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Container;
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
    JTextField info;
    JScrollPane ScrollList;
    JTable studentList;
    StudentTable tableModel;

    String[][] studentInfo = {{"", ""}};
    String[] columnNames = {"Nombre", "Nota"};

    Float averageGrade;

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
        
        info = new JTextField("", JLabel.CENTER);
        info.setEditable(false);
        

        
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
        updateStudentTable();
        updateAverage();
        showAboveAverageStudents();
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
        tableModel.changeData(studentInfo, data.size());
        tableModel.fireTableStructureChanged();

    }

    private void updateAverage() {
        if (data.size() == 0) {
            info.setText("");
            return;
        }
        float acum = 0;
        Iterator<Float> grades = data.values().iterator();
        while(grades.hasNext()) {
            acum += grades.next();
        }

        averageGrade = acum / (float)data.size();

    }

    private void showAboveAverageStudents() {
        String showingText = "Estudiantes por encima de promedio(" + averageGrade + "): ";
        Iterator<Map.Entry<String, Float>> pair = data.entrySet().iterator();
        boolean enter = false;
        while (pair.hasNext()) {
            Map.Entry<String, Float> currentPair = pair.next();
            String name = currentPair.getKey();
            float grade  = currentPair.getValue();
            if (grade > averageGrade) {
                showingText += name +", ";
                enter = true;
            }
        }
        showingText =  showingText.substring(0, showingText.length()-2);


        if (enter) info.setText(showingText);
        else info.setText("No hay estudiantes por encima del promedio");
    }
}
