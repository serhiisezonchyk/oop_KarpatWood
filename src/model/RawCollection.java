package model;

import GUI.mainGui;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RawCollection extends AbstractCollection {

    public RawCollection(short maxLength, JLabel lbl, mainGui mg) {
        super(maxLength, lbl, mg);
    }

    @Override
    public String toString() {
        return "RawCollection{" +
                "raws=" + raws +
                '}';
    }

}
