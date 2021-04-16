package test;

import model.*;

public class workerTest {

    public static void main(String[] args) {

        Stem stem = new Stem(1);
        ((IWooden)stem).makeTransported();

        RawCollection rc = new RawCollection((short) 5);
        Garage garage = new Garage((short)2);
        TimberCollection tc = new TimberCollection((short) 5,garage);
        /*WoodWorker worker = new WoodWorker(rc);
        Thread thread1 = new Thread(worker);
        WoodWorker worker2 = new WoodWorker(rc);
        Thread thread2 = new Thread(worker2);
        WoodWorker worker3 = new WoodWorker(rc);
        Thread thread3 = new Thread(worker3);*/
        Workbench workbench = new Workbench(rc, tc);
        Thread thread4 = new Thread(workbench);
        Workbench workbench2 = new Workbench(rc, tc);
        Thread thread5 = new Thread(workbench2);


        //Truck truck = new Truck((short)5);
        //Truck truck2 = new Truck((short)5);

        //System.out.println(worker.getTime());
        /*thread1.start();
        thread2.start();
        thread3.start();*/
        thread4.start();
        thread5.start();
    }
}
