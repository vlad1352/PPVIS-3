package view;
import java.awt.event.KeyEvent;

import java.awt.event.MouseWheelEvent;

import java.awt.event.MouseWheelListener;



public class ZoomListener implements MouseWheelListener {

    public Graphic graphic;
    public Main frame;
    public ZoomListener(Main frame, Graphic graphic) {
        this.frame = frame;
        this.graphic = graphic;
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {

        if (event.getPreciseWheelRotation() < 0) {
            if(KeyEvent.VK_CONTROL == 0)
            {
                return;
            }
            graphic.incrementUnitSegment();
            graphic.repaint();
        }

        if (event.getPreciseWheelRotation() > 0) {
            if(KeyEvent.VK_CONTROL == 0)
            {
                return;
            }
            graphic.decrementUnitSegment();
            graphic.repaint();

        }

    }
    public void onComingAction(){
            graphic.incrementUnitSegment();
            graphic.repaint();
    }
    public void outComingAction()
    {
        graphic.decrementUnitSegment();
        graphic.repaint();
    }

}
