package model;

public class TimberCollection extends AbstractCollection{
    Garage garage;
    public TimberCollection(short maxLength, Garage garage) {
        super(maxLength);
        this.garage = garage;
    }

    public synchronized void pushItem(IWooden stem) {
        System.out.println("Filling the garage..." + stem);
        garage.fillGarage(stem);
    }

    @Override
    public String toString() {
        return "TimberCollection{" +
                " raws=" + raws +
                '}';
    }
}
