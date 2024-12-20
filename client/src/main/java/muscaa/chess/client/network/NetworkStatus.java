package muscaa.chess.client.network;

public enum NetworkStatus {
	CONNECT("Connecting"),
	ENCRYPT("Encrypting"),
	LOGIN("Logging in"),
	DONE("Done"),
	;
	
	private final String text;
	
	private NetworkStatus(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
