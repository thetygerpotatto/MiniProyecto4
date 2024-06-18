package promedios;

import javax.swing.JFrame;

public class Prom extends JFrame {
    public Prom() {
        setVisible(true);
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Promedios");
        
    }

    public static void main(String[] args) {
        Prom promedios = new Prom();
    }
}
