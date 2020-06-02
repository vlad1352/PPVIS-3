package view;


import model.Point;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class InfoTable {

    public Vector<String> columns;
    public DefaultTableModel model;
    public JTable table;
    public JScrollPane scrollPane;
    public Main frame;
    public InfoTable(Main frame) {

        columns = new Vector<String>();
        model = new DefaultTableModel(columns, 0);
        this.frame = frame;

    }
    JScrollPane buildTable() {

        columns.add("x");
        columns.add("y");
        table = new JTable();
        table.setModel(model);
        scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(new Dimension(150, 350));
        return scrollPane;
    }
    public void addNewPoint(Point point) {

        Vector<Double> vector = new Vector<>();
        vector.add(point.getX() );
        vector.add(point.getY() );
        model.addRow(vector);
    }
    public void clearTable() {
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);
    }
}