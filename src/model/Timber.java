package model;

import java.awt.Component;

public class Timber extends AbstractWood {
    @Override
    public String toString() {
        return "Timber{" +
                "id=" + id +
                '}';
    }
    public Timber(int id) {
        super(id);
    }

}
