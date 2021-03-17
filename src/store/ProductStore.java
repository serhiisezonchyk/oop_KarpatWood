package store;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import model.IWeight;


public class ProductStore {
    private List<Object> list = new CopyOnWriteArrayList<>();
    private Object monitor = new Object();
    public boolean add(IWeight newProduct) {
        synchronized (monitor) {
		list.add(newProduct);	
		}
        return true;
    }

    public int getCount() {
        return list.size();
    }

    public Object[] getArr() {
        return list.toArray();
    }

    public Iterator<Object> iterator() {
        return list.iterator();
    }

    public void remove(Predicate<Object> predicate) {
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            Object object = (Object) iterator.next();
            if (predicate.test(object))
                iterator.remove();
        }
    }
}
