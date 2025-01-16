package muscaa.chess.mod;

import fluff.json.JSONObject;

public class ModInfo {
	
	private String id;
	private String name;
	private String version;
	private String clientMain;
	private String serverMain;
	// TODO dependencies
	
	public String getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVersion() {
		return version;
	}
	
	public String getClientMain() {
		return clientMain;
	}
	
	public String getServerMain() {
		return serverMain;
	}
	
	public static ModInfo of(JSONObject json) {
		ModInfo info = new ModInfo();
		info.id = json.getString("id");
		info.name = json.getString("name");
		info.version = json.getString("version");
		info.clientMain = json.getString("clientMain");
		info.serverMain = json.getString("serverMain");
		
		if (info.name == null) info.name = info.id;
		if (info.version == null) info.version = "unspecified";
		
		return info;
	}
}
