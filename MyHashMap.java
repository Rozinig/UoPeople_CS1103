
public class MyHashMap {
	
	Node[] array;
	int arraySize = 128;
	
	public static void main(String args[]) {
		// Simple set of commands to test the map implementation
		MyHashMap map = new MyHashMap();
		map.put("test", "Test");
		map.put("1", "234");
		map.put("2", "345");
		map.put("todo", "None its done!");
		map.put("test", "It worked");
		System.out.println(map.get("test"));
		System.out.println(map.size());
		System.out.println(map.contains("1"));
		System.out.println(map.contains("3"));
		System.out.println(map.get("todo"));
	}
	
	public MyHashMap(){
		array = new Node[arraySize];
	}
	
	public String get(String key) {
		if (contains(key) == false) {
			return null;
		}
		Node link;
		int code = code(key);
		link = array[code];
		while (link.key != key) {
			link = link.next;
		}
		return link.value;
	} // End get method
	
	public boolean put(String key, String value) {
		Node link;
		// Probably do a if statement here if size > array.size then use subroutine to expand array
		int code = code(key);
		if (array[code] == null) {
			array[code] = new Node();
			array[code].key = key;
			array[code].value = value;
		}
		if (contains(key) == true) {
			link = array[code];
			while (link.key != key) {
				link = link.next;
			}
			link.value = value;
		}
		else {
			link = array[code];
			while (link.next != null) {
				link = link.next;				
			}
			link.next = new Node();
			link = link.next;
			link.key = key;
			link.value = value;
			
		}
		return true;
	} // End Put method
	
	public String remove(String key) {
		if (contains(key) == false) {
			return null;
		}
		Node link;
		int code = code(key);
		link = array[code];
		while (link.next.key != key) {
			link = link.next;
		}
		Node temp = link.next;
		if (link.next.next == null) {
			link.next = null;
		}
		else {
			link.next = temp.next;
		}
		return temp.key;
	} // End of remove method
	
	public boolean contains(String key) {
		int code = code(key);
		if (array[code] == null) {
			return false;
		}
		Node link = array[code];
		while(link.key != key) {
			if (link.next == null) {
				return false;
			}
			link = link.next;
		}
		return true;
	} // end of contains method
	
	public int size() {
		int count =0;
		for (int i=0; i<array.length;i++) {
			if (array[i] != null) {
				Node link = array[i];
				count++;
				while (link.next != null) {
					count++;
					link = link.next;
				}
			}
		}
		return count;
	} // end of size method
	
	int code(String key) {
		// simple method just so its consistent
		return Math.abs(key.hashCode()) % arraySize;
	} // end of code method
	
	// Put a method here that creates a new longer array and then goes through each item in the
	// current array are puts them in the longer array with its new hash value
	//Probably best to store the original array in a tempuray array for this
	
	
	class Node{
		public String key;
		public String value;
		public Node next;
	} //  end of node class
	
	

}
