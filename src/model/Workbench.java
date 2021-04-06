package model;

public class Workbench extends AbstractWorker{
    TimberCollection tc;

    public Workbench(RawCollection rc, TimberCollection tc) {
        super(rc);
        this.tc = tc;
        System.out.println(this.tc);
    }

    @Override
    protected void transport(AbstractWood aw) {
        System.out.println(tc);

        aw.makeTransported();
        tc.pushItem(aw);
    }

    @Override
    public synchronized void run() {
        while (!Thread.interrupted()) {
            try {
                getTime();
                Thread.sleep(time);
                Stem poped = (Stem)rc.popItem();
                System.out.println("Poped!" + poped);
                getTime();
                Thread.sleep(time);
                Timber timber = poped.convertToTimber();
                poped = null;
                System.out.println("Timber produced = " + timber);
                transport(timber);
                getTime();
                Thread.sleep(time);
                System.out.println("Transported!" + timber);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
