package dht;

public class CacheObject {
	private String data;

	public CacheObject(String data){
		this.data = data;
	}

	public String getNodeName() {
		return data;
	}
}
