package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractCollection {
    protected short maxLength;
    protected List raws;

    public AbstractCollection(short maxLength) {
        this.maxLength = maxLength;raws = new CopyOnWriteArrayList();
    }

    public synchronized void pushItem(IWooden stem) {
        while (raws.size() == maxLength) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        raws.add(stem);
        notify();
    }

    public int getLength() {
        return raws.size();
    }

    public synchronized AbstractWood popItem() {
        while (raws.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        AbstractWood item = (AbstractWood)raws.get(raws.size() - 1);
        raws.remove(raws.size() - 1);
        notify();
        return item;
    }
}
