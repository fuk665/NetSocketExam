package Exam4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("all")
public class Exam4 {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		Map map = new HashMap();
		
		for(int i=0;i<50;i++){
			
			int r = new Random().nextInt(100);
			list.add(r);
			
		}
		
		for (Integer i : list) {
			
			map.put(i%10, i);
			
		}
		
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		List<Integer> list4 = new ArrayList<Integer>();
		List<Integer> list5 = new ArrayList<Integer>();
		List<Integer> list6 = new ArrayList<Integer>();
		List<Integer> list7 = new ArrayList<Integer>();
		List<Integer> list8 = new ArrayList<Integer>();
		List<Integer> list9 = new ArrayList<Integer>();
		
		
		list1=(List<Integer>) map.get(1);
		list2=(List<Integer>) map.get(2);
		list3=(List<Integer>) map.get(3);
		list4=(List<Integer>) map.get(4);
		list5=(List<Integer>) map.get(5);
		list6=(List<Integer>) map.get(6);
		list7=(List<Integer>) map.get(7);
		list8=(List<Integer>) map.get(8);
		list9=(List<Integer>) map.get(9);
		
		List list0 = new ArrayList();
		list0.add(list9);
		list0.add(list8);
		list0.add(list7);
		list0.add(list6);
		list0.add(list5);
		list0.add(list4);
		list0.add(list3);
		list0.add(list2);
		list0.add(list1);
		
		for(int i=0;i<10;i++){
			
		}
		
	}
	
}
