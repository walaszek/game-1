package graphics;

import core.Main;
import core.math.VectorTwoDim;

public class Camera {

    public static final Camera interfaceCamera=new Camera(0,0);

    public int x,y;
    public static final int WIDTH= Main.WIDTH/2, HEIGHT=Main.HEIGHT/2;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public VectorTwoDim getPosition(){
        return new VectorTwoDim(x,y);
    }

}
