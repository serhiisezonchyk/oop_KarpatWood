package store;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.Wood;


public class WoodDirectory {
	private Map<Integer, Object> map = new HashMap<>();
	{
		map.put(1,new Wood("Модрина", 1.1f));
	}

	public Wood get(int id){
		return (Wood)map.get(id);
	}

}
