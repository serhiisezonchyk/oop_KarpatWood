package model;

import java.util.Random;

public abstract class AbstractWorker implements Runnable {
    long time;
    RawCollection rc;

    public AbstractWorker(RawCollection rc) {
        this.rc = rc;
    }

    protected void getTime() {
        Random rnd = new Random();
        time = rnd.nextInt(1000) + 1000;
    }

    protected abstract void transport(AbstractWood aw);

    @Override
    public abstract void run();
}
