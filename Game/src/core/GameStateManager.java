package core;

import core.core.gamestate.GameState;
import core.core.gamestate.GameStateGame;
import core.core.gamestate.GameStateMenu;
import graphics.Screen;

public class GameStateManager {
    public static final int GAME_STATE_EXIT=-1;
    public static final int GAME_STATE_MENU=0;
    public static final int GAME_STATE_GAME=1;

    private static GameState gs;
    public static boolean exit=false;

    public static void ChangeGameState(int ID){
        if (ID==GAME_STATE_MENU)gs=new GameStateMenu();
        if (ID==GAME_STATE_GAME)gs=new GameStateGame();
        if (ID==GAME_STATE_EXIT)exit=true;
    }

    public GameStateManager(){
        ChangeGameState(GAME_STATE_MENU);
    }

    public void update(){
        gs.update();
    }

    public void render(Screen s){
        gs.render(s);
    }
}
