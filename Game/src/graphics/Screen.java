package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen {

    public final int HEIGTH;
    public final int WIDTH;

    private BufferedImage image;
    private int[] pixels;

    public Screen(int w, int h) {
        HEIGTH = h;
        WIDTH = w;
        image = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void clear(int color) {
        for (int i = 0; i < WIDTH * HEIGTH; i++) {
            pixels[i] = color;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void renderSprite(int px, int py,int sx,int sy, int size, Spritesheet s, Camera c) {
        if(c==null)c=Camera.interfaceCamera;
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++) {
                pixel(px + x-c.x, py + y-c.y, s.pixels[sx+x+(sy+y*s.WIDTH)]);
            }
    }

    public void renderSprite(int px, int py, Sprite s, Camera c) {
        if(c==null)c=Camera.interfaceCamera;
        for (int y = 0; y < s.height; y++)
            for (int x = 0; x < s.width; x++) {
                pixel(px + x-c.x, py + y-c.y, s.sp.pixels[s.x + x + (s.y+y)* s.sp.WIDTH]);
            }
    }

    public void renderSprite(int px, int py, Sprite s) {
        renderSprite(px, py, s, Camera.interfaceCamera);
    }

    public void renderSprite(int px, int py,int sx,int sy, int size, Spritesheet s) {
        renderSprite(px, py, sx, sy, size, s,Camera.interfaceCamera);
    }



    public void frect(int px, int py, int w, int h, int color) {
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                pixel(x + px, y + py, color);
            }
    }

    private void pixel(int x, int y, int color) {
        if (x < 0 || x >= WIDTH ||
                y < 0 || y >= HEIGTH || color == 0xffff00ff) return;

        pixels[x + y * WIDTH] = color;
    }
}
