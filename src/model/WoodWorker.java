package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class WoodWorker extends AbstractWorker {
    static int id = 0;
    private Graphics2D g2d;
    int x;
    int y;
    int x2;
    int y2;
    Frame frame;

    public WoodWorker(RawCollection rc, int x, int y, int x2, int y2, Graphics2D g2d, Frame frame) {
        super(rc);
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.g2d = g2d;
        this.frame = frame;
    }

    @Override
    protected void transport(AbstractWood stem) {
        System.out.println(rc);

        stem.makeTransported();
        rc.pushItem((Stem)stem);
    }

    protected void transport(Stem stem) {
        System.out.println(rc);

        stem.makeTransported();
        rc.pushItem(stem);
    }

    private Image getImage(){
        BufferedImage img = null;
        try {
            URL url = WoodWorker.class.getResource("../png/brus.png");
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private Image img = null;
    {
        img = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/png/fon.png"));
    }

    @Override
    public void run() {
        while (true) {
            try {
                getTime();
                Thread.sleep(time);
                Object stem;
                synchronized (Stem.class) {
                    WoodWorker.id++;
                    stem = new Stem(WoodWorker.id);
                }
                System.out.println("Produced!" + time + stem);
                getTime();
                Thread.sleep(time);
                transport((Stem) stem);
                System.out.println("Transported!" + time + stem);
                (new Thread(() -> {
                    for (int sx = x, sy = y, i = 0; i < 120; sx += (x2-x)/120, sy += (y2-y)/120, i++) {
                        g2d.setColor(Color.CYAN);
                        // you can draw a rectangle
                        //g2d.fillRect(sx,sy, 40,40);
                        // or try to draw an image
                        g2d.drawImage(getImage(), sx, sy, null);
                        try {
                            Thread.sleep(1000/60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        g2d.setColor(Color.WHITE);
                        // if you'll draw a rectangle, please fix the size of it
                        g2d.fillRect(sx,sy, 236,58);
                    }
                })).start();
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
