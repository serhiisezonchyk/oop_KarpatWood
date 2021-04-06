package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RawCollection extends AbstractCollection {

    public RawCollection(short maxLength) {
        super(maxLength);
    }

    @Override
    public String toString() {
        return "RawCollection{" +
                "raws=" + raws +
                '}';
    }

}
