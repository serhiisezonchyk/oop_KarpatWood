package model;

public class AbstractWood implements IWooden {
    protected int id = 0;
    protected boolean isTransported = false;

    public AbstractWood(int id) {
        this.id = id;
    }

    @Override
    public boolean isTransported() {
        return isTransported;
    }

    @Override
    public boolean makeTransported() {
        isTransported = true;
        return true;
    }
}
