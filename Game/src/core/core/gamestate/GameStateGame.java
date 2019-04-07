package core.core.gamestate;

import core.GameStateManager;
import game.world.WorldMap;
import graphics.Camera;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;
import input.Mouse;

import java.awt.event.KeyEvent;

public class GameStateGame extends GameState {

    float x = 40, y = 40;

    public static final Sprite spriteS=new Sprite(0,0,16,Spritesheet.def1);
    public static final Sprite spriteHero=new Sprite(4*16,0,16,Spritesheet.def1);
    public WorldMap worldMap;
    public Camera camera;
    private int color;

    public GameStateGame() {
        worldMap=new WorldMap(20,20);
        camera=new Camera(7,15);
    }

    public void update() {
        if(Keyboard.getKey(KeyEvent.VK_ESCAPE)) {
            GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_MENU);//menu
        }
        worldMap.update(camera);

        float speed = 1f;
        if (Keyboard.getKey(KeyEvent.VK_UP)) {
            camera.y -= speed;
        }
        if (Keyboard.getKey(KeyEvent.VK_DOWN)) {
            camera.y +=speed;
        }
        if (Keyboard.getKey(KeyEvent.VK_LEFT)) {
            camera.x -= speed;
        }
        if (Keyboard.getKey(KeyEvent.VK_RIGHT)) {
            camera.x +=speed;
        }

        if(!worldMap.BoxCollision((int)Mouse.xPixel+camera.x,(int)Mouse.yPixel+camera.y,6,12))
            color=0x32ff77;
        else
            color=0xff2222;

    }

    public void render(Screen s) {
        s.clear(0x7ccee2);

        worldMap.render(s,camera);
        s.frect((int)Mouse.xPixel,(int)Mouse.yPixel,6,12,color);

    }
}
