package input;

import core.Main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    public static int xMousePosition,yMousePosition,xPixel,yPixel;
    public static boolean leftButton, rightButton,buttonClikedLeft,buttonClikedRight;
    private static boolean b1=false,b2=false;

    public static void update(){
        if (!b1&&leftButton){
            b1=true;
            buttonClikedLeft=true;
        }

        else{
            buttonClikedLeft=false;
        }

        if(!leftButton){
            b1=false;
        }

        if (!b2&&rightButton){
            b2=true;
            buttonClikedRight=true;
        }

        else{
            buttonClikedRight=false;
        }

        if(!rightButton){
            b2=false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        xMousePosition=e.getX();
        yMousePosition=e.getY();
        xPixel= (int)((float)xMousePosition*((16.0f*17.0f)/((float)Main.WIDTH+10.0f)));
        yPixel= (int)((float)yMousePosition*((9.0f*17.0f)/((float)Main.HEIGHT+10.0f)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseMoved(e);
        if(e.getButton()==e.BUTTON1){
            leftButton=true;
        }
        if(e.getButton()==e.BUTTON3){
            rightButton=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseMoved(e);
        if(e.getButton()==e.BUTTON1){
            leftButton=false;
        }
        if(e.getButton()==e.BUTTON3){
            rightButton=false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
