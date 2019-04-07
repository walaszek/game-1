package game.world;

import core.GameStateManager;
import core.math.VectorTwoDim;
import graphics.Camera;
import graphics.Screen;
import input.Mouse;

import java.awt.image.WritableRenderedImage;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class WorldMap {

    public final int WIDTH, HEIGHT;
    public VectorTwoDim gravity;
    public Tile[][] tiles;
    public static Random r = new Random();

    private Hero test;
    private ArrayList<Mob> mobs;

    public WorldMap(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        tiles = new Tile[w][h];
        gravity = new VectorTwoDim(0.0f, 0.3f);

        clear(0);

        //generate area
        for (int y = 8; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                tiles[x][y] = Tile.getTile(1);//insert stone
            }
        }


            for (int y = 0; y < HEIGHT; y++) {
                tiles[0][y] = Tile.getTile(1);//insert stone
                tiles[WIDTH-1][y] = Tile.getTile(1);//insert stone
            }



        int r1;
            int r2;
        Random rand=new Random();

        for (int i=0;i<20;i++) {
            r1 = abs(rand.nextInt() % 20);
            r2 = abs(rand.nextInt() % 6+8);

            tiles[r1][r2] = Tile.getTile(1);//insert stone
        }





        for (int y = 8; y < 10; y++) {
            for (int x = 1; x < WIDTH - 1; x++) {
                tiles[x][y] = Tile.getTile(0);//insert stone
            }
        }
        mobs = new ArrayList<Mob>();
        for (int i = 0; i < 4; i++) {
            float x = 200 * r.nextFloat();
            mobs.add(new Mob(new VectorTwoDim(50.0f + x, 0.0f)));
        }

        test = new Hero(new VectorTwoDim(50.0f, 0.0f));
    }

    public boolean BoxCollision(int pX, int pY, int w, int h) {

        int tileX = pX / 16;
        int tileY = pY / 16;

        //kafelka
        boolean result = false;
        if (BoxCollisionAt(tileX, tileY, pX, pY, w, h)) result = true;
        if (BoxCollisionAt(tileX, tileY + 1, pX, pY, w, h)) result = true;
        if (BoxCollisionAt(tileX + 1, tileY, pX, pY, w, h)) result = true;
        if (BoxCollisionAt(tileX + 1, tileY + 1, pX, pY, w, h)) result = true;

        return result;
    }

//    public boolean BoxCollisionAt(int tx, int ty, int px, int py, int w, int h) {
//
//        int pixelX = tx * 16;
//        int pixelY = ty * 16;
//        if (tx < 0 || tx >= WIDTH || ty < 0 || ty >= HEIGHT)
//            return false;
//        if (px + w > pixelX &&
//                px < pixelX + 16 &&
//                py + h > pixelY &&
//                py < pixelY + 16)
//            return tiles[tx][ty].colider;
//
//        return false;
//    }

    public boolean BoxCollisionAt(int tx, int ty, int px, int py, int w, int h) {

        int pixelX = tx * 16;
        int pixelY = ty * 16;
        if (tx < 0 || tx >= WIDTH || ty < 0 || ty >= HEIGHT)
            return false;
        if (px + w > pixelX &&
                px < pixelX + 16 &&
                py + h > pixelY &&
                py < pixelY + 16)
            return tiles[tx][ty].colider;

        int pixelHeroX = (int) test.mobPosition.x;
        int pixelHeroY = (int) test.mobPosition.y;


        for (int i = 0; i < 4; i++) {
            if (mobs.get(i).mobPosition.x + w > pixelHeroX &&
                    mobs.get(i).mobPosition.x< pixelHeroX + 16 &&
                    mobs.get(i).mobPosition.y + h > pixelHeroY &&
                    mobs.get(i).mobPosition.y < pixelHeroY + 16) {
                GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_MENU);
            }
        }

        return false;
    }


    public void clear(int id) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                tiles[x][y] = Tile.getTile(id);
            }
        }
    }

    public void update(Camera c) {
        int xMapPosition = (Mouse.xPixel + c.x) / 16;
        int yMapPosition = (Mouse.yPixel + c.y) / 16;

        if (!(xMapPosition < 0 || xMapPosition >= WIDTH || yMapPosition < 0 || yMapPosition >= HEIGHT)) {
            if (Mouse.leftButton) {
                tiles[xMapPosition][yMapPosition] = Tile.getTile(1);
            }
        }
        test.update(this);

        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).update(this);
        }
    }

    public void render(Screen s, Camera c) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                tiles[x][y].render(s, x, y, c);
            }
        }
        test.render(s, c);
        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).render(s, c);
        }
    }
}
