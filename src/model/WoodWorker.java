package model;

import GUI.mainGui;

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

    public WoodWorker(RawCollection rc, JLabel lbl, mainGui mg) {
        super(rc, lbl, mg);
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
                mg.drawAnimation(lbl, mg.lblRaw);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
