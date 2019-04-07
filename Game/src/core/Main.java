package core;

import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;
import input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.security.Key;

public class Main extends Canvas implements Runnable {

    public static final String TITLE = "My first game";
    public static final int WIDTH = 1024, HEIGHT = 576;
    private static final int FRAMERATE = 60;

    private boolean RUNNING = false;
    private JFrame frame;
    private Screen screen;
    private Keyboard keyboard = new Keyboard();
    private Mouse mouse=new Mouse();
    private GameStateManager gameStateManager;

    public Main() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyboard);

        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        frame.add(this, new BorderLayout().CENTER);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        int i = 17;

        screen = new Screen(16 * i, 9 * i);

        gameStateManager=new GameStateManager();
    }

    private void start() {
        if (RUNNING) return;
        RUNNING = true;

        new Thread(this, "Game " + TITLE).start();
    }

    private void stop() {
        if (!RUNNING) return;
        RUNNING = false;
        frame.dispose();
        System.exit(0);
    }

    private double timerek = System.currentTimeMillis();
    private int UPS = 0;
    private int FPS = 0;
    private double delta;
    private double frameTime = 1000000000 / FRAMERATE;
    private long timeNow = System.nanoTime();
    private long timeLast = System.nanoTime();

    public void run() {
        while (RUNNING&&!gameStateManager.exit) {
            timeNow = System.nanoTime();
            delta += (timeNow - timeLast) / frameTime;
            timeLast = timeNow;

            if (delta >= 1) {
                update();
                delta -= 1;
                UPS++;
            }

            render();
            FPS++;

            if (System.currentTimeMillis() - timerek >= 1000) {
                timerek = System.currentTimeMillis();
                //System.out.println("FPS: " + FPS + "----- UPS= " + UPS);
                frame.setTitle((TITLE+" -- FPS: " + FPS + "----- UPS= " + UPS));
                FPS = 0;
                UPS = 0;
            }
        }
        stop();
    }

    private void update() {
        keyboard.update();
        mouse.update();
        gameStateManager.update();

    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH + 10, HEIGHT + 10);

        screen.clear(0x000000);

        gameStateManager.render(screen);

        g.drawImage(screen.getImage(), 0, 0, WIDTH+10, HEIGHT+10, null);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}