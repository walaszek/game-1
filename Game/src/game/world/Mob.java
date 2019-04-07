package game.world;

import core.core.gamestate.GameStateGame;
import core.math.VectorTwoDim;
import graphics.Camera;
import graphics.Screen;
import input.Keyboard;
import input.Mouse;

import java.awt.event.KeyEvent;

public class Mob {

    public VectorTwoDim mobPosition;
    public VectorTwoDim velocity;
    public float healthPionts;
    public boolean isFlying;
    public boolean onGround;

    public Mob(VectorTwoDim mobPosition) {
        this.mobPosition = mobPosition;
        velocity=new VectorTwoDim(1.5f*WorldMap.r.nextFloat(),-2.0f*WorldMap.r.nextFloat());
        healthPionts = 10.0f;
        isFlying = false;
        onGround=false;
    }

    public void update(WorldMap m){
        if (mobPosition.y>300)mobPosition.y=-20;
        velocity.y+=m.gravity.y;

        if(velocity.y>12.0f)velocity.y=12.0f;
        else if(velocity.y<-12.0f)velocity.y=-12.0f;
        if(velocity.x>12.0f)velocity.x=12.0f;
        else if(velocity.x<-12.0f)velocity.x=-12.0f;

        //if(mobPosition.y+velocity.y>117)velocity.y*=-0.45f;

        //if(Keyboard.getKeyOnce(KeyEvent.VK_SPACE))velocity.y=-7.0f;

        move(m);
    }

    public void move(WorldMap m){

        if(m.BoxCollision((int)(mobPosition.x+velocity.x),(int)mobPosition.y,16,16)){
            velocity.x*=-1;
            if(onGround) {
                onGround = false;
                velocity.y=-8.0f;
            }
        }else {
            mobPosition.x+=velocity.x;
        }
        if(m.BoxCollision((int)(mobPosition.x),(int)(mobPosition.y+velocity.y),16,16)){
            velocity.y=0;

            onGround=true;
        }else {
            onGround=false;
            mobPosition.y += velocity.y;
        }
    }

    public void render(Screen s, Camera c){
        s.renderSprite((int)mobPosition.x,(int)mobPosition.y,GameStateGame.spriteS,c);
    }
}
