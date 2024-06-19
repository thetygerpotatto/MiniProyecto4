package promedios;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class StudentTable extends AbstractTableModel {
    private String[][] Data;
    private String[] ColumnNames;
    private final int COLUMNCONT = 2;
    private int Size = 0;

    public StudentTable(String[][] StudentData, String ColumnNames[]) {
        Data = StudentData;
        this.ColumnNames = ColumnNames;
    }

    public StudentTable(String[][] StudentData, String ColumnNames[], int size) {
        Data = StudentData;
        this.ColumnNames = ColumnNames;
        this.Size = size;
    }

    @Override
    public int getColumnCount() {
        return COLUMNCONT;
    }

    @Override
    public int getRowCount() {
        return Size;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        return Data[arg0][arg1];
    }

    @Override
    public String getColumnName(int column) {
        return ColumnNames[column];
    }
    

    public void changeData(String[][] newData, int size) {
        Data = newData;
        this.Size = size;
    }
    
}
