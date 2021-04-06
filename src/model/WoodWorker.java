package model;

import java.util.Random;

public class WoodWorker extends AbstractWorker {
    static int id = 0;

    public WoodWorker(RawCollection rc) {
        super(rc);
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
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
