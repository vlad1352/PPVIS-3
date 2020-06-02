package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public JFrame frame;
    public Controller controller;
    private int width = 1200;
    private int height = 600;
    private InputPanel inputPanel;
    private InfoTable mainPointsTable;
    private JButton start;
    private  JButton stop;
    private  JButton zoom1;
    private JButton zoom2;

    private Graphic graphic;
    public JScrollPane scroll;
    private ReentrantLock lock;

    public Graphic getGraphic() {
        return graphic;
    }

    public InfoTable getMainPointsTable() {
        return mainPointsTable;
    }

    public Main() {
        lock = new ReentrantLock();
        controller = new Controller(Main.this, lock);
        frame = new JFrame();
        inputPanel = new InputPanel();


    }

    public JFrame buildFrame() {
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout());

        start = new JButton("Начать рисовать");

        stop = new JButton("Остановить и очистить поле");

        zoom1 = new JButton("Приблизить");

        zoom2 = new JButton("Отдалить");



        graphic = new Graphic();
        mainPointsTable = new InfoTable(this);

        scroll = new JScrollPane(graphic);
        scroll.setPreferredSize(new Dimension(605, 505));
        scroll.setAutoscrolls(true);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        frame.add(mainPointsTable.buildTable(), BorderLayout.WEST);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(inputPanel.buildComponent(), BorderLayout.SOUTH);

        JPanel jPanel = new JPanel();
        jPanel.add(start);
        jPanel.add(stop);
        jPanel.add(Box.createHorizontalGlue());
        jPanel.add(zoom1);
        jPanel.add(zoom2);
        frame.add(jPanel, BorderLayout.BEFORE_FIRST_LINE);


        HoldAndDragListener listener = new HoldAndDragListener(graphic);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scroll.getViewport().addMouseListener(listener);
        scroll.getViewport().addMouseMotionListener(listener);

        ZoomListener zoomListener = new ZoomListener(Main.this, graphic);
        scroll.addMouseWheelListener(zoomListener);

        start.addActionListener(event -> {
            controller.stopThreads();
            mainPointsTable.clearTable();
            if(graphic.getFunctionsData().isEmpty())
            {
                graphic.init();
            }
            startDrawing();
        });
        stop.addActionListener(event -> {
            controller.stopThreads();
            mainPointsTable.clearTable();
            graphic.clear();
        });
        zoom1.addActionListener(actionEvent -> {
            zoomListener.onComingAction();
        });
        zoom2.addActionListener(actionEvent -> {
            zoomListener.outComingAction();
        });
        return frame;
    }

    public void startDrawing() {
        controller.startFunctionAThread();
        controller.startFunctionBThread(inputPanel.getN(), inputPanel.getK());
    }

    public static void main(String[] args) {

        Main frame = new Main();
        frame.buildFrame().setVisible(true);

    }

}