package com.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CollectionsClass {
	public static void main(String[] args) {
		/*HashMap<String, String> hm = new HashMap<String, String>();
		//TreeMap<String, String> hm = new TreeMap<String, String>();
		hm.put("a", "z");
		hm.put("b", "y");
		hm.put("c", "x");
		hm.put("d", "w");
	
		Iterator<String> it = hm.keySet().iterator();
		String key;
		while (it.hasNext()) {
			key = (String) it.next();
			System.out.println(" key : "+key +"  value :" +hm.get(key));
			
		}*/
		
		Employee e1  = new Employee(1, "Janardhan");
		Employee e2 = new Employee(1, "Kamal");
		Employee e3 = new Employee(3, "Anchal");
		
		ArrayList<Employee> al = new ArrayList<Employee>();
		al.add(e1);
		al.add(e2);
		al.add(e3);
		
		Collections.sort(al);
		
		System.out.println("Al :"+Arrays.asList(al));
		
		Iterator<Employee> it = al.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		
	}
}

