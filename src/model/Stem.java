package model;

public class Stem extends AbstractWood {

    public Stem(int id) {
        super(id);
    }

    public Timber convertToTimber(){
        return new Timber(id);
    }

    @Override
    public String toString() {
        return "Stem{" +
                "isTransported=" + isTransported +
                "id=" + id +
                '}';
    }

}
