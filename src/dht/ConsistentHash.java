package dht;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {

	SortedMap<Integer, CacheNode> nodeSpace = new TreeMap<>();
	private final int replicas = 3;

	public void addNode(CacheNode node) {
		for (int i = 0; i < replicas; i++) {
			int hashVal = (int) ((node.hashCode() + Math.pow(10, i)) % 1000);
			nodeSpace.put(hashVal, node);
			System.out.println("Cache node " + node.getNodeName() + " added at location " + hashVal);
		}
	}

	public void removeNode(CacheNode node) {
		for (int i = 0; i < replicas; i++) {
			int hashVal = (int) ((node.hashCode() + Math.pow(10, i)) % 1000);
			nodeSpace.remove(hashVal);
			System.out.println("Cache node " + node.getNodeName() + " removed from location " + hashVal);
		}
	}

	public CacheNode getNextClosestNode(CacheObject object) {
		CacheNode node = null;
		int objectKey = object.hashCode() % 1000;
		System.out.println("Space value for object is " + objectKey);
		
		if(nodeSpace.isEmpty()) {
			System.out.println("There are no cache nodes mapped in the cluster currently");
			return node;
		}
		
		
		if(nodeSpace.containsKey(objectKey)) {
			return nodeSpace.get(objectKey);
		}
		else {
			// Get the list of nodes which appear after this hash value, 
			// and return the first node, which is the next closest, 
			// else, circle back and return the very first node in the node space
			SortedMap<Integer, CacheNode> tailEnd = nodeSpace.tailMap(objectKey);
			node = tailEnd.isEmpty() ? nodeSpace.get(nodeSpace.firstKey()) : tailEnd.get(tailEnd.firstKey());
			System.out.println("Node found for object is " + node.getNodeName());
		}
		return node;
	}
}
