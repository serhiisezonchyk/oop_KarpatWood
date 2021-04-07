package model;

public class Truck extends AbstractCollection{

    private boolean isReady = true;

    public boolean isReady() {
        return isReady;
    }

    public Truck(short capacity) {
        super(capacity);
    }

    private synchronized void unLoad(){
        System.out.println("Truck has gone!" + this);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        raws.removeAll(raws);
        System.out.println("Truck has returned!" + this);
        isReady = true;
        notify();
    }

    public synchronized void pushItem(IWooden stem) {
        if (raws.size() == maxLength) {
            isReady = false;
            unLoad();
        }
        raws.add(stem);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "raws=" + raws +
                '}';
    }
}
