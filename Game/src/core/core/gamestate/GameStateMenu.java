package core.core.gamestate;

import core.GameStateManager;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;

import java.awt.event.KeyEvent;

import static graphics.Spritesheet.backGround;

public class GameStateMenu extends GameState {

    public static final Sprite spriteBackGround=new Sprite(0,0,backGround.WIDTH,backGround.HEIGHT,Spritesheet.backGround);
    public static final Sprite spriteLogo=new Sprite(0,16,64,32,Spritesheet.def1);
    public static final Sprite spriteNewGame=new Sprite(0,48,64,16,Spritesheet.def1);
    public static final Sprite spriteContinue=new Sprite(0,64,64,16,Spritesheet.def1);
    public static final Sprite spriteCredits=new Sprite(0,80,64,16,Spritesheet.def1);
    public static final Sprite spriteExit=new Sprite(0,96,32,16,Spritesheet.def1);
    public static final Sprite spritePionter=new Sprite(16,0,16,16,Spritesheet.def1);

    private int choose=0;
    private float pointerPosition=0;

    public GameStateMenu() {

    }

    public void update() {
        if(Keyboard.getKey(KeyEvent.VK_ENTER)){
            if(choose==0)GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_GAME);//new game
            //if(choose==1)setPointer(48);//contiunue
            //if(choose==2)setPointer(64);//credits
            if(choose==3)GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_EXIT);//exit
        }

        if(Keyboard.getKeyOnce(KeyEvent.VK_DOWN))choose++;
        if(Keyboard.getKeyOnce(KeyEvent.VK_UP))choose--;

        if(choose>3)choose=0;
        if(choose<0)choose=3;

        if(choose==0)setPointer(32);
        if(choose==1)setPointer(48);
        if(choose==2)setPointer(64);
        if(choose==3)setPointer(80);
    }

    private void setPointer(int i){
        pointerPosition+=(i-pointerPosition)/4;

    }

    public void render(Screen s) {
        s.clear(0x323c3c);
        s.renderSprite(0,0,spriteBackGround,null);
        s.renderSprite(100,0,spriteLogo,null);
        s.renderSprite(16,32,spriteNewGame,null);
        s.renderSprite(16,48,spriteContinue,null);
        s.renderSprite(16,64,spriteCredits,null);
        s.renderSprite(16,80,spriteExit,null);
        s.renderSprite(0,(int)pointerPosition,spritePionter,null);
    }
}