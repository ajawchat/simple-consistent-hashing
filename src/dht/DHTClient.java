package dht;

public class DHTClient {
	public static void main(String[] a){
		ConsistentHash ch = new ConsistentHash();
		
		CacheObject obj1 = new CacheObject("This is test1");
		ch.getNextClosestNode(obj1);
		
		System.out.println();
		
		CacheNode node1 = new CacheNode("4bf7d9f436dbb");
		ch.addNode(node1);
		
		System.out.println();
		CacheNode node2 = new CacheNode("5622aa2f89f73s");
		ch.addNode(node2);
		
		System.out.println();
		ch.getNextClosestNode(obj1);
	}
}
